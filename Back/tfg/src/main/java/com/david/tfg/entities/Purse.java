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
@Table(name = "Monedero")
public class Purse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransacccion_Monedero;

    //Realciones
    //1:1 con Usuario
    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private User user;

    //pocos con Pedido
    @OneToMany(mappedBy = "purse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> order;

     // Constructor vacío
    public Purse() {}

    // Constructor con relaciones
    public Purse(int idTransacccion_Monedero, User user, List<Order> order) {
        this.idTransacccion_Monedero = idTransacccion_Monedero;
        this.user = user;
        this.order = order;
    }

    // Constructor sin relaciones
    public Purse(int idTransacccion_Monedero) {
        this.idTransacccion_Monedero = idTransacccion_Monedero;
    }

    // Getters y setters
    public int getIdTransacccion_Monedero() {
        return idTransacccion_Monedero;
    }

    public void setIdTransacccion_Monedero(int idTransacccion_Monedero) {
        this.idTransacccion_Monedero = idTransacccion_Monedero;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
   
}
