package com.fgonzalez.pruebatecnica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fgonzalez.pruebatecnica.domain.TareaDTO;
import com.fgonzalez.pruebatecnica.service.TareaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tarea")
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @GetMapping(value = "/gettareas", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Obtiene todas la tareas vigentes y no vigentes")
    public List<TareaDTO> getTareas() {
        return tareaService.findAll().get();
    }

    @PostMapping(value = "/removetarea", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Elimina la tarea enviada")
    
    public void removeTarea(
            @ApiParam(value = "La tarea a eliminar", required = true) @Valid @RequestBody TareaDTO oldTareaDTO) {
        tareaService.remove(oldTareaDTO);
    }

    @PostMapping(value = "/addtarea", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Agrega la tarea enviada")
    public TareaDTO addTarea(
            @ApiParam(value = "La tarea a agregar", required = true) @RequestBody TareaDTO newTareaDTO) {
        return tareaService.addNew(newTareaDTO).get();
    }

    @PostMapping(value = "/updatetarea", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Actualiza la tarea enviada")
    public TareaDTO updateTarea(
            @ApiParam(value = "La tarea a actualizar", required = true) @RequestBody TareaDTO newTareaDTO) {
        return tareaService.update(newTareaDTO).get();
    }
}
