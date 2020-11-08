/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix;

import java.util.List;

import org.eclipse.text.edits.InsertEdit;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com._1c.g5.v8.dt.bsl.model.BslPackage;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.ui.quickfix.AbstractExternalQuickfixProvider;

import ru.capralow.dt.ssl.checks.attachablecommands_v3_1_1.validator.ObjectFormModuleValidator;

public class ObjectFormModuleQuickFix
    extends AbstractExternalQuickfixProvider
{

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT)
    public void addExport(final Issue issue, final IssueResolutionAcceptor acceptor)
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
}
