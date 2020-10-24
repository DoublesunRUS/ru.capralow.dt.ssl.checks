/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator.tests;

import org.eclipse.xtext.diagnostics.Severity;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com._1c.g5.v8.dt.testing.TestingWorkspace;

import ru.capralow.dt.ssl.checks.internal.attachablecommands_v2_4_1.validator.ObjectFormModule;

public class ObjectFormModuleTests
{

    @ClassRule
    public static TestingWorkspace testingWorkspace = new TestingWorkspace();

    @BeforeClass
    public static void setUp() throws Exception
    {
        testingWorkspace.setUpProject("v2_4_1", ObjectFormModuleTests.class); //$NON-NLS-1$
    }

    @Test
    public void testAdditionalsObjectFormModuleOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.check("v2_4_1", "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            Severity.WARNING, ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS, 0);
    }

    @Test
    public void testFillingsObjectFormModuleOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.check("v2_4_1", "src/Documents/ЗаполнениеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            Severity.WARNING, ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS, 0);
    }

    @Test
    public void testPrintsObjectFormModuleOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.check("v2_4_1", "src/Documents/ПечатьВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            Severity.WARNING, ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS, 0);
    }

    @Test
    public void testReportsObjectFormModuleOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.check("v2_4_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            Severity.WARNING, ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_EXISTS, 0);
    }

}
