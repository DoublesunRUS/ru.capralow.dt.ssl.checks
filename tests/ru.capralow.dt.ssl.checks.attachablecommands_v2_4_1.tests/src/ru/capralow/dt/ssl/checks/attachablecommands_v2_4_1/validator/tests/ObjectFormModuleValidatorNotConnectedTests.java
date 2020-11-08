/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.attachablecommands_v2_4_1.validator.tests;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com._1c.g5.v8.dt.testing.TestingWorkspace;

import ru.capralow.dt.ssl.checks.attachablecommands_v2_4_1.validator.ObjectFormModuleValidator;

public class ObjectFormModuleValidatorNotConnectedTests
{

    @ClassRule
    public static TestingWorkspace testingWorkspace = new TestingWorkspace();

    @BeforeClass
    public static void setUp() throws Exception
    {
        testingWorkspace.setUpProject("v2_4_1", ObjectFormModuleValidatorNotConnectedTests.class); //$NON-NLS-1$
    }

    @Test
    public void testExecuteCommandAtServerNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_EXCESSIVE_METHOD);
    }

    @Test
    public void testExecuteCommandAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testExecuteCommandAtServerNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testExecuteCommandNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_EXCESSIVE_METHOD);
    }

    @Test
    public void testExecuteCommandNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_MISSING_CALL);
    }

    @Test
    public void testExecuteCommandNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_EXECUTE_COMMAND_NOT_EXISTS);
    }

    @Test
    public void testOnCreateAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testOnCreateAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOnCreateAtServerNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_CREATE_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testOnOpenNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_EXCESSIVE_CALL);
    }

    @Test
    public void testOnOpenNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_MISSING_CALL);
    }

    @Test
    public void testOnOpenNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_OPEN_NOT_EXISTS);
    }

    @Test
    public void testOnReadAtServerNoExcessiveCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_EXCESSIVE_CALL);
    }

    @Test
    public void testOnReadAtServerNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_MISSING_CALL);
    }

    @Test
    public void testOnReadAtServerNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_ON_READ_AT_SERVER_NOT_EXISTS);
    }

    @Test
    public void testRereadCommandsNoExcessiveMethod() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_EXCESSIVE_METHOD);
    }

    @Test
    public void testRereadCommandsNoMissingCall() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_MISSING_CALL);
    }

    @Test
    public void testRereadCommandsNoNotExists() throws Exception
    {
        TestingWorkspaceUtils.checkNoIssue("v2_4_1", "src/Documents/НеПодключен/Forms/ФормаДокумента/Module.bsl", //$NON-NLS-1$//$NON-NLS-2$
            ObjectFormModuleValidator.ERROR_METHOD_REREAD_COMMANDS_NOT_EXISTS);
    }

}
