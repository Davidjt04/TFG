
// package com.david.tfg.entities;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.hibernate.annotations.Fetch;
// import org.hibernate.annotations.FetchMode;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "Pedido")
// public class Order {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int idPedido;

//     private Double Precio_Total;
//     private LocalDateTime Fecha_Realizacion;
//     private String Estado;
   
//     //Muchos con usuario  
//     @ManyToOne
//     @JoinColumn(name = "Usuario_idUsuario")
//     private User user;
//     //Muchos con monedero
//     @ManyToOne
//     @JoinColumn(name = "Monedero_idTransacccion_Monedero")
//     private Purse purse;

//     //Pocos con DetallePedido
//     @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @Fetch(FetchMode.SUBSELECT)   // opcional, muy recomendable
//     @JsonIgnore
// private List<OrderDetail> OrderDetails;
    
//     // Constructor vacío
//     public Order() {}

//     // Constructor con todas las relaciones
//     public Order(int idPedido, Double precio_Total, LocalDateTime fecha_Realizacion, String estado,
//                  User user, Purse purse, List<OrderDetail> orderDetails) {
//         this.idPedido = idPedido;
//         this.Precio_Total = precio_Total;
//         this.Fecha_Realizacion = fecha_Realizacion;
//         this.Estado = estado;
//         this.user = user;
//         this.purse = purse;
//         this.OrderDetails = orderDetails;
//     }

//     // Constructor sin relaciones
//     public Order(int idPedido, Double precio_Total, LocalDateTime fecha_Realizacion, String estado) {
//         this.idPedido = idPedido;
//         this.Precio_Total = precio_Total;
//         this.Fecha_Realizacion = fecha_Realizacion;
//         this.Estado = estado;
//     }

//     // Getters y setters
//     public int getIdPedido() {
//         return idPedido;
//     }

//     public void setIdPedido(int idPedido) {
//         this.idPedido = idPedido;
//     }

//     public Double getPrecio_Total() {
//         return Precio_Total;
//     }

//     public void setPrecio_Total(Double precio_Total) {
//         Precio_Total = precio_Total;
//     }

//     public LocalDateTime getFecha_Realizacion() {
//         return Fecha_Realizacion;
//     }

//     public void setFecha_Realizacion(LocalDateTime fecha_Realizacion) {
//         Fecha_Realizacion = fecha_Realizacion;
//     }

//     public String getEstado() {
//         return Estado;
//     }

//     public void setEstado(String estado) {
//         Estado = estado;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

//     public Purse getPurse() {
//         return purse;
//     }

//     public void setPurse(Purse purse) {
//         this.purse = purse;
//     }

//     public List<OrderDetail> getOrderDetails() {
//         return OrderDetails;
//     }

//     public void setOrderDetails(List<OrderDetail> orderDetails) {
//         OrderDetails = orderDetails;
//     }


    
// }

package com.david.tfg.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "Pedido")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    private Double Precio_Total;
    private LocalDateTime Fecha_Realizacion;
    private String Estado;
   
    //Muchos con usuario  
    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private User user;
    //Muchos con monedero
    @ManyToOne
    @JoinColumn(name = "Monedero_idTransacccion_Monedero")
    private Purse purse;

    //Pocos con DetallePedido
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderDetail> OrderDetails;
    
    // Constructor vacío
    public Order() {}

    // Constructor con todas las relaciones
    public Order(int idPedido, Double precio_Total, LocalDateTime fecha_Realizacion, String estado,
                 User user, Purse purse, List<OrderDetail> orderDetails) {
        this.idPedido = idPedido;
        this.Precio_Total = precio_Total;
        this.Fecha_Realizacion = fecha_Realizacion;
        this.Estado = estado;
        this.user = user;
        this.purse = purse;
        this.OrderDetails = orderDetails;
    }

    // Constructor sin relaciones
    public Order(int idPedido, Double precio_Total, LocalDateTime fecha_Realizacion, String estado) {
        this.idPedido = idPedido;
        this.Precio_Total = precio_Total;
        this.Fecha_Realizacion = fecha_Realizacion;
        this.Estado = estado;
    }

    // Getters y setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Double getPrecio_Total() {
        return Precio_Total;
    }

    public void setPrecio_Total(Double precio_Total) {
        Precio_Total = precio_Total;
    }

    public LocalDateTime getFecha_Realizacion() {
        return Fecha_Realizacion;
    }

    public void setFecha_Realizacion(LocalDateTime fecha_Realizacion) {
        Fecha_Realizacion = fecha_Realizacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Purse getPurse() {
        return purse;
    }

    public void setPurse(Purse purse) {
        this.purse = purse;
    }

    public List<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        OrderDetails = orderDetails;
    }


    
}
    
    