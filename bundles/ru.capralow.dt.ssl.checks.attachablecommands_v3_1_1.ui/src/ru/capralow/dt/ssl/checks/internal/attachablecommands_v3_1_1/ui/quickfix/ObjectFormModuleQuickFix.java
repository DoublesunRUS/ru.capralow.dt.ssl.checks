/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com._1c.g5.v8.dt.bsl.model.BslPackage;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.model.RegionPreprocessor;
import com._1c.g5.v8.dt.bsl.model.util.BslUtil;
import com._1c.g5.v8.dt.bsl.ui.quickfix.AbstractExternalQuickfixProvider;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.core.platform.IV8ProjectManager;
import com._1c.g5.v8.dt.metadata.mdclass.Configuration;
import com._1c.g5.v8.dt.metadata.mdclass.ScriptVariant;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import com.google.inject.Inject;

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
        onCreateAtServerTemplateContentRu = readContents(getFileInputSupplier(ON_CREATE_AT_SERVER_TEMPLATE_NAME_RU),
            ON_CREATE_AT_SERVER_TEMPLATE_NAME_RU);
        onCreateAtServerTemplateContentEn = readContents(getFileInputSupplier(ON_CREATE_AT_SERVER_TEMPLATE_NAME_EN),
            ON_CREATE_AT_SERVER_TEMPLATE_NAME_EN);
    }

    private static final String ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_RU = "OnCreateAtServerCallRu.txt"; //$NON-NLS-1$
    private static String onCreateAtServerCallTemplateContentRu;
    private static final String ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_EN = "OnCreateAtServerCallEn.txt"; //$NON-NLS-1$
    private static String onCreateAtServerCallTemplateContentEn;

    static
    {
        onCreateAtServerCallTemplateContentRu = readContents(
            getFileInputSupplier(ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_RU), ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_RU);
        onCreateAtServerCallTemplateContentEn = readContents(
            getFileInputSupplier(ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_EN), ON_CREATE_AT_SERVER_CALL_TEMPLATE_NAME_EN);
    }

    private static final String ON_READ_AT_SERVER_TEMPLATE_NAME_RU = "OnReadAtServerRu.txt"; //$NON-NLS-1$
    private static String onReadAtServerTemplateContentRu;
    private static final String ON_READ_AT_SERVER_TEMPLATE_NAME_EN = "OnReadAtServerEn.txt"; //$NON-NLS-1$
    private static String onReadAtServerTemplateContentEn;

    static
    {
        onReadAtServerTemplateContentRu =
            readContents(getFileInputSupplier(ON_READ_AT_SERVER_TEMPLATE_NAME_RU), ON_READ_AT_SERVER_TEMPLATE_NAME_RU);
        onReadAtServerTemplateContentEn =
            readContents(getFileInputSupplier(ON_READ_AT_SERVER_TEMPLATE_NAME_EN), ON_READ_AT_SERVER_TEMPLATE_NAME_EN);
    }

    private static final String ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_RU = "OnReadAtServerCallRu.txt"; //$NON-NLS-1$
    private static String onReadAtServerCallTemplateContentRu;
    private static final String ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_EN = "OnReadAtServerCallEn.txt"; //$NON-NLS-1$
    private static String onReadAtServerCallTemplateContentEn;

    static
    {
        onReadAtServerCallTemplateContentRu = readContents(
            getFileInputSupplier(ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_RU), ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_RU);
        onReadAtServerCallTemplateContentEn = readContents(
            getFileInputSupplier(ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_EN), ON_READ_AT_SERVER_CALL_TEMPLATE_NAME_EN);
    }

    private static CharSource getFileInputSupplier(String partName)
    {
        return Resources.asCharSource(AttacheableCommandsUiPlugin.class.getResource("/resources/" + partName), //$NON-NLS-1$
            StandardCharsets.UTF_8);
    }

    private static String readContents(CharSource source, String path)
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

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT)
    public void executeCommandAtServerAddExport(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Title,
            Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Description, null,
            new ExternalQuickfixModification<>(issue, Method.class, method -> {

                List<INode> paramNodes =
                    NodeModelUtils.findNodesForFeature(method, BslPackage.Literals.METHOD__FORMAL_PARAMS);
                if (paramNodes == null || paramNodes.isEmpty())
                    return null;

                ICompositeNode methodNode = NodeModelUtils.getNode(method);
                INode lastParamNode = paramNodes.get(paramNodes.size() - 1);
                int endOffset = lastParamNode.getTotalEndOffset() - methodNode.getTotalOffset();
                String methodText = methodNode.getText().substring(endOffset);
                int paramsEndOffset = methodText.indexOf(')');
                int insertOffset = methodNode.getTotalOffset() + endOffset + paramsEndOffset + 1;

                return new InsertEdit(insertOffset, " Экспорт"); //$NON-NLS-1$
            }));
    }

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS)
    public void onCreateAtServerAddCommand(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Title,
            Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Description, null,
            new ExternalQuickfixModification<>(issue, Module.class, module -> {

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
