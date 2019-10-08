package com.hcl.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.client.model.Product;
@Repository
public interface ClientRepositorys extends JpaRepository<Product,Long> {

}
