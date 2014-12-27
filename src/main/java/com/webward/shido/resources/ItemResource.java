package com.webward.shido.resources;


import com.google.inject.persist.Transactional;
import com.webward.shido.dao.ItemDao;
import com.webward.shido.dto.ItemDto;
import com.webward.shido.entities.Item;
import com.webward.shido.transformer.ItemTransformer;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/item")
@Singleton
public class ItemResource {

    @Inject
    ItemTransformer itemTransformer;
    @Inject
    ItemDao itemDao;

    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto getItemById(@PathParam("id") String id) {
        return itemTransformer.toDto(itemDao.findOne("id",new ObjectId(id)));
    }

    @GET
    @Path("/getByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto getItemByName(@PathParam("name") String aName) {
        return itemTransformer.toDto(itemDao.findOne("name",aName));
    }


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ItemDto createItem(ItemDto itemDto)
    {
        Item item = itemTransformer.fromDto(itemDto);
        itemDao.save(item);
        return itemTransformer.toDto(item);
    }

    @DELETE
    @Path("/deleteById/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") String id)
    {
        itemDao.deleteById(new ObjectId(id));
        return Response.status(204).build();
    }


    @GET
    @Path("basic")
    @Produces("text/plain")
    public void getBasic(final @Suspend(10000) AsynchronousResponse response) throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Response jaxrs = Response.ok("basic").type(MediaType.TEXT_PLAIN).build();
                    response.setResponse(jaxrs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }



}
