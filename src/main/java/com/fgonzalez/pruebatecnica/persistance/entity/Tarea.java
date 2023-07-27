package com.fgonzalez.pruebatecnica.persistance.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Table(name = "TBL_Tareas")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public @Data class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    @Column(name = "fecha_creacion")
    @NonNull
    private Timestamp fechaCreacion;

    @Column
    @NonNull
    private String descripcion;

    @Column
    private Boolean vigente;

    @Column
    private Boolean deleted;
}
