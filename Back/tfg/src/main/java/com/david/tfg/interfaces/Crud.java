package com.david.tfg.interfaces;

import java.util.List;
import java.util.Optional;

public interface Crud<T,ID>{
    //guardar
    public void save(T entity);
    //buscar por id
    public Optional<T> findById(ID id);
    //mirar si existe
    public boolean existsById(ID id);
    //buscar todos
    public List<T> findAll();
    //borrar por id 
    public void deleteById(ID id) ;
    //borrar todos
    public void deleteAll();

}