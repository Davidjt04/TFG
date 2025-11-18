package com.david.tfg.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Detalle_Trabajador")
public class WorkerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle_Trabajador;

    private LocalDateTime horario_Trabajador;
    private String especializacion;
    private String ausencias;
    private String imagen;

    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario")
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "Corte_Trabajador_idCorte_Trabajador")
    private WorkerCut workerCut;

    public WorkerDetail() {}

    public WorkerDetail(Integer idDetalle_Trabajador, LocalDateTime horario_Trabajador, String especializacion,
                        String ausencias, String imagen, User user, WorkerCut workerCut) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
        this.horario_Trabajador = horario_Trabajador;
        this.especializacion = especializacion;
        this.ausencias = ausencias;
        this.imagen = imagen;
        this.user = user;
        this.workerCut = workerCut;
    }

    public WorkerDetail(Integer idDetalle_Trabajador, LocalDateTime horario_Trabajador, String especializacion,
                        String ausencias, String imagen) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
        this.horario_Trabajador = horario_Trabajador;
        this.especializacion = especializacion;
        this.ausencias = ausencias;
        this.imagen = imagen;
    }

    // --- Getters y Setters ---
    public Integer getIdDetalle_Trabajador() {
        return idDetalle_Trabajador;
    }

    public void setIdDetalle_Trabajador(Integer idDetalle_Trabajador) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
    }

    public LocalDateTime getHorario_Trabajador() {
        return horario_Trabajador;
    }

    public void setHorario_Trabajador(LocalDateTime horario_Trabajador) {
        this.horario_Trabajador = horario_Trabajador;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public String getAusencias() {
        return ausencias;
    }

    public void setAusencias(String ausencias) {
        this.ausencias = ausencias;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
