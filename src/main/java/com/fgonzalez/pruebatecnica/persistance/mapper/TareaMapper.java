package com.fgonzalez.pruebatecnica.persistance.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.fgonzalez.pruebatecnica.domain.TareaDTO;
import com.fgonzalez.pruebatecnica.persistance.entity.Tarea;

@Mapper(componentModel = "spring")
public interface TareaMapper {
    @Mappings({
        @Mapping(source = "identificador", target = "identificador"),
        @Mapping(source = "fechaCreacion", target = "fechaCreacion"),
        @Mapping(source = "descripcion", target = "descripcion"),
        @Mapping(source = "vigente", target = "vigente")
    })
    TareaDTO toTareaDTO(Tarea tarea);
    List<TareaDTO> toTareasDTO(List<Tarea> categories);

    @InheritInverseConfiguration
    Tarea toTarea(TareaDTO tareaDTO);
    List<Tarea> toTareas(List<TareaDTO> tareasDTO);
}
