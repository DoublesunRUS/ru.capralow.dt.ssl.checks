/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.validator.tests;

import org.eclipse.xtext.diagnostics.Severity;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com._1c.g5.v8.dt.testing.TestingWorkspace;

import ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.validator.ObjectFormModule;

public class ObjectFormModuleReportsTests
{

    @ClassRule
    public static TestingWorkspace testingWorkspace = new TestingWorkspace();

    @BeforeClass
    public static void setUp() throws Exception
    {
        testingWorkspace.setUpProject("v3_1_1", ObjectFormModuleReportsTests.class); //$NON-NLS-1$
    }

    @Test
    public void testAllExecuteCommandAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testAllExecuteCommandAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testAllExecuteCommandAtServerHasExport() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT);
    }

    @Test
    public void testAllExecuteCommandAtServerNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD);
    }

    @Test
    public void testAllExecuteCommandExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testAllExecuteCommandHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testAllExecuteCommandNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD);
    }

    @Test
    public void testAllOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testAllOnCreateAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testAllOnCreateAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testAllOnOpenExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testAllOnOpenHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testAllOnOpenNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL);
    }

    @Test
    public void testAllOnReadAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testAllOnReadAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testAllOnReadAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testAllRereadCommandsExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

    @Test
    public void testAllRereadCommandsHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testAllRereadCommandsNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD);
    }

    @Test
    public void testMissingCallsExecuteCommandAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsExecuteCommandAtServerMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL, Severity.WARNING, 28);
    }

    @Test
    public void testMissingCallsExecuteCommandAtServerNoMissingExport() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT);
    }

    @Test
    public void testMissingCallsExecuteCommandExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsExecuteCommandMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL, Severity.WARNING, 22);
    }

    @Test
    public void testMissingCallsOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsOnCreateAtServerMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL, Severity.WARNING, 3);
    }

    @Test
    public void testMissingCallsOnOpenExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsOnOpenMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_MISSING_CALL, Severity.WARNING, 15);
    }

    @Test
    public void testMissingCallsOnReadAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsOnReadAtServerMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL, Severity.WARNING, 9);
    }

    @Test
    public void testMissingCallsRereadCommandsExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsRereadCommandsMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL, Severity.WARNING, 34);
    }

    @Test
    public void testMissingMethodsExecuteCommandAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsExecuteCommandAtServerNoMissingExport() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT);
    }

    @Test
    public void testMissingMethodsExecuteCommandAtServerNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsExecuteCommandNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsExecuteCommandNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsOnCreateAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsOnCreateAtServerNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsOnOpenNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsOnOpenNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsOnReadAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsOnReadAtServerNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsRereadCommandsNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsRereadCommandsNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", "src/Documents/ОтчетыБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testOld241ExecuteCommandAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testOld241ExecuteCommandAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOld241ExecuteCommandAtServerMissingExport() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT, Severity.WARNING, 39);
    }

    @Test
    public void testOld241ExecuteCommandAtServerNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD);
    }

    @Test
    public void testOld241ExecuteCommandExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testOld241ExecuteCommandHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testOld241ExecuteCommandNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD);
    }

    @Test
    public void testOld241OnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testOld241OnCreateAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOld241OnCreateAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testOld241OnOpenExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testOld241OnOpenHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testOld241OnOpenNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL);
    }

    @Test
    public void testOld241OnReadAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testOld241OnReadAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOld241OnReadAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testOld241RereadCommandsExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

    @Test
    public void testOld241RereadCommandsHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testOld241RereadCommandsNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/ОтчетыВсёНастроено_2_4_1/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModule.ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD);
    }

}
