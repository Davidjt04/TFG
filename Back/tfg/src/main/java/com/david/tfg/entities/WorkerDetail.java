package com.david.tfg.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Detalle_Trabajador")
public class WorkerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle_Trabajador;

    private LocalDateTime Horario_Trabajador;
    private String Especializacion;
    private String Ausencias;


    //Relaciones
    //1:1 con usuario 
    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private User user;

    //1:1 con corte trabajador 
    @OneToOne
    @JoinColumn(name = "Corte_Trabajador_idCorte_Trabajador")
    private WorkerCut workerCut;

     // Constructor vacío (obligatorio para JPA)
    public WorkerDetail() {
    }

    // Constructor con todos los atributos incluyendo relaciones
    public WorkerDetail(int idDetalle_Trabajador, LocalDateTime horario_Trabajador, String especializacion,
                        String ausencias, User user, WorkerCut workerCut) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
        this.Horario_Trabajador = horario_Trabajador;
        this.Especializacion = especializacion;
        this.Ausencias = ausencias;
        this.user = user;
        this.workerCut = workerCut;
    }

    // Constructor solo con atributos básicos (sin relaciones)
    public WorkerDetail(int idDetalle_Trabajador, LocalDateTime horario_Trabajador, String especializacion,
                        String ausencias) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
        this.Horario_Trabajador = horario_Trabajador;
        this.Especializacion = especializacion;
        this.Ausencias = ausencias;
    }

    // --- Getters y Setters ---

    public int getIdDetalle_Trabajador() {
        return idDetalle_Trabajador;
    }

    public void setIdDetalle_Trabajador(int idDetalle_Trabajador) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
    }

    public LocalDateTime getHorario_Trabajador() {
        return Horario_Trabajador;
    }

    public void setHorario_Trabajador(LocalDateTime horario_Trabajador) {
        Horario_Trabajador = horario_Trabajador;
    }

    public String getEspecializacion() {
        return Especializacion;
    }

    public void setEspecializacion(String especializacion) {
        Especializacion = especializacion;
    }

    public String getAusencias() {
        return Ausencias;
    }

    public void setAusencias(String ausencias) {
        Ausencias = ausencias;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkerCut getWorkerCut() {
        return workerCut;
    }

    public void setWorkerCut(WorkerCut workerCut) {
        this.workerCut = workerCut;
    }


}
