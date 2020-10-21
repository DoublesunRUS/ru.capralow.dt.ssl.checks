/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.core.dataexchange;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.CancelIndicator;

import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.validation.CustomValidationMessageAcceptor;
import com._1c.g5.v8.dt.bsl.validation.IExternalBslValidator;
import com._1c.g5.v8.dt.mcore.McorePackage;

public class ExchangePlanContents
    implements IExternalBslValidator
{
    public static final String ERROR_CODE = "DataExchange_ExchangePlanContents"; //$NON-NLS-1$

    @Override
    public boolean needValidation(EObject object)
    {
        return object instanceof Method;
    }

    @Override
    public void validate(EObject object, CustomValidationMessageAcceptor messageAcceptor, CancelIndicator monitor)
    {
        Method method = (Method)object;
        messageAcceptor.warning(Messages.ExchangePlanManagers_Error, method, McorePackage.Literals.NAMED_ELEMENT__NAME,
            ERROR_CODE);
    }

}
