package com.ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
