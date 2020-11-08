/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.attachablecommands_v2_4_1.validator;

import org.eclipse.osgi.util.NLS;

final class Messages
    extends NLS
{
    private static final String BUNDLE_NAME =
        "ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator.messages"; //$NON-NLS-1$

    public static String Error_ObjectFormModule_MethodOnCreateAtServerNotExists;
    public static String Error_ObjectFormModule_MethodOnReadAtServerNotExists;
    public static String Error_ObjectFormModule_MethodOnOpenNotExists;
    public static String Error_ObjectFormModule_MethodExecuteCommandNotExists;
    public static String Error_ObjectFormModule_MethodExecuteCommandAtServerNotExists;
    public static String Error_ObjectFormModule_MethodRereadCommandsNotExists;

    public static String Error_ObjectFormModule_MethodOnCreateAtServerMissingCall;
    public static String Error_ObjectFormModule_MethodOnReadAtServerMissingCall;
    public static String Error_ObjectFormModule_MethodOnOpenMissingCall;
    public static String Error_ObjectFormModule_MethodExecuteCommandMissingCall;
    public static String Error_ObjectFormModule_MethodExecuteCommandAtServerMissingCall;
    public static String Error_ObjectFormModule_MethodRereadCommandsMissingCall;

    public static String Error_ObjectFormModule_MethodOnCreateAtServerExcessiveCall;
    public static String Error_ObjectFormModule_MethodOnReadAtServerExcessiveCall;
    public static String Error_ObjectFormModule_MethodOnOpenExcessiveCall;
    public static String Error_ObjectFormModule_MethodExecuteCommandExcessiveMethod;
    public static String Error_ObjectFormModule_MethodExecuteCommandAtServerExcessiveMethod;
    public static String Error_ObjectFormModule_MethodRereadCommandsExcessiveMethod;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
