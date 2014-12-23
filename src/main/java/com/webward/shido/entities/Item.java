package com.webward.shido.entities;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by dustinosthzn on 2014/11/09.
 */
@Entity
public class Item {
    @Id
    private ObjectId id;
    private String name;


    public Item()
    {


    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
