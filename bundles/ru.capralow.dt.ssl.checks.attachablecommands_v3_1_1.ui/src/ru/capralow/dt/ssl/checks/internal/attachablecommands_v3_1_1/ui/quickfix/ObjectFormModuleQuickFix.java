/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com._1c.g5.v8.bm.core.IBmTransaction;
import com._1c.g5.v8.bm.integration.AbstractBmTask;
import com._1c.g5.v8.bm.integration.IBmModel;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.ui.quickfix.AbstractExternalQuickfixProvider;
import com._1c.g5.v8.dt.core.platform.IBmModelManager;
import com.google.inject.Inject;

import ru.capralow.dt.ssl.checks.attachablecommands_v3_1_1.validator.ObjectFormModuleValidator;

public class ObjectFormModuleQuickFix
    extends AbstractExternalQuickfixProvider
{
    @Inject
    private IBmModelManager bmModelManager;

    @Fix(ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT)
    public void addExport(final Issue issue, final IssueResolutionAcceptor acceptor)
    {
        acceptor.accept(issue, Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Title,
            Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Description, null,
            new ExternalQuickfixModification<>(issue, Method.class, method -> {

                IBmModel model = bmModelManager.getModel(method);
                if (model == null)
                    return null;

                model.getGlobalContext().execute(new AbstractBmTask<Void>(
                    Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Title)
                {
                    @Override
                    public Void execute(IBmTransaction transaction, IProgressMonitor monitor)
                    {

                        Method editing = transaction.toTransactionObject(method);
                        editing.setExport(true);
                        return null;
                    }
                });

                return null;
            }));
    }
}
