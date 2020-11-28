/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;

import com._1c.g5.v8.dt.bm.index.emf.IBmEmfIndexManager;
import com._1c.g5.v8.dt.bm.index.emf.IBmEmfIndexProvider;
import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.bsl.model.Module;
import com._1c.g5.v8.dt.core.platform.IConfigurationProject;
import com._1c.g5.v8.dt.core.platform.IExtensionProject;
import com._1c.g5.v8.dt.core.platform.IExternalObjectProject;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.metadata.mdclass.Configuration;
import com._1c.g5.v8.dt.metadata.mdclass.MdClassPackage;
import com._1c.g5.v8.dt.metadata.mdclass.MdObject;

import ru.capralow.dt.ssl.checks.internal.SslPlugin;

public final class MdUtils
{

    public static final String MD_OBJECT = "{0}.{1}"; //$NON-NLS-1$

    public static Configuration getConfigurationForProject(IV8Project v8Project)
    {
        if (v8Project instanceof IConfigurationProject)
            return ((IConfigurationProject)v8Project).getConfiguration();
        else if (v8Project instanceof IExtensionProject)
            return ((IExtensionProject)v8Project).getConfiguration();
        else if (v8Project instanceof IExternalObjectProject)
            return ((IExternalObjectProject)v8Project).getParent().getConfiguration();

        return null;
    }

    private static QualifiedName getConfigurationObjectQualifiedName(String objectFullName, EClass mdLiteral)
    {
        String[] objectArray = objectFullName.substring(objectFullName.indexOf('.') + 1).split("[.]"); //$NON-NLS-1$

        QualifiedName qnObjectName = null;
        for (String objectValue : objectArray)
        {
            if (qnObjectName == null)
                qnObjectName = QualifiedName.create(mdLiteral.getName(), objectValue);

            else
            {
                if (mdLiteral.equals(MdClassPackage.Literals.SUBSYSTEM))
                    qnObjectName = qnObjectName.append(QualifiedName.create(mdLiteral.getName(), objectValue));

                else
                    qnObjectName = qnObjectName.append(QualifiedName.create(objectValue));

            }

        }

        return qnObjectName;
    }

    private static EClass getMdLiteral(String objectFullName)
    {
        EClass mdLiteral = MdClassPackage.Literals.CONFIGURATION;

        String objectType = objectFullName.substring(0, objectFullName.indexOf('.'));

        if (objectType.equals("Подсистема")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.SUBSYSTEM;

        else if (objectType.equals("ОбщийМодуль")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.COMMON_MODULE;

        else if (objectType.equals("ОпределяемыйТип")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.DEFINED_TYPE;

        else if (objectType.equals("Справочник")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.CATALOG;

        else if (objectType.equals("Документ")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.DOCUMENT;

        else if (objectType.equals("Перечисление")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.ENUM;

        else if (objectType.equals("ПланВидовХарактеристик")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.CHART_OF_CHARACTERISTIC_TYPES;

        else if (objectType.equals("ПланВидовРасчета")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.CHART_OF_CALCULATION_TYPES;

        else if (objectType.equals("РегистрСведений")) //$NON-NLS-1$
            mdLiteral = MdClassPackage.Literals.INFORMATION_REGISTER;

        return mdLiteral;
    }

    public static MdObject getMdObject(String objectFullName, IV8Project v8Project)
    {
        IBmEmfIndexManager bmEmfIndexManager =
            SslPlugin.getInstance().getInjector().getInstance(IBmEmfIndexManager.class);
        IBmEmfIndexProvider bmEmfIndexProvider = bmEmfIndexManager.getEmfIndexProvider(v8Project.getProject());

        EClass mdLiteral = getMdLiteral(objectFullName);
        QualifiedName qnObjectName = getConfigurationObjectQualifiedName(objectFullName, mdLiteral);

        MdObject object = null;

        Iterable<IEObjectDescription> objectIndex =
            bmEmfIndexProvider.getEObjectIndexByType(mdLiteral, qnObjectName, true);
        Iterator<IEObjectDescription> objectItr = objectIndex.iterator();
        if (objectItr.hasNext())
            object = (MdObject)objectItr.next().getEObjectOrProxy();

        if (object == null)
            return null;

        return object;
    }

    public static Method getMethod(Module mdModule, String methodName)
    {
        for (Method mdMethod : mdModule.allMethods())
        {
            if (mdMethod.getName().equals(methodName))
                return mdMethod;
        }

        return null;
    }

    private MdUtils()
    {
        throw new IllegalStateException(Messages.Internal_class);
    }
}
