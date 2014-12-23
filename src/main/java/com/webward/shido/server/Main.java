package com.webward.shido.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.webward.shido.listeners.CustomServletContextListener;
import com.webward.shido.jaxrs.ClientErrorExceptionMapper;
import com.webward.shido.modules.MainModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class Main {

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new MainModule());
        injector.getAllBindings();
        injector.createChildInjector().getAllBindings();

        Server server = new Server(8080);
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addEventListener(injector.getInstance(CustomServletContextListener.class));

        ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
        servletHandler.addServlet(sh, "/*");

        server.setHandler(servletHandler);
        server.start();
        server.join();
    }


}
