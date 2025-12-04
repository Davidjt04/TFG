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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private String rol;
   
    //Relaciones
    //1:1 con monedero 
   //1:1 con monedero 
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Purse purse;

    //pocos con pedido 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> orders;

    //1:1 con carrito
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    //1:1 con detalle trabajador
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private WorkerDetail workerDetail;

    //pocos con reseña 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Review> review;
     // Constructor vacío
    public User() {}

    // Constructor con relaciones
    public User(int idUsuario, String NombreUsuario, String contrasenia, String Email, String rol,
                Purse purse, List<Order> orders, Cart cart, WorkerDetail workerDetail, List<Review> review) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = NombreUsuario;
        this.contrasenia = contrasenia;
        this.email = Email;
        this.rol = rol;
        this.purse = purse;
        this.orders = orders;
        this.cart = cart;
        this.workerDetail = workerDetail;
        this.review = review;
    }

    // Constructor sin relaciones
    public User(int idUsuario, String NombreUsuario, String contrasenia, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = NombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.rol = rol;
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.nombreUsuario = NombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Purse getPurse() {
        return purse;
    }

    public void setPurse(Purse purse) {
        this.purse = purse;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public WorkerDetail getWorkerDetail() {
        return workerDetail;
    }

    public void setWorkerDetail(WorkerDetail workerDetail) {
        this.workerDetail = workerDetail;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

}
