package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.Service;

public interface RepoService extends JpaRepository <Service, Integer>{

}
