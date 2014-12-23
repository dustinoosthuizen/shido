package com.webward.shido.listeners;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.webward.shido.modules.MongoDbModule;
import com.webward.shido.modules.ResourcesModule;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/11/29.
 */
public class CustomServletContextListener extends GuiceResteasyBootstrapServletContextListener
{

    @Override
    protected List<Module> getModules(ServletContext context)
    {
        List<Module> modules = new ArrayList<>();
//        modules.add(new JpaPersistModule("test1234"));
            modules.add(new MongoDbModule());
        modules.add(new ResourcesModule());
        return modules;
    }

    @Override
    public void withInjector(Injector injector)
    {

//        injector.getInstance(PersistService.class).start();
    }
}
