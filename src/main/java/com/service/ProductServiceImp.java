/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Product;
import com.persistence.ProductPersistence;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres Felipe Diaz
 */
@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductPersistence productPersistence;

    @Override
    public void saveProduct(Product p) {
        productPersistence.save(p);
    }

    @Override
    public void deleteProduct(int id) {
        productPersistence.deleteById(id);
    }

    @Override
    public List<Product> listProduct() {
        return productPersistence.findAll();
    }

    @Override
    public Optional<Product> listProductId(int id) {
        return productPersistence.findById(id);
    }

}
