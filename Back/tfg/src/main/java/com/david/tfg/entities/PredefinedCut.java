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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Corte_Predefinido")
public class PredefinedCut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCorte_Predefinido;

    private String Nombre;
    private Double Precio_Total;
    private LocalDateTime Duracion_Base;
    private String Imagen;


    //Relaciones
    //N:M con servicio
    @ManyToMany
    @JoinTable(
    name = "Servicio_has_Corte_Predefinido", // nombre de la tabla intermedia
    joinColumns = @JoinColumn(name = "Corte_Predefinido_idCorte_Predefinido"),
    inverseJoinColumns = @JoinColumn(name = "Servicio_idServicio"))
    private List<Services> services;
    //pocos con corte Trabajador 
    
    @OneToMany(mappedBy = "predefinedCut", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<WorkerCut> workerCut;



    

  // Constructor vacío (requerido por JPA)
    public PredefinedCut() {
    }

    // Constructor con todos los atributos incluyendo relaciones
    public PredefinedCut(int idCorte_Predefinido, String nombre, Double precio_Total,
                         LocalDateTime duracion_Base, String Imagen, List<Services> services,
                         List<WorkerCut> workerCut, PredefinedCut predefinedCut) {
        this.idCorte_Predefinido = idCorte_Predefinido;
        this.Nombre = nombre;
        this.Precio_Total = precio_Total;
        this.Duracion_Base = duracion_Base;
        this.Imagen = Imagen;
        this.services = services;
        this.workerCut = workerCut;
        // this.predefinedCut = predefinedCut;
    }

    // Constructor solo con atributos básicos (sin relaciones)
    public PredefinedCut(int idCorte_Predefinido, String nombre, Double precio_Total,
                         LocalDateTime duracion_Base,String Imagen) {
        this.idCorte_Predefinido = idCorte_Predefinido;
        this.Nombre = nombre;
        this.Precio_Total = precio_Total;
        this.Duracion_Base = duracion_Base;
        this.Imagen = Imagen;

    }

    // --- Getters y Setters ---

    public int getIdCorte_Predefinido() {
        return idCorte_Predefinido;
    }

    public void setIdCorte_Predefinido(int idCorte_Predefinido) {
        this.idCorte_Predefinido = idCorte_Predefinido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public Double getPrecio_Total() {
        return Precio_Total;
    }

    public void setPrecio_Total(Double precio_Total) {
        this.Precio_Total = precio_Total;
    }

    public LocalDateTime getDuracion_Base() {
        return Duracion_Base;
    }

    public void setDuracion_Base(LocalDateTime duracion_Base) {
        this.Duracion_Base = duracion_Base;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public List<WorkerCut> getWorkerCut() {
        return workerCut;
    }

    public void setWorkerCut(List<WorkerCut> workerCut) {
        this.workerCut = workerCut;
    }

    // public PredefinedCut getPredefinedCut() {
    //     return predefinedCut;
    // }

    // public void setPredefinedCut(PredefinedCut predefinedCut) {
    //     this.predefinedCut = predefinedCut;
    // }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
}
