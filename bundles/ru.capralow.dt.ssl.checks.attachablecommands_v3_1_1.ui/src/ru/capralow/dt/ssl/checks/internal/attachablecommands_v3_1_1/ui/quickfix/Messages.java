/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix;

import org.eclipse.osgi.util.NLS;

final class Messages
    extends NLS
{
    private static final String BUNDLE_NAME =
        "ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui.quickfix.messages"; //$NON-NLS-1$

    public static String Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Title;
    public static String Error_ObjectFormModule_MethodOnCreateAtServerNotExists_Description;
    public static String Error_ObjectFormModule_MethodOnReadAtServerNotExists_Title;
    public static String Error_ObjectFormModule_MethodOnReadAtServerNotExists_Description;
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

    public static String Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Title;
    public static String Error_ObjectFormModule_MethodExecuteCommandAtServerMissingExport_Description;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
