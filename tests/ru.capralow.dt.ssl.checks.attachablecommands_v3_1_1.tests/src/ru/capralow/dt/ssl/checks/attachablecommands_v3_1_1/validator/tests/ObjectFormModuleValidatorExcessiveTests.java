/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.attachablecommands_v3_1_1.validator.tests;

import org.eclipse.xtext.diagnostics.Severity;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com._1c.g5.v8.dt.testing.TestingWorkspace;

import ru.capralow.dt.ssl.checks.attachablecommands_v3_1_1.validator.ObjectFormModuleValidator;

public class ObjectFormModuleValidatorExcessiveTests
{

    @ClassRule
    public static TestingWorkspace testingWorkspace = new TestingWorkspace();

    @BeforeClass
    public static void setUp() throws Exception
    {
        testingWorkspace.setUpProject("v3_1_1", ObjectFormModuleValidatorExcessiveTests.class); //$NON-NLS-1$
    }

    @Test
    public void testExecuteCommandAtServerExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD, Severity.WARNING, 39);
    }

    @Test
    public void testExecuteCommandAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testExecuteCommandAtServerNoMissingExport() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_EXPORT);
    }

    @Test
    public void testExecuteCommandAtServerNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testExecuteCommandExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD, Severity.WARNING, 31);
    }

    @Test
    public void testExecuteCommandNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testExecuteCommandNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testOnCreateAtServerExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL, Severity.WARNING, 5);
    }

    @Test
    public void testOnCreateAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOnCreateAtServerNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testOnOpenExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL, Severity.WARNING, 23);
    }

    @Test
    public void testOnOpenNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testOnOpenNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testOnReadAtServerExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL, Severity.WARNING, 14);
    }

    @Test
    public void testOnReadAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOnReadAtServerNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testRereadCommandsExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD, Severity.WARNING, 47);
    }

    @Test
    public void testRereadCommandsNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testRereadCommandsNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v3_1_1", //$NON-NLS-1$
            "src/Documents/НенужныеПроцедурыИВызовы/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

}
