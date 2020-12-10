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

    public static String Error_ObjectFormModule_FormHandler0NotExists_Title;
    public static String Error_ObjectFormModule_FormHandler0NotExists_Description;

    public static String Error_ObjectFormModule_MethodsNotExists_Title;
    public static String Error_ObjectFormModule_MethodsNotExists_Description;

    public static String Error_ObjectFormModule_FormHandler0MissingCall_Title;
    public static String Error_ObjectFormModule_FormHandler0MissingCall_Description;

    public static String Error_ObjectFormModule_FormHandler0ExcessiveCall_Title;
    public static String Error_ObjectFormModule_FormHandler0ExcessiveCall_Description;

    public static String Error_ObjectFormModule_FormHandler0ExcessiveMethod_Title;
    public static String Error_ObjectFormModule_FormHandler0ExcessiveMethod_Description;

    public static String Error_ObjectFormModule_Method0MissingExport_Title;
    public static String Error_ObjectFormModule_Method0MissingExport_Description;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
