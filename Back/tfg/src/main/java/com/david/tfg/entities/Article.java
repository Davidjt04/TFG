// package com.david.tfg.services;

// import java.util.List;
// import java.util.Optional;
// import org.springframework.stereotype.Service;
// import com.david.tfg.entities.Cart;
// import com.david.tfg.interfaces.Crud;
// import com.david.tfg.repos.RepoCart;

// @Service
// public class CartService implements Crud<Cart, Integer> {

//     private final RepoCart repo;

//     public CartService(RepoCart repo) {
//         this.repo = repo;
//     }

//     @Override
//     public void save(Cart entity) {
//         repo.save(entity);
//     }

//     @Override
//     public Optional<Cart> findById(Integer id) {
//         return repo.findById(id);
//     }

//     @Override
//     public boolean existsById(Integer id) {
//         return repo.existsById(id);
//     }

//     @Override
//     public List<Cart> findAll() {
//         return repo.findAll();
//     }

//     @Override
//     public void deleteById(Integer id) {
//         repo.deleteById(id);
//     }

//     @Override
//     public void deleteAll() {
//         repo.deleteAll();
//     }

//     // ✅ ESTE ES EL MÉTODO CLAVE
//     public Optional<Cart> findByUsuarioId(Integer idUsuario) {
//         return repo.findByUserIdUsuario(idUsuario);
//     }
// }
package com.david.tfg.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticulo;

    private String Nombre;
    private String Descripcion;
    private String Imagen;
    private double Precio;
    private String Categoria;

    //Relaciones
    //Pocos con detallePedido
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    //pocos con CartHasArticle
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartHasArticle> CartHasArticles;

    // Constructor vacío
    public Article() {}

    // Constructor con todas las relaciones
    public Article(int idArticulo, String nombre, String descripcion, String imagen, double precio, String categoria, int cantidad,
                   List<OrderDetail> orderDetails, List<CartHasArticle> cartHasArticles) {
        this.idArticulo = idArticulo;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Imagen = imagen;
        this.Precio = precio;
        this.Categoria = categoria;
        this.orderDetails = orderDetails;
        this.CartHasArticles = cartHasArticles;
    }

    // Constructor sin relaciones
    public Article(int idArticulo, String nombre, String descripcion, String imagen, double precio, String categoria, int cantidad) {
        this.idArticulo = idArticulo;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Imagen = imagen;
        this.Precio = precio;
        this.Categoria = categoria;
    }

    // Getters y Setters
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int id) {
        this.idArticulo = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        this.Precio = precio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        this.Categoria = categoria;
    }

}
