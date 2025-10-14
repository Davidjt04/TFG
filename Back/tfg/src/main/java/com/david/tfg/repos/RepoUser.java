package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.User;

public interface RepoUser extends JpaRepository <User, Integer>{

    //Metodo para buscar usuario por nombre de usuario
    public boolean existsByNombreUsuario(String nombreUsuario);
    //Metodo para buscar usuario por contrasenia
    public boolean existsByEmail(String email);
    //Metodo para buscar la contraseña de un usuario
    public boolean existsByContrasenia(String contrasenia);
    //metodo para buscar el nombre de usuario 
    public User findByNombreUsuario(String nombreUsuario);
}
