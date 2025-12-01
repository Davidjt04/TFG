package com.david.tfg.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Servicio")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idServicio;

    private String nombre ;
    private Double precio;

    //Relaciones
    //N:M con corte predefinido
    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private List<WorkerCut> workerCuts;

    // Constructor vacío
    public Services() {}

    // Constructor con todas las relaciones
    public Services(int idServicio, String nombre, Double precio, List<WorkerCut> workerCuts) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.precio = precio;
        this.workerCuts = workerCuts;
    }

    // Constructor sin relaciones
    public Services(int idServicio, String nombre, Double precio) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<WorkerCut> getWorkerCuts() {
        return workerCuts;
    }

    public void setWorkerCuts(List<WorkerCut> workerCuts) {
        this.workerCuts = workerCuts;
    }
}
