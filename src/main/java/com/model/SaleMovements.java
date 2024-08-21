/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Andres Felipe Diaz
 */
@Entity
@Table(name = "sale_movements")
public class SaleMovements implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name_item;
    private String desc_item;
    private int quantity_item;
    private double price_item;
    private double total_item;
    @ManyToOne
    @JoinColumn(name = "FK_SaleId")
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "FK_ProductId")
    private Product product;

    public int getId() {
        return id;
    }

    public String getDesc_item() {
        return desc_item;
    }

    public void setDesc_item(String desc_item) {
        this.desc_item = desc_item;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name_item) {
        this.name_item = name_item;
    }

    public int getQuantity_item() {
        return quantity_item;
    }

    public void setQuantity_item(int quantity_item) {
        this.quantity_item = quantity_item;
    }

    public double getPrice_item() {
        return price_item;
    }

    public void setPrice_item(double price_item) {
        this.price_item = price_item;
    }

    public double getTotal_item() {
        return total_item;
    }

    public void setTotal_item(double total_item) {
        this.total_item = total_item;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "SaleMovements{" + "id=" + id + ", name_item=" + name_item + ", quantity_item=" + quantity_item + ", price_item=" + price_item + ", total_item=" + total_item + ", sale=" + sale + ", product=" + product + '}';
    }

}
