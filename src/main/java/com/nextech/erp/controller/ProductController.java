package com.nextech.erp.controller;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.erp.model.Product;
import com.nextech.erp.service.ProductService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProduct(
			@Valid @RequestBody Product product, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			productService.addProduct(product);
			return new UserStatus(1, "Product added Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Product getProduct(@PathVariable("id") long id) {
		Product product = null;
		try {
			product = productService.getProductById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProduct(@RequestBody Product product) {
		try {
			productService.updateProduct(product);
			return new UserStatus(1, "Product update Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Product> getProduct() {

		List<Product> ProductList = null;
		try {
			ProductList = productService.getProductist();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ProductList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProduct(@PathVariable("id") long id) {

		try {
			Product product = productService.getProductById(id);
			product.setIsactive(false);
			productService.updateProduct(product);
			return new UserStatus(1, "Product deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
