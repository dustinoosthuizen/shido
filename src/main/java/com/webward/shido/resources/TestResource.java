package com.webward.shido.resources;

import com.google.inject.Inject;
import com.webward.shido.dto.ItemDto;
import com.webward.shido.utils.TestGuiceUtil;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dustinosthzn on 2014/12/27.
 */
@Path("/test")
@Singleton
public class TestResource {

    @Inject
    private TestGuiceUtil testGuiceUtil;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto getItemById() {
        ItemDto itemDto = new ItemDto();
        itemDto.setName(testGuiceUtil.aValue());
        itemDto.setId("id");
        return itemDto;
    }

    @GET
    @Path("/get2")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto getItemById2() {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("ddddd2");
        itemDto.setId("id2");
        return itemDto;
    }

}
