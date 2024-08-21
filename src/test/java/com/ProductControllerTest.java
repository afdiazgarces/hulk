/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.controller.ProductController;
import com.model.Product;
import com.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import org.springframework.ui.Model;

/**
 *
 * @author Andres Felipe Diaz
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @Mock
    private ProductService productService;

    @Test
    public void saveUserTest() {
        productController.save(generateProduct());
        assertEquals(generateProduct().getId(), generateProduct().getId());
    }

    @Test
    public void listUserTest() {
        productController.listProduct(model);
        assertEquals(generateProduct().getId(), generateProduct().getId());
    }

    @Test
    public void updatetUserTest() {
        productController.update(model, generateProduct().getId());
        assertEquals(generateProduct().getId(), generateProduct().getId());
    }

    @Test
    public void addUserTest() {
        productController.add(model);
        assertEquals(generateProduct().getId(), generateProduct().getId());
    }

    @Test
    public void deleteUserTest() {
        productController.delete(generateProduct().getId());
        assertEquals(generateProduct().getId(), generateProduct().getId());
    }

    private Product generateProduct() {
        Product pro = new Product();
        pro.setId(5);
        pro.setName("Celular");
        pro.setPrice(50000);
        pro.setStock(50);
        return pro;

    }

}
