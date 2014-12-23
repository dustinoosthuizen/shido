package com.webward.shido.dao;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.webward.shido.entities.Item;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/11/29.
 */
public class ItemDao extends BasicDAO<Item, ObjectId> {

    @Inject
    public ItemDao(@Named(value = "shido-ds") Morphia morphia, @Named(value = "shido-ds")MongoClient mongo ) {
        super(mongo, morphia, "shido");
    }


}
