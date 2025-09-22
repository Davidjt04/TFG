package com.david.tfg.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cita")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;

    private LocalDateTime Hora;
    private LocalDateTime Duracion_Corte;
    private Double Precio_Corte;
    private String Nombre_Corte;


    //Getter y Setter
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getHora() {
        return Hora;
    }

    public void setHora(LocalDateTime hora) {
        this.Hora = hora;
    }

    public LocalDateTime getDuracion_Corte() {
        return Duracion_Corte;
    }

    public void setDuracion_Corte(LocalDateTime duracion_Corte) {
        this.Duracion_Corte = duracion_Corte;
    }

    public Double getPrecio_Corte() {
        return Precio_Corte;
    }

    public void setPrecio_Corte(Double precio_Corte) {
        this.Precio_Corte = precio_Corte;
    }

    public String getNombre_Corte() {
        return Nombre_Corte;
    }

    public void setNombre_Corte(String nombre_Corte) {
        this.Nombre_Corte = nombre_Corte;
    }
}
