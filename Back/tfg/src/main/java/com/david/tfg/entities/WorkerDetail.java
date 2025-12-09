package com.david.tfg.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Detalle_Trabajador")
public class WorkerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle_Trabajador;

    @Column(name = "Horario_Trabajador")
    // @OneToOne(mappedBy = "detalleTrabajador", cascade = CascadeType.ALL, orphanRemoval = true)
    private LocalDateTime horario_Trabajador;
    private String especializacion;
    private String ausencias;
    private String imagen;
    private String nombre;

  @OneToOne
    @JoinColumn(name = "Usuario_idUsuario")
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "Corte_Trabajador_idCorte_Trabajador")
    private WorkerCut workerCut;

    @OneToMany(mappedBy = "detalleTrabajador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
private List<Workerschedule> horarios;

    public WorkerDetail() {}

    public WorkerDetail(Integer idDetalle_Trabajador, LocalDateTime horario_Trabajador, String especializacion,
                        String ausencias, String imagen, User user, WorkerCut workerCut, String nombre) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
        this.horario_Trabajador = horario_Trabajador;
        this.especializacion = especializacion;
        this.ausencias = ausencias;
        this.imagen = imagen;
        this.user = user;
        this.workerCut = workerCut;
        this.nombre = nombre;
    }

    public WorkerDetail(Integer idDetalle_Trabajador, LocalDateTime horario_Trabajador, String especializacion,
                        String ausencias, String imagen, String nombre) {
        this.idDetalle_Trabajador = idDetalle_Trabajador;
        this.horario_Trabajador = horario_Trabajador;
        this.especializacion = especializacion;
        this.ausencias = ausencias;
        this.imagen = imagen;
        this.nombre = nombre;
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Workerschedule> getHorarios() {
    return horarios;
    }

    public void setHorarios(List<Workerschedule> horarios) {
        this.horarios = horarios;
    }
}


