/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Product;
import com.model.Sale;
import com.model.SaleMovements;
import com.persistence.SaleMovementsPersistence;
import com.persistence.SalePersistence;

/**
 *
 * @author Andres Felipe Diaz
 */
@Service
public class SaleServiceImp implements SaleService {

	@Autowired
	SalePersistence salePersistence;

	@Autowired
	SaleMovementsPersistence saleMovementsPersistence;

	@Autowired
	ProductService productService;

	@Override
	public void saveSale(Sale u) {
		salePersistence.save(u);
	}

	@Override
	public void deleteSale(int id) {
		salePersistence.deleteById(id);
	}

	@Override
	public List<Sale> listSale() {
		return salePersistence.findAll();
	}

	@Override
	public Optional<Sale> listSaleId(int id) {
		return salePersistence.findById(id);
	}

	@Override
	public List<SaleMovements> listSaleMovements(int idSale) {
		return saleMovementsPersistence.findAll();
	}

	@Override
	public void saveSaleMovements(Sale s, SaleMovements sm) {
		JSONArray jsonArray = new JSONArray(sm.getName_item());

		for (int i = 0; i < jsonArray.length(); i++) {

			Product pro = new Product();
			try {
				pro.setId(jsonArray.getJSONObject(i).getJSONObject("product").getInt("id"));
			} catch (Exception e) {
				pro.setId(jsonArray.getJSONObject(i).getInt("id"));
			}

			SaleMovements sale_m = new SaleMovements();

			sale_m.setSale(s);
			sale_m.setId(jsonArray.getJSONObject(i).getInt("id"));
			sale_m.setName_item(jsonArray.getJSONObject(i).getString("name"));
			sale_m.setPrice_item(jsonArray.getJSONObject(i).getDouble("price"));
			sale_m.setQuantity_item(jsonArray.getJSONObject(i).getInt("cant"));
			sale_m.setTotal_item(jsonArray.getJSONObject(i).getDouble("total"));
			sale_m.setProduct(pro);

			saleMovementsPersistence.save(sale_m);

			int cant_update = 0;

			try {
				cant_update = jsonArray.getJSONObject(i).getInt("quantity_item");
			} catch (Exception e) {
				cant_update = 0;
			}

			updateStock(pro, sale_m, cant_update);

		}
	}

	@Override
	public void deleteSaleMovements(SaleMovements sm) {
		JSONArray jsonArray = new JSONArray(sm.getDesc_item());

		for (int i = 0; i < jsonArray.length(); i++) {

			Product pro = new Product();
			pro.setId(jsonArray.getJSONObject(i).getJSONObject("product").getInt("id"));

			SaleMovements sale_m = new SaleMovements();
			sale_m.setQuantity_item(0);

			int cant_update = jsonArray.getJSONObject(i).getInt("quantity_item");

			updateStock(pro, sale_m, cant_update);

			saleMovementsPersistence.deleteById(jsonArray.getJSONObject(i).getInt("id"));

		}
	}

	private void updateStock(Product pro, SaleMovements sale_m, int cant_update) {
		Optional<Product> producto = productService.listProductId(pro.getId());
		producto.get().setStock((producto.get().getStock() + cant_update) - sale_m.getQuantity_item());
		productService.saveProduct(producto.get());
	}

}
