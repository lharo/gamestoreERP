package com.lharo.gaming.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lharo.gaming.models.Producto;
import com.lharo.gaming.repositories.ProductDao;

@Controller
@RequestMapping(path="/product", method = RequestMethod.POST)
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping(path="/add")
	public @ResponseBody Boolean addNewUser (@RequestParam String nombre
			, @RequestParam String descripcion, @RequestParam double precio) {

		Producto p = new Producto();
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		productDao.save(p);
		return true;
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Producto> getAllProducts() {
		return productDao.findAll();
	}
	
	@GetMapping(path="/delete")
	public @ResponseBody Boolean deleteProduct(@RequestParam Integer idProducto) {
		Optional<Producto> p = productDao.findById(idProducto);
		if(p.isPresent()) {
			productDao.deleteById(idProducto);
			return true;
		}
		return false;
	}
	
	@GetMapping(path="/update")
	public @ResponseBody Boolean updateProduct(@RequestParam Integer idProducto, 
			@RequestParam String nombre, @RequestParam String descripcion, 
			@RequestParam double precio) {
		Optional<Producto> p = productDao.findById(idProducto);
		if(p.isPresent()) {
			Producto pro = p.get();
			pro.setNombre(nombre);
			pro.setDescripcion(descripcion);
			pro.setPrecio(precio);
			productDao.save(pro);
			return true;
		}
		return false;
	}
}
