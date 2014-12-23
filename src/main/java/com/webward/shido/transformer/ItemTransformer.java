package com.webward.shido.transformer;

import com.webward.shido.dto.ItemDto;
import com.webward.shido.entities.Item;
import org.bson.types.ObjectId;

/**
 * Created by dustinosthzn on 2014/11/16.
 */
public class ItemTransformer {

    public Item fromDto(ItemDto itemDTO)
    {
        if(itemDTO==null)
        {
            return null;
        }
        Item item = new Item();
        item.setName(itemDTO.getName());
        if(itemDTO.getId()!=null)
        {

            item.setId(new ObjectId(itemDTO.getId()));

        }
        return item;
    }


    public ItemDto toDto(Item item)
    {
        if(item==null)
        {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        itemDto.setId(item.getId().toString());
        return itemDto;
    }
}
