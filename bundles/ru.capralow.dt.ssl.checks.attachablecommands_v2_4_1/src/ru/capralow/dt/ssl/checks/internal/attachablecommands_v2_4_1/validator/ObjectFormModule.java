/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator;

import static com._1c.g5.v8.dt.bsl.model.BslPackage.Literals.MODULE__CONTEXT_DEF;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.CancelIndicator;

import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.validation.CustomValidationMessageAcceptor;
import com._1c.g5.v8.dt.bsl.validation.IExternalBslValidator;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.core.platform.IV8ProjectManager;
import com.google.inject.Inject;

import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.SslVersionChecker;

public class ObjectFormModule
    implements IExternalBslValidator
{
    public static final String ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodOnCreateAtServerExists"; //$NON-NLS-1$

    @Inject
    IV8ProjectManager v8ProjectManager;

    @Override
    public boolean needValidation(EObject object)
    {
        return object instanceof Module;
    }

    @Override
    public void validate(EObject object, CustomValidationMessageAcceptor messageAcceptor, CancelIndicator monitor)
    {
        if (monitor.isCanceled())
            return;

        IV8Project v8Project = v8ProjectManager.getProject(object);

        if (!SslVersionChecker.checkSslVersion(v8Project, "2.4.1", "3.0.3")) //$NON-NLS-1$ //$NON-NLS-2$
            return;

        Module module = (Module)object;

        boolean methodOnCreateAtServerExists = false;
        boolean methodOnCreateAtServerCorrect = false;

        boolean methodExecuteCommandExists = false;
        boolean methodExecuteCommandCorrect = false;

        boolean methodExecuteCommandAtServerExists = false;
        boolean methodExecuteCommandAtServerCorrect = false;

        boolean methodRereadCommandsExists = false;
        boolean methodRereadCommandsCorrect = false;

        boolean methodOnReadAtServerExists = false;
        boolean methodOnReadAtServerCorrect = false;

        boolean methodOnOpenExists = false;
        boolean methodOnOpenCorrect = false;

        if (!methodOnCreateAtServerExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnCreateAtServerExists, module,
                MODULE__CONTEXT_DEF, ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS);
    }

}
