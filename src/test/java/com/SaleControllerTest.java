/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.controller.SaleController;
import com.model.Product;
import com.model.Sale;
import com.model.SaleMovements;
import com.persistence.SaleMovementsPersistence;
import com.service.ProductService;
import com.service.SaleService;
import com.service.UserService;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import org.springframework.ui.Model;

/**
 *
 * @author Andres Felipe Diaz
 */
@RunWith(MockitoJUnitRunner.class)
public class SaleControllerTest {

    @Mock
    private SaleService saleService;

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @Mock
    private SaleMovementsPersistence saleMovementsPersistence;

    @InjectMocks
    private SaleController saleController;

    @Mock
    private Model model;

    @Test
    public void saveUserTest() {

        Product pro = new Product();
        pro.setStock(100);

        Optional<Product> list = Optional.ofNullable(pro);

        when(productService.listProductId(anyInt())).thenReturn(list);

        saleController.save(generateSale(), generateSaleMovements());

        assertEquals(generateSale().getId(), generateSale().getId());
    }

    @Test
    public void listSaleTest() {
        saleController.listSale(model);
        assertEquals(generateSale().getId(), generateSale().getId());
    }

    @Test
    public void updatetSaleTest() {
        saleController.update(model, generateSale().getId());
        assertEquals(generateSale().getId(), generateSale().getId());
    }

    @Test
    public void addSaleTest() {
        saleController.add(model);
        assertEquals(generateSale().getId(), generateSale().getId());
    }

    private Sale generateSale() {
        Sale sale = new Sale();
        sale.setId(5);
        sale.setDescription("Cotización");
        sale.setIva(10000);
        sale.setSub_total(100000);
        sale.setTasa_iva(16);
        sale.setTotal(5000000);
        return sale;
    }

    private SaleMovements generateSaleMovements() {
        SaleMovements sm = new SaleMovements();
        sm.setId(1);
        sm.setName_item("[{\"id\":1,\"name\":\"tv lg\",\"description\":\"Es gama alta\",\"price\":50000,\"stock\":40,\"rateIva\":0,\"total\":50000,\"cant\":1}]");
        sm.setDesc_item("[{\"id\":27,\"name_item\":\"Estufa\",\"quantity_item\":8,\"price_item\":45000,\"total_item\":360000,\"sale\":{\"id\":11,\"name\":\"COT-11\",\"description\":\"Entrga en Cali\",\"tasa_iva\":20,\"sub_total\":420000,\"iva\":84000,\"total\":504000,\"user\":{\"id\":1,\"name\":\"Felipe\",\"lastName\":\"Diaz\",\"userName\":\"felipeDiaz\",\"password\":\"MTIzNDU=\",\"creditCard\":\"23214-4545-6767\"}},\"product\":{\"id\":3,\"name\":\"Estufa\",\"description\":\"Buena\",\"price\":45000,\"stock\":90,\"rateIva\":0},\"name\":\"Estufa\",\"price\":45000,\"total\":360000,\"cant\":8}]");
        sm.setPrice_item(5000);
        sm.setSale(generateSale());
        sm.setQuantity_item(2);
        sm.setTotal_item(10000);
        return sm;
    }

}
