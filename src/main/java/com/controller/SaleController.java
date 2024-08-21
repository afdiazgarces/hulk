/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.model.Sale;
import com.model.SaleMovements;
import com.persistence.SaleMovementsPersistence;
import com.service.ProductService;
import com.service.SaleService;
import com.service.UserService;

/**
 *
 * @author Andres Felipe Diaz
 */
@Controller
public class SaleController {

	@Autowired
	SaleService saleService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	SaleMovementsPersistence saleMovementsPersistence;

	@GetMapping("/listSale")
	public String listSale(Model model) {
		model.addAttribute("sales", saleService.listSale());
		return "listSale";
	}

	@GetMapping("/newSale")
	public String add(Model model) {
		model.addAttribute("sale", new Sale());
		model.addAttribute("sm", new SaleMovements());
		model.addAttribute("products", productService.listProduct());
		model.addAttribute("users", userService.listUser());
		return "formSale";
	}

	@PostMapping("/saveSale")
	public String save(@Validated Sale s, @Validated SaleMovements sm) {

		saleService.saveSale(s);

		s.setName("COT-" + s.getId());

		saleService.saveSale(s);

		saleService.saveSaleMovements(s, sm);

		saleService.deleteSaleMovements(sm);

		return "redirect:/listSale";
	}

	@GetMapping("/updateSale/{id}")
	public String update(Model model, @PathVariable int id) {

		List<SaleMovements> sale_movements = new ArrayList<>();

		saleService.listSaleMovements(id).stream().filter((saleMovements) -> (saleMovements.getSale().getId() == id))
				.forEachOrdered((saleMovements) -> {
					sale_movements.add(saleMovements);
				});

		String json_sale_movements = new Gson().toJson(sale_movements);

		SaleMovements sm = new SaleMovements();
		sm.setName_item(json_sale_movements);

		model.addAttribute("sale", saleService.listSaleId(id));
		model.addAttribute("sm", sm);
		model.addAttribute("products", productService.listProduct());
		model.addAttribute("users", userService.listUser());

		return "formSale";

	}

	@GetMapping("/detailSale/{id}")
	public String detail(@PathVariable int id, Model model) {

		List<SaleMovements> sale_movements = new ArrayList<>();

		saleService.listSaleMovements(id).stream().filter((saleMovements) -> (saleMovements.getSale().getId() == id))
				.forEachOrdered((saleMovements) -> {
					sale_movements.add(saleMovements);
				});

		model.addAttribute("sale", saleService.listSaleId(id).get());
		model.addAttribute("sale_movements", sale_movements);
		return "detailSale";

	}
}
