package com.lee.homework.model;

import java.io.Serializable;

public class Category implements Serializable {
    private Long id;

    private String catName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }
}