/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator;

import java.text.MessageFormat;

import com._1c.g5.v8.dt.bsl.model.DynamicFeatureAccess;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.SimpleStatement;
import com._1c.g5.v8.dt.bsl.model.Statement;
import com._1c.g5.v8.dt.bsl.model.StringLiteral;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.metadata.mdclass.CommonModule;
import com._1c.g5.v8.dt.metadata.mdclass.MdObject;

import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.MdObjects;
import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.Messages;

public class ValidationObjects
{

    public static void getValidationObjects(IV8Project v8Project)
    {
        getPrintObjects(v8Project);
    }

    private static void getPrintObjects(IV8Project v8Project)
    {
        MdObject configurationObject = MdObjects.getConfigurationObject(
            MessageFormat.format(MdObjects.CONFIGURATION_OBJECT, "ОбщийМодуль", "УправлениеПечатьюПереопределяемый"), //$NON-NLS-2$
            v8Project);
        if (configurationObject == null)
            return;

        CommonModule mdCommonModule = (CommonModule)configurationObject;

        Method mdMethod = MdObjects.getMethod(mdCommonModule.getModule(), "ПриОпределенииОбъектовСКомандамиПечати"); //$NON-NLS-1$
        if (mdMethod == null)
            return;

        for (Statement mdStatement : mdMethod.getStatements())
        {
            DynamicFeatureAccess methodAccess = (DynamicFeatureAccess)((SimpleStatement)mdStatement).getLeft();

            if (methodAccess.getName().equalsIgnoreCase("Версия")) //$NON-NLS-1$
            {
                String version = ((StringLiteral)((SimpleStatement)mdStatement).getRight()).getLines().get(0);
                version = version.substring(1, version.length() - 1);
                break;
            }
        }
    }

    private ValidationObjects()
    {
        throw new IllegalStateException(Messages.Internal_class);
    }
}
