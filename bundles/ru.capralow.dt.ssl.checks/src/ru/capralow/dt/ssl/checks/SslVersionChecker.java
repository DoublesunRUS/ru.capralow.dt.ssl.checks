/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks;

import java.text.MessageFormat;

import com._1c.g5.v8.dt.bsl.model.DynamicFeatureAccess;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.bsl.model.SimpleStatement;
import com._1c.g5.v8.dt.bsl.model.Statement;
import com._1c.g5.v8.dt.bsl.model.StringLiteral;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.metadata.mdclass.CommonModule;
import com._1c.g5.v8.dt.metadata.mdclass.MdObject;

public final class SslVersionChecker
{

    public static boolean checkSslVersion(IV8Project v8Project, String startVersion)
    {
        String sslVersion = getSslVersion(v8Project);

        return compareVersions(startVersion, sslVersion) == 0;
    }

    public static boolean checkSslVersion(IV8Project v8Project, String startVersion, String endVersion)
    {
        String sslVersion = getSslVersion(v8Project);

        return compareVersions(startVersion, sslVersion) != 1 && compareVersions(endVersion, sslVersion) != -1;
    }

    private static Integer compareVersions(String version1, String version2)
    {

        String[] levels1 = version1.split("\\."); //$NON-NLS-1$
        String[] levels2 = version2.split("\\."); //$NON-NLS-1$

        Integer length = Math.min(levels1.length, levels2.length);
        for (Integer i = 0; i < length; i++)
        {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            Integer compare = v1.compareTo(v2);
            if (compare != 0)
            {
                return compare;
            }
        }

        return 0;
    }

    private static String getSslVersion(IV8Project v8Project)
    {
        String version = ""; //$NON-NLS-1$

        MdObject mdObject = MdUtils.getMdObject(
            MessageFormat.format(MdUtils.MD_OBJECT, "ОбщийМодуль", "ОбновлениеИнформационнойБазыБСП"), //$NON-NLS-1$ //$NON-NLS-2$
            v8Project);
        if (mdObject == null)
            return version;

        Module module = ((CommonModule)mdObject).getModule();
        BslModelUtils.customResolveLazyCrossReferences(module.eResource());

        Method method = MdUtils.getMethod(module, "ПриДобавленииПодсистемы"); //$NON-NLS-1$
        if (method == null)
            return version;

        for (Statement statement : method.getStatements())
        {
            DynamicFeatureAccess methodLeftFeatureAccess = (DynamicFeatureAccess)((SimpleStatement)statement).getLeft();

            if (methodLeftFeatureAccess.getName().equalsIgnoreCase("Версия")) //$NON-NLS-1$
            {
                version = ((StringLiteral)((SimpleStatement)statement).getRight()).getLines().get(0);
                version = version.substring(1, version.length() - 1);
                break;
            }
        }

        return version;
    }

    private SslVersionChecker()
    {
        throw new IllegalStateException(Messages.Internal_class);
    }
}
