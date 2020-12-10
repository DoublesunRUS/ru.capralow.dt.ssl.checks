/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.antlr.stringtemplate.StringTemplate;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com._1c.g5.v8.bm.core.IBmTransaction;
import com._1c.g5.v8.bm.integration.AbstractBmTask;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.model.RegionPreprocessor;
import com._1c.g5.v8.dt.bsl.model.Statement;
import com._1c.g5.v8.dt.bsl.model.util.BslUtil;
import com._1c.g5.v8.dt.bsl.ui.quickfix.AbstractExternalQuickfixProvider;
import com._1c.g5.v8.dt.core.operations.model.IEditingContext;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.core.platform.IV8ProjectManager;
import com._1c.g5.v8.dt.form.model.EventHandler;
import com._1c.g5.v8.dt.form.model.ExtendedMethodCallType;
import com._1c.g5.v8.dt.form.model.Form;
import com._1c.g5.v8.dt.form.model.FormExtInfo;
import com._1c.g5.v8.dt.form.model.util.ModelUtils;
import com._1c.g5.v8.dt.mcore.Event;
import com._1c.g5.v8.dt.metadata.mdclass.Configuration;
import com._1c.g5.v8.dt.metadata.mdclass.ObjectBelonging;
import com._1c.g5.v8.dt.metadata.mdclass.ScriptVariant;
import com._1c.g5.v8.dt.ui.editor.IDtEditor;
import com._1c.g5.v8.dt.ui.editor.input.DtEditorInput;
import com.google.common.collect.Iterables;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import com.google.inject.Inject;

import ru.capralow.dt.ssl.checks.BslModelUtils;
import ru.capralow.dt.ssl.checks.MdUtils;
import ru.capralow.dt.ssl.checks.attachablecommands_v3_1_1.validator.ObjectFormModuleValidator;
import ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.AttacheableCommandsUiPlugin;

