package com.david.tfg.entities;

public class CartArticleDTO {
    private int idUsuario;  // Id del usuario
    private int idArticulo; // Id del artículo
    private int cantidad;   // Cantidad a añadir

    public CartArticleDTO() {}

    public CartArticleDTO(int idUsuario, int idArticulo, int cantidad) {
        this.idUsuario = idUsuario;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdArticulo() { return idArticulo; }
    public void setIdArticulo(int idArticulo) { this.idArticulo = idArticulo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
