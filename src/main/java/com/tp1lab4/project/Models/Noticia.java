package com.tp1lab4.project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String tituloNoticia;
    @Column
    private String resumenNoticia;
    @Column
    private String imagenNoticia;
    @Column
    private String contenidoHtml;
    @Column
    private char publicada;
    @Column
    private Date fechaPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}