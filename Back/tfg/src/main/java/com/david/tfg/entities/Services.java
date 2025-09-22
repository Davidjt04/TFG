package com.david.tfg.entities;

import java.util.List;

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

    private String Nombre;
    private Double Precio;

    //Relaciones
    //N:M con corte predefinido
    @ManyToMany(mappedBy = "services")
    private List<WorkerCut> workerCuts;

    // Constructor vacío
    public Services() {}

    // Constructor con todas las relaciones
    public Services(int idServicio, String nombre, Double precio, List<WorkerCut> workerCuts) {
        this.idServicio = idServicio;
        this.Nombre = nombre;
        this.Precio = precio;
        this.workerCuts = workerCuts;
    }

    // Constructor sin relaciones
    public Services(int idServicio, String nombre, Double precio) {
        this.idServicio = idServicio;
        this.Nombre = nombre;
        this.Precio = precio;
    }

    // Getters y setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public List<WorkerCut> getWorkerCuts() {
        return workerCuts;
    }

    public void setWorkerCuts(List<WorkerCut> workerCuts) {
        this.workerCuts = workerCuts;
    }
}
