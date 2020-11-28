/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.attachablecommands_v3_1_1;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com._1c.g5.v8.dt.bsl.model.Method;
import com._1c.g5.v8.dt.core.platform.IV8Project;
import com._1c.g5.v8.dt.mcore.TypeItem;
import com._1c.g5.v8.dt.metadata.mdclass.CommonModule;
import com._1c.g5.v8.dt.metadata.mdclass.DefinedType;
import com._1c.g5.v8.dt.metadata.mdclass.MdObject;

import ru.capralow.dt.ssl.checks.BslModelUtils;
import ru.capralow.dt.ssl.checks.MdUtils;
import ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.Messages;

public final class AttacheableObjects
{

    public static void getAdditionalObjects(List<String> objectsList, IV8Project v8Project)
    {
        MdObject mdObject = MdUtils.getMdObject(
            MessageFormat.format(MdUtils.MD_OBJECT, "ОпределяемыйТип", "ОбъектСДополнительнымиКомандами"), v8Project); //$NON-NLS-1$ //$NON-NLS-2$
        if (mdObject == null)
            return;

        for (TypeItem typefromDefinedType : ((DefinedType)mdObject).getType().getTypes())
            objectsList.add(typefromDefinedType.getName());
    }

    public static List<String> getAttacheableObjects(IV8Project v8Project)
    {
        List<String> objectsList = new ArrayList<>();

        getAdditionalObjects(objectsList, v8Project);
        getFillingObjects(objectsList, v8Project);
        getPrintObjects(objectsList, v8Project);
        getReportObjects(objectsList, v8Project);

        return objectsList;
    }

    public static void getFillingObjects(List<String> objectsList, IV8Project v8Project)
    {
        MdObject mdObject = MdUtils.getMdObject(
            MessageFormat.format(MdUtils.MD_OBJECT, "ОбщийМодуль", "ЗаполнениеОбъектовПереопределяемый"), //$NON-NLS-1$ //$NON-NLS-2$
            v8Project);
        if (mdObject == null)
            return;

        CommonModule commonModule = (CommonModule)mdObject;

        Method method = MdUtils.getMethod(commonModule.getModule(), "ПриОпределенииОбъектовСКомандамиЗаполнения"); //$NON-NLS-1$
        if (method == null)
            return;

        BslModelUtils.parseStatements(method, objectsList, v8Project);
    }

    public static void getPrintObjects(List<String> objectsList, IV8Project v8Project)
    {
        MdObject mdObject = MdUtils.getMdObject(
            MessageFormat.format(MdUtils.MD_OBJECT, "ОбщийМодуль", "УправлениеПечатьюПереопределяемый"), //$NON-NLS-1$ //$NON-NLS-2$
            v8Project);
        if (mdObject == null)
            return;

        CommonModule commonModule = (CommonModule)mdObject;

        Method method = MdUtils.getMethod(commonModule.getModule(), "ПриОпределенииОбъектовСКомандамиПечати"); //$NON-NLS-1$
        if (method == null)
            return;

        BslModelUtils.parseStatements(method, objectsList, v8Project);
    }

    public static void getReportObjects(List<String> objectsList, IV8Project v8Project)
    {
        MdObject mdObject = MdUtils.getMdObject(
            MessageFormat.format(MdUtils.MD_OBJECT, "ОбщийМодуль", "ВариантыОтчетовПереопределяемый"), //$NON-NLS-1$ //$NON-NLS-2$
            v8Project);
        if (mdObject == null)
            return;

        CommonModule commonModule = (CommonModule)mdObject;

        Method method = MdUtils.getMethod(commonModule.getModule(), "ОпределитьОбъектыСКомандамиОтчетов"); //$NON-NLS-1$
        if (method == null)
            return;

        BslModelUtils.parseStatements(method, objectsList, v8Project);
    }

    private AttacheableObjects()
    {
        throw new IllegalStateException(Messages.Internal_class);
    }
}
