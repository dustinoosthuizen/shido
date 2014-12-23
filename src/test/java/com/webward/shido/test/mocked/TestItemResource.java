package com.webward.shido.test.mocked;

import com.webward.shido.dao.ItemDao;
import com.webward.shido.dto.ItemDto;
import com.webward.shido.entities.Item;
import com.webward.shido.resources.ItemResource;
import com.webward.shido.transformer.ItemTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TestItemResource {

    @InjectMocks
    ItemResource itemResource;
    @Mock
    ItemDao itemDao;
    @Spy
    ItemTransformer itemTransformer;
    @Test
    public void testGetItemById()
    {
//        Item item = new Item();
//        item.setName("mocking is great");
//        item.setId(1);
//
//        given(itemDao.find(anyString())).willReturn(item);
//
//        ItemDto anItem = itemResource.getItemById("mocking is great");
//
//        then(anItem.getName()).isEqualTo("mocking is great");

    }

}