package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.tfg.entities.User;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoUser;

@Service
public class UserService implements Crud <User,Integer>{
 //inyeccion de dependencias
    private final RepoUser repo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(RepoUser repo) {
        this.repo = repo;
    }

    @Override
    public void save(User entity) {
        repo.save(entity);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<User> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repo.deleteAll();
    }



    public User validaGuardaUsuario (User usuario) throws Exception{

        boolean NombreUsuario = repo.existsByNombreUsuario(usuario.getNombreUsuario());
        if(NombreUsuario){
            throw new Exception("Usuario ya registrado");
        }
        boolean Email = repo.existsByEmail(usuario.getEmail());
        if(Email){
            throw new Exception("Email ya registrado");
        }
        // System.out.println(usuario.getContrasenia());
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        
        // Asignar rol por defecto si no viene
        if (usuario.getRol() == null) {
            usuario.setRol("cliente");
        }

        return repo.save(usuario);
    }
    

}
