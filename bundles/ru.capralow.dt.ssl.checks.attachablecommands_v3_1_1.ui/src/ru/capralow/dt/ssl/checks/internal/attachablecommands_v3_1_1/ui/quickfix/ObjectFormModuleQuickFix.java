/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.antlr.stringtemplate.StringTemplate;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com._1c.g5.v8.bm.core.IBmTransaction;
import com._1c.g5.v8.bm.integration.AbstractBmTask;
import com._1c.g5.v8.bm.integration.IBmModel;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.model.RegionPreprocessor;
import com._1c.g5.v8.dt.bsl.model.util.BslUtil;
import com._1c.g5.v8.dt.bsl.ui.quickfix.AbstractExternalQuickfixProvider;
import com._1c.g5.v8.dt.core.platform.IBmModelManager;
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

    private static final String ON_CREATE_AT_SERVER_TEMPLATE_NAME_RU = "OnCreateAtServerRu.txt"; //$NON-NLS-1$
    private static String onCreateAtServerTemplateContentRu;
    private static final String ON_CREATE_AT_SERVER_TEMPLATE_NAME_EN = "OnCreateAtServerEn.txt"; //$NON-NLS-1$

    private static String onCreateAtServerTemplateContentEn;

    static
    {
        onCreateAtServerTemplateContentRu = readContents(getFileInputSupplier(ON_CREATE_AT_SERVER_TEMPLATE_NAME_RU));
        onCreateAtServerTemplateContentEn = readContents(getFileInputSupplier(ON_CREATE_AT_SERVER_TEMPLATE_NAME_EN));
    }
    private static final String ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_RU = "OnCreateAtServerCallRu.txt"; //$NON-NLS-1$
    private static String onCreateAtServerCallTemplateContentRu;
    private static final String ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_EN = "OnCreateAtServerCallEn.txt"; //$NON-NLS-1$

    private static String onCreateAtServerCallTemplateContentEn;

    static
    {
        onCreateAtServerCallTemplateContentRu =
            readContents(getFileInputSupplier(ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_RU));
        onCreateAtServerCallTemplateContentEn =
            readContents(getFileInputSupplier(ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_EN));
    }
    private static final String ON_READ_AT_SERVER_TEMPLATE_NAME_RU = "OnReadAtServerRu.txt"; //$NON-NLS-1$
    private static String onReadAtServerTemplateContentRu;
    private static final String ON_READ_AT_SERVER_TEMPLATE_NAME_EN = "OnReadAtServerEn.txt"; //$NON-NLS-1$

    private static String onReadAtServerTemplateContentEn;

    static
    {
        onReadAtServerTemplateContentRu = readContents(getFileInputSupplier(ON_READ_AT_SERVER_TEMPLATE_NAME_RU));
        onReadAtServerTemplateContentEn = readContents(getFileInputSupplier(ON_READ_AT_SERVER_TEMPLATE_NAME_EN));
    }
    private static final String ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_RU = "OnReadAtServerCallRu.txt"; //$NON-NLS-1$
    private static String onReadAtServerCallTemplateContentRu;
    private static final String ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_EN = "OnReadAtServerCallEn.txt"; //$NON-NLS-1$

    private static String onReadAtServerCallTemplateContentEn;

    static
    {
        onReadAtServerCallTemplateContentRu =
            readContents(getFileInputSupplier(ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_RU));
        onReadAtServerCallTemplateContentEn =
            readContents(getFileInputSupplier(ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_EN));
    }

    private static CharSource getFileInputSupplier(String partName)
    {
        return Resources.asCharSource(AttacheableCommandsUiPlugin.class.getResource("/resources/" + partName), //$NON-NLS-1$
            StandardCharsets.UTF_8);
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
    private IBmModelManager bmModelManager;
    @Inject
    private IV8ProjectManager projectManager;

    private void addEventHandler(Form moduleForm, String eventName, String handlerName, IBmTransaction transaction)
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

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT)
    public void executeCommandAtServerAddExport(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Title,
            Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Description, null,
            new ExternalQuickfixModification<>(issue, Method.class, method -> {
                INode bracketNode = BslModelUtils.getEndBracketParamsNode(method);
                if (bracketNode == null)
                    return null;

                return new InsertEdit(bracketNode.getTotalEndOffset(), " Экспорт"); //$NON-NLS-1$
            }));
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS)
    public void onCreateAtServerAddCommand(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Title,
            Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Description, null,
            new ExternalQuickfixModification<>(issue, Module.class, module -> {

                Form moduleForm = (Form)module.getOwner();
                IBmModel model = bmModelManager.getModel(moduleForm);
                if (model == null)
                    return null;

                model.createLocalContext(Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Title).execute(
                    new AbstractBmTask<IStatus>(Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Title)
                    {
                        @Override
                        public IStatus execute(IBmTransaction transaction, IProgressMonitor monitor)
                        {
                            addEventHandler(moduleForm, "OnCreateAtServer", "ПриСозданииНаСервере", transaction); //$NON-NLS-1$ //$NON-NLS-2$
                            return Status.OK_STATUS;
                        }
                    });

                IV8Project v8Project = projectManager.getProject(module);
                Configuration configuration = MdUtils.getConfigurationForProject(v8Project);

                String templateContent;
                String templateCallContent;
                if (configuration.getScriptVariant().equals(ScriptVariant.RUSSIAN))
                {
                    templateContent = onCreateAtServerTemplateContentRu;
                    templateCallContent = onCreateAtServerCallTemplateContentRu;
                }
                else
                {
                    templateContent = onCreateAtServerTemplateContentEn;
                    templateCallContent = onCreateAtServerCallTemplateContentEn;
                }

                StringTemplate template = new StringTemplate(templateContent);
                template.setAttribute("Call", templateCallContent); //$NON-NLS-1$

                ICompositeNode moduleNode = NodeModelUtils.findActualNodeFor(module);
                int insertOffset = moduleNode.getTotalEndOffset();

                List<RegionPreprocessor> regions = BslUtil.getAllRegionPreprocessors(module);
                if (!regions.isEmpty())
                    for (RegionPreprocessor region : regions)
                        if (region.getName().equalsIgnoreCase("ОбработчикиСобытийФормы")) //$NON-NLS-1$
                        {
                            insertOffset = NodeModelUtils.findActualNodeFor(region.getItem()).getTotalEndOffset();
                            break;
                        }

                return new InsertEdit(insertOffset,
                    System.lineSeparator() + System.lineSeparator() + template.toString());
            }));
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS)
    public void onReadAtServerAddCommand(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodOnReadAtServerNotExists_Title,
            Messages.Error_ObjectFormModule_MethodOnReadAtServerNotExists_Description, null,
            new ExternalQuickfixModification<>(issue, Module.class, module -> {

                IV8Project v8Project = projectManager.getProject(module);
                Configuration configuration = MdUtils.getConfigurationForProject(v8Project);

                String templateContent;
                String templateCallContent;
                if (configuration.getScriptVariant().equals(ScriptVariant.RUSSIAN))
                {
                    templateContent = onReadAtServerTemplateContentRu;
                    templateCallContent = onReadAtServerCallTemplateContentRu;
                }
                else
                {
                    templateContent = onReadAtServerTemplateContentEn;
                    templateCallContent = onReadAtServerCallTemplateContentEn;
                }

                StringTemplate templateCall = new StringTemplate(templateCallContent);
                templateCall.setAttribute("Object", "Объект"); //$NON-NLS-1$ //$NON-NLS-2$

                StringTemplate template = new StringTemplate(templateContent);
                template.setAttribute("Call", templateCall.toString()); //$NON-NLS-1$

                ICompositeNode moduleNode = NodeModelUtils.findActualNodeFor(module);
                int insertOffset = moduleNode.getTotalEndOffset();

                List<RegionPreprocessor> regions = BslUtil.getAllRegionPreprocessors(module);
                if (!regions.isEmpty())
                    for (RegionPreprocessor region : regions)
                        if (region.getName().equalsIgnoreCase("ОбработчикиСобытийФормы")) //$NON-NLS-1$
                        {
                            insertOffset = NodeModelUtils.findActualNodeFor(region.getItem()).getTotalEndOffset();
                            break;
                        }

                return new InsertEdit(insertOffset,
                    System.lineSeparator() + System.lineSeparator() + template.toString());
            }));
    }
}
