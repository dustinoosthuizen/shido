package com.webward.shido.dto;

import javax.persistence.*;

/**
 * Created by dustinosthzn on 2014/11/29.
 */
public class ItemDto {


    private String id;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
