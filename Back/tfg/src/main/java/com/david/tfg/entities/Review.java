// package com.david.tfg.entities;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "Resenia")
// public class Review {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int idResenia;

//     private int Estrellas;
//     private LocalDateTime Hora;

//     //Relaciones
//     //Muchos con Usuario 
//     @ManyToOne
//     @JoinColumn(name = "Usuario_idUsuario")
//     private User user;
//     private String Resenia; 


//      // Constructor vacío
//     public Review() {}

//     // Constructor con relaciones
//     public Review(int idResenia, int estrellas, LocalDateTime hora, User user, String Resenia) {
//         this.idResenia = idResenia;
//         this.Estrellas = estrellas;
//         this.Hora = hora;
//         this.user = user;
//         this.Resenia = Resenia;
//     }

//     // Constructor sin relaciones
//     public Review(int idResenia, int estrellas, LocalDateTime hora, String Resenia) {
//         this.idResenia = idResenia;
//         this.Estrellas = estrellas;
//         this.Hora = hora;
//         this.Resenia = Resenia;
//     }

//     // Getters y setters
//     public int getIdResenia() {
//         return idResenia;
//     }

//     public void setIdResenia(int idResenia) {
//         this.idResenia = idResenia;
//     }

//         public String getResenia() {
//         return Resenia;
//     }

//     public void setResenia(String Resenia) {
//         this.Resenia = Resenia;
//     }


//     public int getEstrellas() {
//         return Estrellas;
//     }

//     public void setEstrellas(int estrellas) {
//         Estrellas = estrellas;
//     }

//     public LocalDateTime getHora() {
//         return Hora;
//     }

//     public void setHora(LocalDateTime hora) {
//         Hora = hora;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

// }

package com.david.tfg.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Resenia")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResenia;

    private int Estrellas;
    private LocalDateTime Hora;

    //Relaciones
    //Muchos con Usuario 
    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private User user;
    private String Resenia; 


     // Constructor vacío
    public Review() {}

    // Constructor con relaciones
    public Review(int idResenia, int estrellas, LocalDateTime hora, User user, String Resenia) {
        this.idResenia = idResenia;
        this.Estrellas = estrellas;
        this.Hora = hora;
        this.user = user;
        this.Resenia = Resenia;
    }

    // Constructor sin relaciones
    public Review(int idResenia, int estrellas, LocalDateTime hora, String Resenia) {
        this.idResenia = idResenia;
        this.Estrellas = estrellas;
        this.Hora = hora;
        this.Resenia = Resenia;
    }

    // Getters y setters
    public int getIdResenia() {
        return idResenia;
    }

    public void setIdResenia(int idResenia) {
        this.idResenia = idResenia;
    }

        public String getResenia() {
        return Resenia;
    }

    public void setResenia(String Resenia) {
        this.Resenia = Resenia;
    }


    public int getEstrellas() {
        return Estrellas;
    }

    public void setEstrellas(int estrellas) {
        Estrellas = estrellas;
    }

    public LocalDateTime getHora() {
        return Hora;
    }

    public void setHora(LocalDateTime hora) {
        Hora = hora;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

