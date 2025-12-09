package com.david.tfg.entities;

import java.util.List;

public class CartDTO {
    private int idCarrito;
    private int cantidadTotal;
    private int userId;
    private List<CartArticleDTO> articulos;

    // Getters y setters
    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }

    public int getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(int cantidadTotal) { this.cantidadTotal = cantidadTotal; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public List<CartArticleDTO> getArticulos() { return articulos; }
    public void setArticulos(List<CartArticleDTO> articulos) { this.articulos = articulos; }
}
