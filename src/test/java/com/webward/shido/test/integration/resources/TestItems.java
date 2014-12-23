package com.webward.shido.test.integration.resources;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.webward.shido.dto.ItemDto;
import com.webward.shido.modules.MainModule;
import com.webward.shido.test.integration.listener.TestServletContextListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by dustinosthzn on 2014/11/30.
 */
public class TestItems {

    private  static Server server;

    @BeforeClass
    public  static void before() throws Exception
    {
        Injector injector = Guice.createInjector(new MainModule());
        injector.getAllBindings();
        injector.createChildInjector().getAllBindings();

        server = new Server(9090);
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addEventListener(injector.getInstance(TestServletContextListener.class));

        ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
        servletHandler.addServlet(sh, "/*");

        server.setHandler(servletHandler);
        server.start();
    }

    @Test
    public void testCrud()
    {
        Client client = ClientBuilder.newBuilder().build();
        create(client);
        ItemDto itemDto = read(client);
        Assert.assertTrue(itemDto.getName().equals("TestName"));
        delete(client, itemDto);
        itemDto = read(client);
        Assert.assertNull(itemDto);
        testAsyncGet( client);
    }

    public void create(Client client)
    {
        System.out.println("create");
        ItemDto item = new ItemDto();
        item.setName("TestName");

        WebTarget webTarget = client.target(getBaseURI()).path("items").path("create");
        ItemDto itemDto = webTarget.request().post(Entity.entity(item, MediaType.APPLICATION_JSON),
                ItemDto.class);

    }

    public ItemDto read(Client client)
    {
        WebTarget target = client.target(getBaseURI()).path("items").path("itemByName").path("TestName");
        Response response = target.request().get();
        ItemDto value = response.readEntity(ItemDto.class);
        return value;

    }


    public void delete(Client client,ItemDto itemDto)
    {
        WebTarget target = client.target(getBaseURI()).path("items").path("deleteById").path(""+itemDto.getId());
        Response response = target.request().delete();
        System.out.println(response);
    }

    public void testAsyncGet(Client client)
    {

        Future<Response> fb = client
                .target(getBaseURI()).path("items").path("basic")
                .request()
                .async().get();
        System.out.println("blocks from here "+ LocalDateTime.now());
        try {
            Response response = fb.get();
            String value = response.readEntity(String.class);
            Assert.assertTrue(value.equals("basic"));
        }catch(InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        System.out.println("Done blocking from here "+ LocalDateTime.now());
    }


    @AfterClass
    public static void after() throws Exception
    {
        System.out.println("shutting down server");
        server.stop();
        System.out.println("test server stopped");

    }

    public String getBaseURI()
    {
        return "http://localhost:9090";
    }
}
