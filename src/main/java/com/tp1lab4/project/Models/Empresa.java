package com.tp1lab4.project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    public String denominacion;
    @Column
    private String telefono;
    @Column
    private String horarioAtencion;
    @Column
    private String quienesSomos;
    @Column
    private double latitud;
    @Column
    private double longitud;
    @Column
    private String domicilio;
    @Column
    private String email;
}