package com.classes;

import javax.websocket.Decoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author antonios
 */
public class Product {

    private int id;
    private String name;
    private String desc;
    private float price;
    private int quantity;
    private int categoryID;
    private String image; //hello

    public Product(int id, String name, String desc, float price, int quantity, int categoryID, String image) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.image = image;
    }

    public Product(int id, String name, String desc, float price, int quantity, int categoryID) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getImage() {
        return image;
    }
}
