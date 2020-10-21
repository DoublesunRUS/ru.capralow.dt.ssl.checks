/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.core.dataexchange;

import org.eclipse.osgi.util.NLS;

final class Messages
    extends NLS
{
    private static final String BUNDLE_NAME = "ru.capralow.dt.ssl.checks.internal.core.dataExchange.messages"; //$NON-NLS-1$

    public static String ExchangePlanManagers_Error;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