public class ObjectFormModuleQuickFix
    extends AbstractExternalQuickfixProvider
{
    private static void addCallToFormHandler(String eventName, String handlerName, final Issue issue,
        final IssueResolutionAcceptor acceptor, IV8ProjectManager projectManager)
    {
        String errorTitle =
            MessageFormat.format(Messages.Error_ObjectFormModule_FormHandler0MissingCall_Title, handlerName);
        String errorDesc =
            MessageFormat.format(Messages.Error_ObjectFormModule_FormHandler0MissingCall_Description, handlerName);

        acceptor.accept(issue, errorTitle, errorDesc, null,
            new ExternalQuickfixModification<>(issue, Method.class, method -> {
                Module module = EcoreUtil2.getContainerOfType(method, Module.class);
                String formMainAttributeName = MdUtils.getFormMainAttributeNameForModule(module);
                ScriptVariant scriptVariant = getScriptVariant(module, projectManager);
                String template = getFormHandlerCallTemplate(eventName, formMainAttributeName, scriptVariant);

                int insertOffset = getMethodInsertOffset(method);

                return new InsertEdit(insertOffset, System.lineSeparator() + System.lineSeparator() + template);
            }));
    }

    private static void addEventHandler(Form moduleForm, String eventName, String handlerName,
        IBmTransaction transaction)
    {
        boolean isExtension = moduleForm.getMdForm().getObjectBelonging() != ObjectBelonging.NATIVE;
        Form editing = transaction.toTransactionObject(moduleForm);
        FormExtInfo editingExtInfo = transaction.toTransactionObject(moduleForm.getExtInfo());
        Event onCreateAtServerEvent =
            Iterables.find(editing.getFormEvents(), event -> event.getName().equals(eventName), null);
        List<EventHandler> eventHandlers = editing.getHandlers();
        if (isExtension)
            eventHandlers = editingExtInfo.getHandlers();
        Optional<EventHandler> found = ModelUtils.findEventHanlder(onCreateAtServerEvent, eventHandlers);
        if (!found.isPresent())
        {
            EventHandler handler = !isExtension ? ModelUtils.newEventHandler(handlerName, onCreateAtServerEvent)
                : ModelUtils.newEventHandler(handlerName, onCreateAtServerEvent, ExtendedMethodCallType.AFTER);
            ModelUtils.addEventHandler(handler, eventHandlers);
        }

    }

    private static void addEventHandlerUi(String eventName, String handlerName, String errorTitle, Module module)
    {
        Form moduleForm = (Form)module.getOwner();

        if (PlatformUI.getWorkbench() != null && moduleForm.getMdForm() != null)
        {
            PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null)
                {
                    IDtEditor<?> dtEditor = getDtEditorByModel(moduleForm, activeWorkbenchWindow.getActivePage());
                    if (dtEditor != null)
                    {
                        IEditingContext editingContext = dtEditor.getApiEditingContext();
                        if (!editingContext.isDisposed())
                        {
                            editingContext.execute(new AbstractBmTask<IStatus>(errorTitle)
                            {
                                @Override
                                public IStatus execute(IBmTransaction transaction, IProgressMonitor monitor)
                                {
                                    addEventHandler(moduleForm, eventName, handlerName, transaction);
                                    return Status.OK_STATUS;
                                }
                            }, new NullProgressMonitor());
                        }
                    }
                }
            });
        }
    }

    private static void addExport(String handlerName, final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        String errorTitle =
            MessageFormat.format(Messages.Error_ObjectFormModule_Method0MissingExport_Title, handlerName);
        String errorDesc =
            MessageFormat.format(Messages.Error_ObjectFormModule_Method0MissingExport_Description, handlerName);

        acceptor.accept(issue, errorTitle, errorDesc, null,
            new ExternalQuickfixModification<>(issue, Method.class, method -> {
                INode bracketNode = BslModelUtils.getEndBracketParamsNode(method);
                if (bracketNode == null)
                    return null;

                return new InsertEdit(bracketNode.getTotalEndOffset(), " Экспорт"); //$NON-NLS-1$
            }));
    }

    private static void addFormHandler(String eventName, String handlerName, final Issue issue,
        final IssueResolutionAcceptor acceptor, IV8ProjectManager projectManager)
    {
        String errorTitle =
            MessageFormat.format(Messages.Error_ObjectFormModule_FormHandler0NotExists_Title, handlerName);
        String errorDesc =
            MessageFormat.format(Messages.Error_ObjectFormModule_FormHandler0NotExists_Description, handlerName);

        acceptor.accept(issue, errorTitle, errorDesc, null,
            new ExternalQuickfixModification<>(issue, Module.class, module -> {
                addEventHandlerUi(eventName, handlerName, errorTitle, module);

                String formMainAttributeName = MdUtils.getFormMainAttributeNameForModule(module);
                ScriptVariant scriptVariant = getScriptVariant(module, projectManager);

                String template = getFormHandlerTemplate(eventName, formMainAttributeName, scriptVariant);

                int insertOffset = getModuleInsertOffset(module, "ОбработчикиСобытийФормы"); //$NON-NLS-1$

                return new InsertEdit(insertOffset, System.lineSeparator() + System.lineSeparator() + template);
            }));
    }

    private static void addMethods(final Issue issue, final IssueResolutionAcceptor acceptor,
        IV8ProjectManager projectManager)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodsNotExists_Title,
            Messages.Error_ObjectFormModule_MethodsNotExists_Description, null,
            new ExternalQuickfixModification<>(issue, Module.class, module -> {
                String formMainAttributeName = MdUtils.getFormMainAttributeNameForModule(module);
                ScriptVariant scriptVariant = getScriptVariant(module, projectManager);
                String template = getMethodsTemplate(formMainAttributeName, scriptVariant);

                int insertOffset = getModuleInsertOffset(module, "СлужебныеПроцедурыИФункции"); //$NON-NLS-1$

                return new InsertEdit(insertOffset, System.lineSeparator() + System.lineSeparator() + template);
            }));
    }

    private static IDtEditor<?> getDtEditorByModel(Form form, IWorkbenchPage activePage)
    {
        for (IEditorReference reference : activePage.getEditorReferences())
        {
            try
            {
                if (reference.getEditorInput() instanceof DtEditorInput<?>)
                {
                    DtEditorInput<?> input = (DtEditorInput<?>)reference.getEditorInput();
                    if (form.getMdForm().equals(input.getModel()) && reference.getEditor(false) instanceof IDtEditor<?>)
                    {
                        return (IDtEditor<?>)reference.getEditor(false);
                    }
                }
            }
            catch (PartInitException e)
            {
                AttacheableCommandsUiPlugin.log(AttacheableCommandsUiPlugin.createErrorStatus(e.getMessage(), e));
            }
        }
        return null;
    }

    private static CharSource getFileInputSupplier(String partName)
    {
        return Resources.asCharSource(AttacheableCommandsUiPlugin.class.getResource("/resources/" + partName), //$NON-NLS-1$
            StandardCharsets.UTF_8);
    }

    private static String getFormHandlerCallTemplate(String eventName, String formMainAttributeName,
        ScriptVariant scriptVariant)
    {
        String suffix = "En"; //$NON-NLS-1$
        if (scriptVariant.equals(ScriptVariant.RUSSIAN))
            suffix = "Ru"; //$NON-NLS-1$

        String templateCallContent = readContents(getFileInputSupplier(eventName + "Call" + suffix + ".txt")); //$NON-NLS-1$ //$NON-NLS-2$

        StringTemplate template = new StringTemplate(templateCallContent);
        template.setAttribute("FormMainAttribute", formMainAttributeName); //$NON-NLS-1$

        return template.toString();
    }

    private static String getFormHandlerTemplate(String eventName, String formMainAttributeName,
        ScriptVariant scriptVariant)
    {
        String suffix = "En"; //$NON-NLS-1$
        if (scriptVariant.equals(ScriptVariant.RUSSIAN))
            suffix = "Ru"; //$NON-NLS-1$

        String templateContent = readContents(getFileInputSupplier(eventName + suffix + ".txt")); //$NON-NLS-1$
        String templateCallContent = readContents(getFileInputSupplier(eventName + "Call" + suffix + ".txt")); //$NON-NLS-1$ //$NON-NLS-2$

        StringTemplate templateCall = new StringTemplate(templateCallContent);
        templateCall.setAttribute("FormMainAttribute", formMainAttributeName); //$NON-NLS-1$

        StringTemplate template = new StringTemplate(templateContent);
        template.setAttribute("Call", templateCall.toString()); //$NON-NLS-1$

        return template.toString();
    }

    private static int getMethodInsertOffset(Method method)
    {
        ICompositeNode methodNode = NodeModelUtils.findActualNodeFor(method);
        int insertOffset = methodNode.getTotalEndOffset();

        Statement statement = BslModelUtils.getNearestStatement(method, insertOffset - 1);
        if (statement != null)
            insertOffset = NodeModelUtils.findActualNodeFor(statement).getTotalEndOffset() + 1;

        return insertOffset;
    }

    private static String getMethodsTemplate(String formMainAttributeName, ScriptVariant scriptVariant)
    {
        String suffix = "En"; //$NON-NLS-1$
        if (scriptVariant.equals(ScriptVariant.RUSSIAN))
            suffix = "Ru"; //$NON-NLS-1$

        String templateContent = readContents(getFileInputSupplier("AttachableCommands" + suffix + ".txt")); //$NON-NLS-1$ //$NON-NLS-2$

        StringTemplate template = new StringTemplate(templateContent);
        template.setAttribute("FormMainAttribute", formMainAttributeName); //$NON-NLS-1$

        return template.toString();
    }

    private static int getModuleInsertOffset(Module module, String regionName)
    {
        ICompositeNode moduleNode = NodeModelUtils.findActualNodeFor(module);
        int insertOffset = moduleNode.getTotalEndOffset();

        List<RegionPreprocessor> regions = BslUtil.getAllRegionPreprocessors(module);
        if (!regions.isEmpty())
            for (RegionPreprocessor region : regions)
                if (region.getName().equalsIgnoreCase(regionName))
                {
                    insertOffset = NodeModelUtils.findActualNodeFor(region.getItem()).getTotalEndOffset();
                    break;
                }

        return insertOffset;
    }

    private static ScriptVariant getScriptVariant(Module module, IV8ProjectManager projectManager)
    {
        IV8Project v8Project = projectManager.getProject(module);
        Configuration configuration = MdUtils.getConfigurationForProject(v8Project);

        return configuration.getScriptVariant();
    }

    private static String readContents(CharSource source)
    {
        try (Reader reader = source.openBufferedStream())
        {
            return CharStreams.toString(reader);
        }
        catch (IOException | NullPointerException e)
        {
            return ""; //$NON-NLS-1$
        }
    }

    @Inject
    private IV8ProjectManager projectManager;

    @Fix(ObjectFormModuleValidator.ERROR_METHODS_NOT_EXISTS)
    public void executeAddMethods(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addMethods(issue, acceptor, projectManager);
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT)
    public void executeCommandAtServerAddExport(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addExport("ВыполнитьКомандуНаСервере", issue, acceptor); //$NON-NLS-1$
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL)
    public void onCreateAtServerAddCall(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addCallToFormHandler("OnCreateAtServer", "ПриСозданииНаСервере", //$NON-NLS-1$ //$NON-NLS-2$
            issue, acceptor, projectManager);
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS)
    public void onCreateAtServerAddHandler(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addFormHandler("OnCreateAtServer", "ПриСозданииНаСервере", //$NON-NLS-1$ //$NON-NLS-2$
            issue, acceptor, projectManager);
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_MISSING_CALL)
    public void onOpenAddCall(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addCallToFormHandler("OnOpen", "ПриОткрытии", //$NON-NLS-1$ //$NON-NLS-2$
            issue, acceptor, projectManager);
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_NOT_EXISTS)
    public void onOpenAddHandler(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addFormHandler("OnOpen", "ПриОткрытии", //$NON-NLS-1$ //$NON-NLS-2$
            issue, acceptor, projectManager);
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL)
    public void onReadAtServerAddCall(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addCallToFormHandler("OnReadAtServer", "ПриЧтенииНаСервере", //$NON-NLS-1$ //$NON-NLS-2$
            issue, acceptor, projectManager);
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS)
    public void onReadAtServerAddHandler(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        addFormHandler("OnReadAtServer", "ПриЧтенииНаСервере", //$NON-NLS-1$ //$NON-NLS-2$
            issue, acceptor, projectManager);
    }
}
