package com.webward.shido.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.webward.shido.resources.ItemResource;

/**
 * Created by dustinosthzn on 2014/11/29.
 */
public class ResourcesModule implements Module{

    @Override
    public void configure(Binder binder) {
        binder.bind(ItemResource.class);



    }
}
