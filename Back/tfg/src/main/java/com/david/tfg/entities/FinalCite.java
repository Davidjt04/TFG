package com.david.tfg.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cita")
public class FinalCite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;

    // Fecha seleccionada del corte
    @Column(name = "fecha")
    private LocalDate fecha;

    // Hora seleccionada del corte
    @Column(name = "Hora")
    private LocalTime hora;

    // Precio del corte
    @Column(name = "Precio_Corte")
    private Double precioCorte;

    // Nombre del corte
    @Column(name = "Nombre_Corte")
    private String nombreCorte;

    // Nombre del trabajador
    @Column(name = "Nombre_trabajador")
    private String nombreTrabajador;

    // Getters y setters
    public int getIdCita() {
        return idCita; 
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita; 
    }

    public LocalDate getFecha() 
    { return fecha; 

    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha; 
    }

    public LocalTime getHora() {
        return hora; 
    }
    public void setHora(LocalTime hora) {
        this.hora = hora; 
    }
    public Double getPrecioCorte() {
        return precioCorte; 
    }
    public void setPrecioCorte(Double precioCorte) {
        this.precioCorte = precioCorte; 
    }

    public String getNombreCorte() {
        return nombreCorte; 
    }
    public void setNombreCorte(String nombreCorte) {
        this.nombreCorte = nombreCorte; 
    }

    public String getNombreTrabajador() {
        return nombreTrabajador; 
    }
    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador; 
    }
}
