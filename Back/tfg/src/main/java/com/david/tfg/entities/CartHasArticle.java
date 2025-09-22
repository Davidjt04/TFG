package com.david.tfg.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carrito_has_Articulo")
public class CartHasArticle {

    //esta anotacion indica que la clave primaria es compuesta
    @EmbeddedId
    private IDCartHasArticle id;
    private int Cantidad;

    //Relaciones
    //Muchos con carrito
    @ManyToOne
    //especifica donde se encuentra una de las dos partes de la clave primaria compuesta
    @MapsId("Carrito_idCarrito")
    @JoinColumn(name = "Carrito_idCarrito")
    private Cart cart;

    //Muchos con articulo
    @ManyToOne
    @MapsId("Articulo_idArticulo") 
    @JoinColumn(name = "Articulo_idArticulo")
    private Article article;

     // Constructor vacío
    public CartHasArticle() {}

    // Constructor con todos los atributos y relaciones
    public CartHasArticle(IDCartHasArticle id, int cantidad, Cart cart, Article article) {
        this.id = id;
        this.Cantidad = cantidad;
        this.cart = cart;
        this.article = article;
    }

    // Constructor sin relaciones (solo ID y cantidad)
    public CartHasArticle(IDCartHasArticle id, int cantidad) {
        this.id = id;
        this.Cantidad = cantidad;
    }

    // Getters y Setters
    public IDCartHasArticle getId() {
        return id;
    }

    public void setId(IDCartHasArticle id) {
        this.id = id;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
   
}
