package com.webward.shido.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.webward.shido.dao.ItemDao;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by dustinosthzn on 2014/12/23.
 */
public class MongoDbModule implements Module {

    @Override
    public void configure(Binder binder) {
        MongoClient mongo = null;
        Morphia morphia = new Morphia();
        MongoCredential mongoCredential = MongoCredential.createMongoCRCredential("shido","shido","password".toCharArray());
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Datastore ds = morphia.createDatastore(mongo, "shido");

        binder.bind(MongoClient.class).annotatedWith(Names.named("shido-ds")).toInstance(mongo);
        binder.bind(Morphia.class).annotatedWith(Names.named("shido-ds")).toInstance(morphia);
        binder.bind(Datastore.class).annotatedWith(Names.named("shido-ds")).toInstance(ds);
    }

}
