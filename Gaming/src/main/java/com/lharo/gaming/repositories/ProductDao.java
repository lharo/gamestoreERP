package com.lharo.gaming.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lharo.gaming.models.Producto;

public interface ProductDao extends CrudRepository<Producto, Integer>  {

}
