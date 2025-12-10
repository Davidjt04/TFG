package com.david.tfg.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carrito_has_Articulo")
public class CartHasArticle {

    @EmbeddedId
    private IDCartHasArticle id;

    private int Cantidad;

    // Muchos con carrito
    @ManyToOne
    @MapsId("carritoId") // <-- NOMBRE EXACTO DEL CAMPO EN IDCartHasArticle
    @JoinColumn(name = "Carrito_idCarrito")
    private Cart cart;

    // Muchos con artículo
    @ManyToOne
    @MapsId("articuloId") // <-- NOMBRE EXACTO DEL CAMPO EN IDCartHasArticle
    @JoinColumn(name = "Articulo_idArticulo")
    private Article article;

    public CartHasArticle() {}

    public CartHasArticle(IDCartHasArticle id, int cantidad, Cart cart, Article article) {
        this.id = id;
        this.Cantidad = cantidad;
        this.cart = cart;
        this.article = article;
    }

    public IDCartHasArticle getId() { return id; }
    public void setId(IDCartHasArticle id) { this.id = id; }

    public int getCantidad() { return Cantidad; }
    public void setCantidad(int cantidad) { this.Cantidad = cantidad; }

    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }

    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
}
