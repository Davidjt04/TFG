package com.david.tfg.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String NombreUsuario;
    private String Contrasenia;
    private String Email;
    private double Rol;
   
    //Relaciones
    //1:1 con monedero 
   @OneToOne(mappedBy = "user")
    private Purse purse;

    //pocos con pedido 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> orders;

    //1:1 con carrito
    @OneToOne(mappedBy = "user")
    private Cart cart;

    //1:1 con detalle trabajador
    @OneToOne(mappedBy = "user")
    private WorkerDetail workerDetail;

    //pocos con reseña 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Review> review;
     // Constructor vacío
    public User() {}

    // Constructor con relaciones
    public User(int idUsuario, String nombreUsuario, String contrasenia, String email, double rol,
                Purse purse, List<Order> orders, Cart cart, WorkerDetail workerDetail, List<Review> review) {
        this.idUsuario = idUsuario;
        this.NombreUsuario = nombreUsuario;
        this.Contrasenia = contrasenia;
        this.Email = email;
        this.Rol = rol;
        this.purse = purse;
        this.orders = orders;
        this.cart = cart;
        this.workerDetail = workerDetail;
        this.review = review;
    }

    // Constructor sin relaciones
    public User(int idUsuario, String nombreUsuario, String contrasenia, String email, double rol) {
        this.idUsuario = idUsuario;
        this.NombreUsuario = nombreUsuario;
        this.Contrasenia = contrasenia;
        this.Email = email;
        this.Rol = rol;
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getRol() {
        return Rol;
    }

    public void setRol(double rol) {
        Rol = rol;
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
