package com.webward.shido.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.webward.shido.transformer.ItemTransformer;

/**
 * Created by dustinosthzn on 2014/11/30.
 */
public class PojoModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ItemTransformer.class);



    }
}
