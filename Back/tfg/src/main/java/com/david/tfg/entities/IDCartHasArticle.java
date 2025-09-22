package com.david.tfg.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class IDCartHasArticle implements Serializable{
    private int Carrito_idCarrito;
    private int Articulo_idArticulo;

    //constructor vacio
    public IDCartHasArticle() {}

    //constructor con parametros
    public IDCartHasArticle(int carrito_idCarrito, int articulo_idArticulo) {
        this.Carrito_idCarrito = carrito_idCarrito;
        this.Articulo_idArticulo = articulo_idArticulo;
    }

    // Getters y Setters
    public int getCarrito_idCarrito() {
        return Carrito_idCarrito;
    }

    public void setCarrito_idCarrito(int carrito_idCarrito) {
        this.Carrito_idCarrito = carrito_idCarrito;
    }

    public int getArticulo_idArticulo() {
        return Articulo_idArticulo;
    }

    public void setArticulo_idArticulo(int articulo_idArticulo) {
        this.Articulo_idArticulo = articulo_idArticulo;
    }
    
    //equals 
    @Override
    public boolean equals(Object o) {
        //Validaciones
        if (this == o) return true;
        if (!(o instanceof IDCartHasArticle)) return false;
        //casting
        IDCartHasArticle that = (IDCartHasArticle) o;
        
        return Carrito_idCarrito == that.Carrito_idCarrito &&
               Articulo_idArticulo == that.Articulo_idArticulo;
    }
    //hashCode
    //genera un numero unico para cada objeto
    public int hashCode() {
        return Objects.hash(Carrito_idCarrito, Articulo_idArticulo);
    }

}
