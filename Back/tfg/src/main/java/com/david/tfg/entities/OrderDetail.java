package com.david.tfg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Detalle_Pedido")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle_Pedido;

    private Double Precio_Unitario;
    private int Cantidad_Unitaria;

    //Relaciones
    //muchos con articulo
    @ManyToOne
    @JoinColumn(name = "Articulo_idArticulo")
    private Article article; 

    //muchos con pedido
    @ManyToOne
    @JoinColumn(name = "Pedido_idPedido")
    private Order order;
    
    // Constructor vacío
    public OrderDetail() {}

    // Constructor con relaciones
    public OrderDetail(int idDetalle_Pedido, Double precio_Unitario, int cantidad_Unitaria, Article article, Order order) {
        this.idDetalle_Pedido = idDetalle_Pedido;
        this.Precio_Unitario = precio_Unitario;
        this.Cantidad_Unitaria = cantidad_Unitaria;
        this.article = article;
        this.order = order;
    }

    // Constructor sin relaciones
    public OrderDetail(int idDetalle_Pedido, Double precio_Unitario, int cantidad_Unitaria) {
        this.idDetalle_Pedido = idDetalle_Pedido;
        this.Precio_Unitario = precio_Unitario;
        this.Cantidad_Unitaria = cantidad_Unitaria;
    }

    // Getters y setters
    public int getIdDetalle_Pedido() {
        return idDetalle_Pedido;
    }

    public void setIdDetalle_Pedido(int idDetalle_Pedido) {
        this.idDetalle_Pedido = idDetalle_Pedido;
    }

    public Double getPrecio_Unitario() {
        return Precio_Unitario;
    }

    public void setPrecio_Unitario(Double precio_Unitario) {
        Precio_Unitario = precio_Unitario;
    }

    public int getCantidad_Unitaria() {
        return Cantidad_Unitaria;
    }

    public void setCantidad_Unitaria(int cantidad_Unitaria) {
        Cantidad_Unitaria = cantidad_Unitaria;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
