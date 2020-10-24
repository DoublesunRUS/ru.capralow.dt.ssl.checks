/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator;

import org.eclipse.osgi.util.NLS;

final class Messages
    extends NLS
{
    private static final String BUNDLE_NAME =
        "ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator.messages"; //$NON-NLS-1$

    public static String Error_ObjectFormModule_MethodOnCreateAtServerExists;
    public static String Error_ObjectFormModule_MethodOnReadAtServerExists;
    public static String Error_ObjectFormModule_MethodOnOpenExists;
    public static String Error_ObjectFormModule_MethodExecuteCommandExists;
    public static String Error_ObjectFormModule_MethodExecuteCommandAtServerExists;
    public static String Error_ObjectFormModule_MethodRereadCommandsExists;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
