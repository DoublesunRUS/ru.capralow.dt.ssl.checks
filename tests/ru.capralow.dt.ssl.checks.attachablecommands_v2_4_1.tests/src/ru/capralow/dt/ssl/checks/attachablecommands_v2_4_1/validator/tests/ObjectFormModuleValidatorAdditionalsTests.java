/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.attachablecommands_v2_4_1.validator.tests;

import org.eclipse.xtext.diagnostics.Severity;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com._1c.g5.v8.dt.testing.TestingWorkspace;

import ru.capralow.dt.ssl.checks.attachablecommands_v2_4_1.validator.ObjectFormModuleValidator;

public class ObjectFormModuleValidatorAdditionalsTests
{

    @ClassRule
    public static TestingWorkspace testingWorkspace = new TestingWorkspace();

    @BeforeClass
    public static void setUp() throws Exception
    {
        testingWorkspace.setUpProject("v2_4_1", ObjectFormModuleValidatorAdditionalsTests.class); //$NON-NLS-1$
    }

    @Test
    public void testAllExecuteCommandAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testAllExecuteCommandAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testAllExecuteCommandAtServerNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD);
    }

    @Test
    public void testAllExecuteCommandExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testAllExecuteCommandHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testAllExecuteCommandNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD);
    }

    @Test
    public void testAllOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testAllOnCreateAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testAllOnCreateAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testAllOnOpenExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testAllOnOpenHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testAllOnOpenNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL);
    }

    @Test
    public void testAllOnReadAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testAllOnReadAtServerHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testAllOnReadAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testAllRereadCommandsExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

    @Test
    public void testAllRereadCommandsHasCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testAllRereadCommandsNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", //$NON-NLS-1$
            "src/Documents/ВнешниеВсёНастроено/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD);
    }

    @Test
    public void testMissingCallsExecuteCommandAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsExecuteCommandAtServerMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL, Severity.WARNING, 28);
    }

    @Test
    public void testMissingCallsExecuteCommandExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsExecuteCommandMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL, Severity.WARNING, 22);
    }

    @Test
    public void testMissingCallsOnCreateAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsOnCreateAtServerMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL, Severity.WARNING, 3);
    }

    @Test
    public void testMissingCallsOnOpenExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsOnOpenMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_MISSING_CALL, Severity.WARNING, 15);
    }

    @Test
    public void testMissingCallsOnReadAtServerExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsOnReadAtServerMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL, Severity.WARNING, 9);
    }

    @Test
    public void testMissingCallsRereadCommandsExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

    @Test
    public void testMissingCallsRereadCommandsMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезВызовов/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL, Severity.WARNING, 34);
    }

    @Test
    public void testMissingMethodsExecuteCommandAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsExecuteCommandAtServerNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsExecuteCommandNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsExecuteCommandNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsOnCreateAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsOnCreateAtServerNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsOnOpenNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsOnOpenNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsOnReadAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsOnReadAtServerNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS, Severity.WARNING, 1);
    }

    @Test
    public void testMissingMethodsRereadCommandsNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testMissingMethodsRereadCommandsNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v2_4_1", "src/Documents/ВнешниеБезПроцедур/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS, Severity.WARNING, 1);
    }

}
