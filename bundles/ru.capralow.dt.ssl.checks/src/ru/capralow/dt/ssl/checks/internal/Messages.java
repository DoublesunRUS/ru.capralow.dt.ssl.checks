/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal;

import org.eclipse.osgi.util.NLS;

public class Messages
    extends NLS
{

    private static final String BUNDLE_NAME = "ru.capralow.dt.ssl.checks.internal.messages"; //$NON-NLS-1$

    public static String Failed_to_create_injector_for_0;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
