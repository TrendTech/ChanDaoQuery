package com.ChanDao.model;

/**
 * Author ajiang
 * Created 2019/11/24 13:07
 */
public class Product {

    public Product(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;
}
