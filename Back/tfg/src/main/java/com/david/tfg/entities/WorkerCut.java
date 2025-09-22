package com.david.tfg.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Corte_Trabajador")
public class WorkerCut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCorte_Trabajador;

    private LocalDateTime Duracion;
    private Double Precio;
    
    //Relaciones
    //1:1 con detalle trabajador 
    @OneToOne(mappedBy = "workerCut")
    private WorkerDetail workerDetail;

    

    //N:M con servicio
    @ManyToMany
    @JoinTable(
    name = "Servicio_has_Corte_Predefinido", // nombre de la tabla intermedia
    joinColumns = @JoinColumn(name = "idTrabajador"),
    inverseJoinColumns = @JoinColumn(name = "Corte_Predefinido_idCorte_Predefinido"))
    private List<Service> services;

    // Constructor vacío
    public WorkerCut() {
    }

    // Constructor con todos los atributos incluyendo relaciones
    public WorkerCut(int idCorte_Trabajador, LocalDateTime duracion, Double precio,
                     WorkerDetail workerDetail, List<Service> services) {
        this.idCorte_Trabajador = idCorte_Trabajador;
        this.Duracion = duracion;
        this.Precio = precio;
        this.workerDetail = workerDetail;
        this.services = services;
    }

    // Constructor solo con atributos básicos (sin relaciones)
    public WorkerCut(int idCorte_Trabajador, LocalDateTime duracion, Double precio) {
        this.idCorte_Trabajador = idCorte_Trabajador;
        this.Duracion = duracion;
        this.Precio = precio;
    }

    // --- Getters y Setters ---

    public int getIdCorte_Trabajador() {
        return idCorte_Trabajador;
    }

    public void setIdCorte_Trabajador(int idCorte_Trabajador) {
        this.idCorte_Trabajador = idCorte_Trabajador;
    }

    public LocalDateTime getDuracion() {
        return Duracion;
    }

    public void setDuracion(LocalDateTime duracion) {
        Duracion = duracion;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public WorkerDetail getWorkerDetail() {
        return workerDetail;
    }

    public void setWorkerDetail(WorkerDetail workerDetail) {
        this.workerDetail = workerDetail;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}
