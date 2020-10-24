/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator;

import static com._1c.g5.v8.dt.bsl.model.BslPackage.Literals.MODULE__CONTEXT_DEF;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.CancelIndicator;

import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.model.ModuleType;
import com._1c.g5.v8.dt.bsl.validation.CustomValidationMessageAcceptor;
import com._1c.g5.v8.dt.bsl.validation.IExternalBslValidator;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.core.platform.IV8ProjectManager;
import com._1c.g5.v8.dt.form.model.Form;
import com._1c.g5.v8.dt.form.model.FormAttribute;
import com._1c.g5.v8.dt.form.service.FormUtil;
import com._1c.g5.v8.dt.mcore.TypeItem;
import com._1c.g5.v8.dt.mcore.util.McoreUtil;
import com.google.inject.Inject;

import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.SslVersionChecker;

public class ObjectFormModule
    implements IExternalBslValidator
{
    public static final String ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodOnCreateAtServerExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_READ_AT_SERVER_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodOnReadAtServerExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_OPEN_EXISTS = "AttacheableCommands_ObjectFormModule_MethodOnOpenExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandAtServerExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_REREAD_COMMANDS_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodRereadCommandsExists"; //$NON-NLS-1$

    @Inject
    IV8ProjectManager v8ProjectManager;

    @Override
    public boolean needValidation(EObject object)
    {
        return object instanceof Module && ((Module)object).getModuleType() == ModuleType.FORM_MODULE;
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

        Form moduleForm = (Form)module.getOwner();
        FormAttribute mainAttribute = FormUtil.getMainAttribute(moduleForm);
        if (mainAttribute == null)
            return;

        if (FormUtil.isListForm(moduleForm, mainAttribute))
            return;

        TypeItem mainType = FormUtil.getExactAttributeType(mainAttribute);

        String mainTypeName = McoreUtil.getTypeName(mainType);

        if ("DynamicList".equals(mainTypeName)) //$NON-NLS-1$
            return; // Пока isListForm не работает

        if (!mainTypeName.contains("Object.")) //$NON-NLS-1$
            return;

        boolean methodOnCreateAtServerExists = false;
        boolean methodOnCreateAtServerCorrect = false;

        boolean methodOnReadAtServerExists = false;
        boolean methodOnReadAtServerCorrect = false;

        boolean methodOnOpenExists = false;
        boolean methodOnOpenCorrect = false;

        boolean methodExecuteCommandExists = false;
        boolean methodExecuteCommandCorrect = false;

        boolean methodExecuteCommandAtServerExists = false;
        boolean methodExecuteCommandAtServerCorrect = false;

        boolean methodRereadCommandsExists = false;
        boolean methodRereadCommandsCorrect = false;

        for (Method method : module.allMethods())
        {
            String methodName = method.getName();

            if (methodName.equalsIgnoreCase("ПриСозданииНаСервере") || methodName.equalsIgnoreCase("OnCreateAtServer")) //$NON-NLS-1$ //$NON-NLS-2$
                methodOnCreateAtServerExists = true;
            else if (methodName.equalsIgnoreCase("ПриЧтенииНаСервере") || methodName.equalsIgnoreCase("OnReadAtServer")) //$NON-NLS-1$ //$NON-NLS-2$
                methodOnReadAtServerExists = true;
            else if (methodName.equalsIgnoreCase("ПриОткрытии") || methodName.equalsIgnoreCase("OnOpen")) //$NON-NLS-1$ //$NON-NLS-2$
                methodOnOpenExists = true;
            else if (methodName.equalsIgnoreCase("Подключаемый_ВыполнитьКоманду")) //$NON-NLS-1$
                methodExecuteCommandExists = true;
            else if (methodName.equalsIgnoreCase("Подключаемый_ВыполнитьКомандуНаСервере")) //$NON-NLS-1$
                methodExecuteCommandAtServerExists = true;
            else if (methodName.equalsIgnoreCase("Подключаемый_ОбновитьКоманды")) //$NON-NLS-1$
                methodRereadCommandsExists = true;
        }

        if (!methodOnCreateAtServerExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnCreateAtServerExists, module,
                MODULE__CONTEXT_DEF, ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS);

        if (!methodOnReadAtServerExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnReadAtServerExists, module,
                MODULE__CONTEXT_DEF, ERROR_METHOD_ON_READ_AT_SERVER_EXISTS);

        if (!methodOnOpenExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnOpenExists, module, MODULE__CONTEXT_DEF,
                ERROR_METHOD_ON_OPEN_EXISTS);

        if (!methodExecuteCommandExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandExists, module,
                MODULE__CONTEXT_DEF, ERROR_METHOD_EXECUTE_COMMAND_EXISTS);

        if (!methodExecuteCommandAtServerExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerExists, module,
                MODULE__CONTEXT_DEF, ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXISTS);

        if (!methodRereadCommandsExists)
            messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodRereadCommandsExists, module,
                MODULE__CONTEXT_DEF, ERROR_METHOD_REREAD_COMMANDS_EXISTS);
    }

}
