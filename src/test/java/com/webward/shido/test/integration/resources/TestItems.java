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

    public void clean(Client client)
    {
        ItemDto itemDto = read(client);
        if(itemDto!=null)
        {
            delete(client,itemDto);
        }
    }

    public void create(Client client)
    {
        ItemDto item = new ItemDto();
        item.setName("TestName");

        WebTarget webTarget = client.target(TestResources.baseUri).path("item").path("create");
        ItemDto itemDto = webTarget.request().post(Entity.entity(item, MediaType.APPLICATION_JSON),
                ItemDto.class);

    }

    public ItemDto read(Client client)
    {
        WebTarget target = client.target(TestResources.baseUri).path("item").path("getByName").path("TestName");
        Response response = target.request().get();
        if(response.getStatus()==204)
        {
            return null;

        }else {
            ItemDto value = response.readEntity(ItemDto.class);
            return value;
        }
    }


    public void delete(Client client,ItemDto itemDto)
    {
        WebTarget target = client.target(TestResources.baseUri).path("item").path("deleteById").path(""+itemDto.getId());
        Response response = target.request().delete();
        System.out.println(response);
    }

    public void testAsyncGet(Client client)
    {

        Future<Response> fb = client
                .target(TestResources.baseUri).path("item").path("basic")
                .request()
                .async().get();
        try {
            Response response = fb.get();
            String value = response.readEntity(String.class);
            Assert.assertTrue(value.equals("basic"));
        }catch(InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }


}
