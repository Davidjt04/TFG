package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.tfg.entities.User;
import com.david.tfg.enums.RolEnum;
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



    public User validaRegistro(User usuario) throws Exception {
    if (!(validaNombreUsu(usuario.getNombreUsuario()) &&
          validaContrasenia(usuario.getContrasenia()) &&
          validaEmail(usuario.getEmail()) &&
          validaRol(usuario.getRol()))) {
        throw new Exception("Datos de usuario no válidos");
    }

    if (repo.existsByNombreUsuario(usuario.getNombreUsuario())) {
        throw new Exception("El nombre de usuario ya existe en la base de datos");
    }

    usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));

    return repo.save(usuario);
}

    public User validaLogin (User usuario) throws Exception{
   User usuarioBD = repo.findByNombreUsuario(usuario.getNombreUsuario());

    if (usuarioBD == null) {
        throw new Exception("El nombre de usuario no es correcto");
    }

    // 2️ Validar contraseña (comparando hash)
    if (!passwordEncoder.matches(usuario.getContrasenia(), usuarioBD.getContrasenia())) {
        throw new Exception("La contraseña no es correcta");
    }

    // 3️ Retornar el usuario autenticado (desde BD)
    return usuarioBD;
    }


    public boolean validaNombreUsu(String nombreUsu) throws Exception{
        if(nombreUsu.matches("^(?!\\s*$).+") || nombreUsu != null){
            return true;
        }
        throw new Exception("Nombre de usuario no puede estar vacio");
    }
    public boolean validaContrasenia(String contrasenia) throws Exception{
        if(contrasenia.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$")|| contrasenia != null){
            return true;

        }
        throw new Exception("Contraseña no valida. Debe tener minimo 8 digitos, al menos una letra, un numero y un caracter especial");
    }
    public boolean validaEmail(String email) throws Exception{
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")|| email == null){
            throw new Exception("Email no valido");
        }
        if (repo.existsByEmail(email)) {
            throw new Exception("Email ya registrado");
        }
        return true;

    }
    public boolean validaRol(String rol) throws Exception{
       try {
        // Convierte el String a enum (si no coincide lanza una excepción)
        RolEnum.valueOf(rol.toUpperCase());
        return true;
    } catch (IllegalArgumentException e) {
        throw new Exception("Rol no válido. Debe ser TRABAJADOR, ADMIN o CLIENTE");
    }
    }
    public User findByNombreUsuario(String nombreUsuario) {
        return repo.findByNombreUsuario(nombreUsuario);
    }


}
