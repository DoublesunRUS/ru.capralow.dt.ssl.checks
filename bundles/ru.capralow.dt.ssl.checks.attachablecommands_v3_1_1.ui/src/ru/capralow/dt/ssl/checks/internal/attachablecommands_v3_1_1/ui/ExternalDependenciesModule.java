/**
 * Copyright (c) 2020, Alexander Kapralov
 */
package ru.capralow.dt.ssl.checks.internal.attachablecommands_v3_1_1.ui;

import org.eclipse.core.runtime.Plugin;

import com._1c.g5.wiring.AbstractServiceAwareModule;

public class ExternalDependenciesModule
    extends AbstractServiceAwareModule
{

    public ExternalDependenciesModule(Plugin bundle)
    {
        super(bundle);
    }

    @Override
    protected void doConfigure()
    {
        // Пока нет сервисов
    }

}
