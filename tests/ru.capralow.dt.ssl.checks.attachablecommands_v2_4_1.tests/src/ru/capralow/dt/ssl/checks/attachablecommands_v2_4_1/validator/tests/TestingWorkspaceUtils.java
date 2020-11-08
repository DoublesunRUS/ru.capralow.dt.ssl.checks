/**
 *
 */
package ru.capralow.dt.ssl.checks.attachablecommands_v2_4_1.validator.tests;

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

import com._1c.g5.v8.dt.bsl.resource.BslResource;
import com._1c.g5.v8.dt.core.handle.IV8File;
import com._1c.g5.v8.dt.core.handle.impl.V8ModelManager;
import com._1c.g5.v8.dt.core.handle.impl.V8XtextFile;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class TestingWorkspaceUtils
{

    public static void checkIssue(String projectName, String fileName, final String issueCode, Severity severity,
        int lineNumber) throws Exception
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
        Assert.assertEquals("Количество проблем не совпадает", 1, issues.size());
        Issue issue = issues.iterator().next();
        Assert.assertEquals("Серьезность проблемы не совпадает", severity, issue.getSeverity());
        Assert.assertEquals("Номер строки с проблемой не совпадает", lineNumber, (int)issue.getLineNumber());
    }

    public static void checkNoIssue(String projectName, String fileName, final String issueCode) throws Exception
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
        Assert.assertEquals("Количество проблем не совпадает", 0, issues.size());
    }

    private static List<Issue> validate(String projectName, String fileName) throws Exception
    {
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
