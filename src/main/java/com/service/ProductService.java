/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres Felipe Diaz
 */
public interface ProductService {

    void saveProduct(Product p);

    void deleteProduct(int id);

    List<Product> listProduct();
    
    public Optional<Product> listProductId(int id);

    
}
