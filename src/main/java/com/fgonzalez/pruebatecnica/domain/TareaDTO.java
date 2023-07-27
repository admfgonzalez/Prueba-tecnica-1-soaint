package com.fgonzalez.pruebatecnica.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Representa una tarea")
public @Data class TareaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "Identificador de registro", example = "3")
    private Integer identificador;
    @ApiModelProperty(value = "Fecha creacion formato ISO 8601", example = "2023-26-12T22:20:00.000+00:00", dataType = "string")
    private Timestamp fechaCreacion;
    @NotNull
    @ApiModelProperty(value = "Descripcion de la tarea", example = "Realizar seguimiento ruta critica")
    private String descripcion;
    @ApiModelProperty(value = "Si la tarea es actual", example = "true")
    private Boolean vigente;

}
