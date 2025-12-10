package com.david.tfg.entities;

public class CartHasArticleResponseDTO {
private int cantidad;
    private Article article;  // artículo completo
    private int idCarrito;    // opcional, para referencia

    public CartHasArticleResponseDTO() {}

    public CartHasArticleResponseDTO(CartHasArticle cha) {
        this.cantidad = cha.getCantidad();
        this.article = cha.getArticle();
        this.idCarrito = cha.getCart() != null ? cha.getCart().getIdCarrito() : 0;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }

    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }
}

