

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
@Table(name = "Carrito")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrito;

    private int Cantidad_Total;

    //Relaciones
    //1:1 con usuario 
    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private User user;

    //pocos con CartHasArticle
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartHasArticle> CartHasArticles;
    
    // Constructor vacío
    public Cart() {}

    // Constructor con todas las relaciones
    public Cart(int idCarrito, int cantidad_Total, User user, List<CartHasArticle> cartHasArticles) {
        this.idCarrito = idCarrito;
        this.Cantidad_Total = cantidad_Total;
        this.user = user;
        this.CartHasArticles = cartHasArticles;
    }

    // Constructor sin relaciones
    public Cart(int idCarrito, int cantidad_Total) {
        this.idCarrito = idCarrito;
        this.Cantidad_Total = cantidad_Total;
    }
    
    // Getter y Setter

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getCantidad_Total() {
        return Cantidad_Total;
    }

    public void setCantidad_Total(int cantidad_Total) {
        this.Cantidad_Total = cantidad_Total;
    }
    // Getter y Setter del usuario
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter y Setter de la lista de CartHasArticle
    public List<CartHasArticle> getCartHasArticles() {
        return CartHasArticles;
    }

    public void setCartHasArticles(List<CartHasArticle> cartHasArticles) {
        this.CartHasArticles = cartHasArticles;
    }


}

