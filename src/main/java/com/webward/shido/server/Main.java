package com.webward.shido.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.webward.shido.listeners.CustomServletContextListener;
import com.webward.shido.jaxrs.ClientErrorExceptionMapper;
import com.webward.shido.modules.MainModule;
import com.webward.shido.modules.MongoDbModule;
import com.webward.shido.modules.PojoModule;
import com.webward.shido.modules.ResourcesModule;
import com.webward.shido.utils.DirectoryResolver;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class Main {

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new MainModule(),new ResourcesModule(),new PojoModule(),new MongoDbModule());
        injector.getAllBindings();
        injector.createChildInjector().getAllBindings();

        Server server = new Server(8080);
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addEventListener(injector.getInstance(GuiceResteasyBootstrapServletContextListener.class));

        ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
        servletHandler.addServlet(sh, "/*");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[] { "index.html" });
        resourceHandler.setResourceBase(DirectoryResolver.resolve(".","webapp"));
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resourceHandler, servletHandler });

        server.setHandler(handlers);

        server.start();
        server.join();
    }


}
