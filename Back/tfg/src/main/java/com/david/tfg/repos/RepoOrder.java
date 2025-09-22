package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.Order;

public interface RepoOrder  extends JpaRepository <Order, Integer>{

}
