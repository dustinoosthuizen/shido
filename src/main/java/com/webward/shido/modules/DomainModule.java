package com.webward.shido.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.webward.shido.dao.ItemDao;
import com.webward.shido.dao.UserDao;

/**
 * Created by dustinosthzn on 2014/11/30.
 */
public class DomainModule implements Module {

    @Override
    public void configure(Binder binder) {

        binder.bind(ItemDao.class);
        binder.bind(UserDao.class);
    }
}
