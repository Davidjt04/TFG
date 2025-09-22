package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.OrderDetail;

public interface RepoOrderDetail extends JpaRepository <OrderDetail, Integer>{

}
