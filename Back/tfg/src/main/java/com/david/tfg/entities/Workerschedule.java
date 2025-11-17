package com.david.tfg.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;

@Entity
@Table(name = "horario_trabajador")
public class Workerschedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHorario_trabajador")
    private Integer idHorario_trabajador;

    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "Detalle_Trabajador_idDetalle_Trabajador")
    private WorkerDetail detalleTrabajador;

    public Workerschedule() {}

    public Workerschedule(Integer idHorario_trabajador, LocalDate fecha, LocalTime hora, Boolean disponible, WorkerDetail detalleTrabajador) {
        this.idHorario_trabajador = idHorario_trabajador;
        this.fecha = fecha;
        this.hora = hora;
        this.disponible = disponible;
        this.detalleTrabajador = detalleTrabajador;
    }

    public Workerschedule(Integer idHorario_trabajador, LocalDate fecha, LocalTime hora, Boolean disponible) {
        this.idHorario_trabajador = idHorario_trabajador;
        this.fecha = fecha;
        this.hora = hora;
        this.disponible = disponible;
    }

    // --- Getters y Setters ---
    public Integer getId() {
        return idHorario_trabajador;
    }

    public void setId(Integer idHorario_trabajador) {
        this.idHorario_trabajador = idHorario_trabajador;
    }

    public WorkerDetail getDetalleTrabajador() {
        return detalleTrabajador;
    }

    public void setDetalleTrabajador(WorkerDetail detalleTrabajador) {
        this.detalleTrabajador = detalleTrabajador;
    }

    public LocalDate getFecha() {
        return fecha;
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

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
