/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.core.dataexchange.tests;

import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com._1c.g5.v8.dt.bsl.resource.BslResource;
import com._1c.g5.v8.dt.core.handle.IV8File;
import com._1c.g5.v8.dt.core.handle.impl.V8ModelManager;
import com._1c.g5.v8.dt.core.handle.impl.V8XtextFile;
import com._1c.g5.v8.dt.testing.TestingWorkspace;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import ru.capralow.dt.ssl.checks.internal.core.dataexchange.ExchangePlanContents;

public class ExchangePlanContentsTests
{
    @Rule
    public TestingWorkspace testingWorkspace = new TestingWorkspace();

    @Test
    public void testExchangePlanContents() throws Exception
    {
        check("test", "src/Configuration/ManagedApplicationModule.bsl", Severity.WARNING, //$NON-NLS-1$//$NON-NLS-2$
            ExchangePlanContents.ERROR_CODE, 1);
    }

    private void check(String projectName, String fileName, Severity severity, final String issueCode, int lineNumber)
        throws Exception
    {
        Collection<Issue> issues = validate(projectName, fileName);

        issues = Collections2.filter(issues, new Predicate<Issue>()
        {
            @Override
            public boolean apply(Issue input)
            {
                return issueCode.equals(input.getCode());
            }
        });
        Assert.assertEquals(1, issues.size());
        Issue issue = issues.iterator().next();
        Assert.assertEquals(severity, issue.getSeverity());
        Assert.assertEquals(lineNumber, (int)issue.getLineNumber());
    }

    private List<Issue> validate(String projectName, String fileName) throws Exception
    {
        testingWorkspace.setUpProject(projectName, getClass());

        IV8File file = V8ModelManager.INSTANCE.getV8Model().getV8Project(projectName).getV8File(fileName);
        Assert.assertTrue(file instanceof V8XtextFile);
        V8XtextFile xtextFile = (V8XtextFile)file;
        List<Issue> issues = xtextFile.readOnly(new IUnitOfWork<List<Issue>, XtextResource>()
        {
            @Override
            public List<Issue> exec(XtextResource state) throws Exception
            {

                Assert.assertTrue(state instanceof BslResource);
                BslResource resource = (BslResource)state;
                resource.setDeepAnalysis(true);

                IResourceServiceProvider provider = resource.getResourceServiceProvider();
                IResourceValidator validator = provider.get(IResourceValidator.class);
                Assert.assertNotNull(validator);

                EcoreUtil2.resolveLazyCrossReferences(resource, null);

                return validator.validate(resource, CheckMode.ALL, null);
            }
        });

        return issues;
    }
}
