package com.lee.homework.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private Long id;

    private String menuName;

    private String menuChineseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuChineseName() {
        return menuChineseName;
    }

    public void setMenuChineseName(String menuChineseName) {
        this.menuChineseName = menuChineseName == null ? null : menuChineseName.trim();
    }
}