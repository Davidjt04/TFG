package com.david.tfg.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class IDCartHasArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    private int carritoId;   // antes: Carrito_idCarrito
    private int articuloId;  // antes: Articulo_idArticulo

    // Constructor vacío
    public IDCartHasArticle() {}

    // Constructor con parámetros
    public IDCartHasArticle(int carritoId, int articuloId) {
        this.carritoId = carritoId;
        this.articuloId = articuloId;
    }

    // --- Getters y Setters ---
    public int getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(int carritoId) {
        this.carritoId = carritoId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    // equals y hashCode obligatorios para claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IDCartHasArticle)) return false;
        IDCartHasArticle that = (IDCartHasArticle) o;
        return carritoId == that.carritoId && articuloId == that.articuloId;
    }

    @Override
    public int hashCode() {
        return 31 * carritoId + articuloId;
    }
}
