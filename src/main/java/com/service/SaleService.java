/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Sale;
import com.model.SaleMovements;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres Felipe Diaz
 */
public interface SaleService {

    void saveSale(Sale s);

    void deleteSale(int id);

    List<Sale> listSale();

    public Optional<Sale> listSaleId(int id);
    
    public List<SaleMovements> listSaleMovements(int idSale);
    
    void saveSaleMovements(Sale s, SaleMovements sm);
    
    void deleteSaleMovements(SaleMovements sm);

}
