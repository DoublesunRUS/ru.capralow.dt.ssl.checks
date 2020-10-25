/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator;

import static com._1c.g5.v8.dt.bsl.model.BslPackage.Literals.MODULE__CONTEXT_DEF;
import static com._1c.g5.v8.dt.bsl.model.BslPackage.Literals.SIMPLE_STATEMENT__LEFT;
import static com._1c.g5.v8.dt.mcore.McorePackage.Literals.NAMED_ELEMENT__NAME;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.CancelIndicator;

import com._1c.g5.v8.dt.bsl.model.DynamicFeatureAccess;
import com._1c.g5.v8.dt.bsl.model.Expression;
import com._1c.g5.v8.dt.bsl.model.Invocation;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.model.ModuleType;
import com._1c.g5.v8.dt.bsl.model.SimpleStatement;
import com._1c.g5.v8.dt.bsl.model.Statement;
import com._1c.g5.v8.dt.bsl.model.StaticFeatureAccess;
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

import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.MdObjects;
import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.SslVersionChecker;

public class ObjectFormModule
    implements IExternalBslValidator
{
    public static final String ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodOnCreateAtServerNotExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodOnReadAtServerNotExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_OPEN_NOT_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodOnOpenNotExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandNotExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandAtServerNotExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS =
        "AttacheableCommands_ObjectFormModule_MethodRereadCommandsNotExists"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL =
        "AttacheableCommands_ObjectFormModule_MethodOnCreateAtServerMissingCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL =
        "AttacheableCommands_ObjectFormModule_MethodOnReadAtServerMissingCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_OPEN_MISSING_CALL =
        "AttacheableCommands_ObjectFormModule_MethodOnOpenMissingCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandMissingCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandAtServerMissingCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL =
        "AttacheableCommands_ObjectFormModule_MethodRereadCommandsMissingCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL =
        "AttacheableCommands_ObjectFormModule_MethodOnCreateAtServerExcessiveCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL =
        "AttacheableCommands_ObjectFormModule_MethodOnReadAtServerExcessiveCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL =
        "AttacheableCommands_ObjectFormModule_MethodOnOpenExcessiveCall"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandExcessiveMethod"; //$NON-NLS-1$

    public static final String ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD =
        "AttacheableCommands_ObjectFormModule_MethodExecuteCommandAtServerExcessiveMethod"; //$NON-NLS-1$

    public static final String ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD =
        "AttacheableCommands_ObjectFormModule_MethodRereadCommandsExcessiveMethod"; //$NON-NLS-1$

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

        EList<String> objectsList = ConnectedObjects.getAllObjects(v8Project);
        String[] moduleNameArray = module.getUniqueName().split("[/]"); //$NON-NLS-1$
        String objectName = MessageFormat.format(MdObjects.MD_OBJECT, moduleNameArray[4], moduleNameArray[5]);
        boolean objectConnected = objectsList.contains(objectName);

        Method methodOnCreateAtServer = null;

        Method methodOnReadAtServer = null;

        Method methodOnOpen = null;

        Method methodExecuteCommand = null;

        Method methodExecuteCommandAtServer = null;

        Method methodRereadCommands = null;

        for (Method method : module.allMethods())
        {
            String methodName = method.getName();

            if (methodName.equalsIgnoreCase("ПриСозданииНаСервере") || methodName.equalsIgnoreCase("OnCreateAtServer")) //$NON-NLS-1$//$NON-NLS-2$
                methodOnCreateAtServer = method;

            else if (methodName.equalsIgnoreCase("ПриЧтенииНаСервере") || methodName.equalsIgnoreCase("OnReadAtServer")) //$NON-NLS-1$ //$NON-NLS-2$
                methodOnReadAtServer = method;

            else if (methodName.equalsIgnoreCase("ПриОткрытии") || methodName.equalsIgnoreCase("OnOpen")) //$NON-NLS-1$ //$NON-NLS-2$
                methodOnOpen = method;

            else if (methodName.equalsIgnoreCase("Подключаемый_ВыполнитьКоманду")) //$NON-NLS-1$
                methodExecuteCommand = method;

            else if (methodName.equalsIgnoreCase("Подключаемый_ВыполнитьКомандуНаСервере")) //$NON-NLS-1$
                methodExecuteCommandAtServer = method;

            else if (methodName.equalsIgnoreCase("Подключаемый_ОбновитьКоманды")) //$NON-NLS-1$
                methodRereadCommands = method;

        }

        validateOnCreateAtServer(objectConnected, methodOnCreateAtServer, module, messageAcceptor);
        validateOnReadAtServer(objectConnected, methodOnReadAtServer, module, messageAcceptor);
        validateOnOpen(objectConnected, methodOnOpen, module, messageAcceptor);
        validateExecuteCommand(objectConnected, methodExecuteCommand, module, messageAcceptor);
        validateExecuteCommandAtServer(objectConnected, methodExecuteCommandAtServer, module, messageAcceptor);
        validateRereadCommands(objectConnected, methodRereadCommands, module, messageAcceptor);
    }

    private Statement checkCall(String methodName, Method method)
    {
        if (method == null)
            return null;

        for (Statement statement : method.getStatements())
        {
            if (!(statement instanceof SimpleStatement))
                continue;

            Expression leftStatement = ((SimpleStatement)statement).getLeft();
            if (!(leftStatement instanceof Invocation))
                continue;

            DynamicFeatureAccess methodAccess = (DynamicFeatureAccess)((Invocation)leftStatement).getMethodAccess();

            String statementMethodName = MessageFormat.format("{0}.{1}", //$NON-NLS-1$
                ((StaticFeatureAccess)methodAccess.getSource()).getName(), methodAccess.getName());

            if (methodName.equalsIgnoreCase(statementMethodName))
                return statement;
        }

        return null;
    }

    private void validateExecuteCommand(boolean objectConnected, Method method, Module module,
        CustomValidationMessageAcceptor messageAcceptor)
    {
        boolean methodExists = method != null;
        Statement methodStatement = checkCall("ПодключаемыеКомандыКлиент.ВыполнитьКоманду", method); //$NON-NLS-1$
        boolean methodHasCall = methodStatement != null;

        if (objectConnected)
        {
            if (!methodExists)
            {
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandNotExists, module,
                    MODULE__CONTEXT_DEF, ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
                return;
            }

            if (!methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandMissingCall, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
        }
        else
        {
            if (methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandExcessiveMethod, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD);

        }

    }

    private void validateExecuteCommandAtServer(boolean objectConnected, Method method, Module module,
        CustomValidationMessageAcceptor messageAcceptor)
    {
        boolean methodExists = method != null;
        Statement methodStatement = checkCall("ПодключаемыеКоманды.ВыполнитьКоманду", method); //$NON-NLS-1$
        boolean methodHasCall = methodStatement != null;

        if (objectConnected)
        {
            if (!methodExists)
            {
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerNotExists, module,
                    MODULE__CONTEXT_DEF, ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
                return;
            }

            if (!methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerMissingCall, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
        }
        else
        {
            if (methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodExecuteCommandAtServerExcessiveMethod,
                    method, NAMED_ELEMENT__NAME, ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD);

        }

    }

    private void validateOnCreateAtServer(boolean objectConnected, Method method, Module module,
        CustomValidationMessageAcceptor messageAcceptor)
    {
        boolean methodExists = method != null;
        Statement methodStatement = checkCall("ПодключаемыеКоманды.ПриСозданииНаСервере", method); //$NON-NLS-1$
        boolean methodHasCall = methodStatement != null;

        if (objectConnected)
        {
            if (!methodExists)
            {
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnCreateAtServerNotExists, module,
                    MODULE__CONTEXT_DEF, ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
                return;
            }

            if (!methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnCreateAtServerMissingCall, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
        }
        else
        {
            if (methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnCreateAtServerExcessiveCall,
                    methodStatement, SIMPLE_STATEMENT__LEFT, ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL);

        }

    }

    private void validateOnOpen(boolean objectConnected, Method method, Module module,
        CustomValidationMessageAcceptor messageAcceptor)
    {
        boolean methodExists = method != null;
        Statement methodStatement = checkCall("ПодключаемыеКомандыКлиент.НачатьОбновлениеКоманд", method); //$NON-NLS-1$
        boolean methodHasCall = methodStatement != null;

        if (objectConnected)
        {
            if (!methodExists)
            {
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnOpenNotExists, module,
                    MODULE__CONTEXT_DEF, ERROR_METHOD_ON_OPEN_NOT_EXISTS);
                return;
            }

            if (!methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnOpenMissingCall, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_ON_OPEN_MISSING_CALL);
        }
        else
        {
            if (methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnOpenExcessiveCall, methodStatement,
                    SIMPLE_STATEMENT__LEFT, ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL);

        }

    }

    private void validateOnReadAtServer(boolean objectConnected, Method method, Module module,
        CustomValidationMessageAcceptor messageAcceptor)
    {
        boolean methodExists = method != null;
        Statement methodStatement = checkCall("ПодключаемыеКомандыКлиентСервер.ОбновитьКоманды", method); //$NON-NLS-1$
        boolean methodHasCall = methodStatement != null;

        if (objectConnected)
        {
            if (!methodExists)
            {
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnReadAtServerNotExists, module,
                    MODULE__CONTEXT_DEF, ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
                return;
            }

            if (!methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnReadAtServerMissingCall, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
        }
        else
        {
            if (methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodOnReadAtServerExcessiveCall,
                    methodStatement, SIMPLE_STATEMENT__LEFT, ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL);

        }

    }

    private void validateRereadCommands(boolean objectConnected, Method method, Module module,
        CustomValidationMessageAcceptor messageAcceptor)
    {
        boolean methodExists = method != null;
        Statement methodStatement = checkCall("ПодключаемыеКомандыКлиентСервер.ОбновитьКоманды", method); //$NON-NLS-1$
        boolean methodHasCall = methodStatement != null;

        if (objectConnected)
        {
            if (!methodExists)
            {
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodRereadCommandsNotExists, module,
                    MODULE__CONTEXT_DEF, ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
                return;
            }

            if (!methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodRereadCommandsMissingCall, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
        }
        else
        {
            if (methodHasCall)
                messageAcceptor.warning(Messages.Error_ObjectFormModule_MethodRereadCommandsExcessiveMethod, method,
                    NAMED_ELEMENT__NAME, ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD);

        }

    }

}
