package com.fgonzalez.pruebatecnica.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fgonzalez.pruebatecnica.domain.TareaDTO;
import com.fgonzalez.pruebatecnica.exception.RecordNotFoundException;
import com.fgonzalez.pruebatecnica.persistance.entity.Tarea;
import com.fgonzalez.pruebatecnica.persistance.mapper.TareaMapper;
import com.fgonzalez.pruebatecnica.persistance.repository.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private TareaMapper tareaMapper;

    @Override
    public Optional<TareaDTO> findById(Integer id) {
        return tareaRepository.findById(id).map(tarea -> tareaMapper.toTareaDTO(tarea));
    }

    @Override
    public Optional<List<TareaDTO>> findAll() {
        List<Tarea> actualList = new ArrayList<Tarea>();
        tareaRepository.findAll().forEach(actualList::add);
        return Optional.of(tareaMapper.toTareasDTO(actualList));
    }

    @Override
    public Optional<TareaDTO> addNew(TareaDTO tareaDTO) throws IllegalArgumentException {
        if (tareaDTO.getFechaCreacion() == null) {
            tareaDTO.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        }
        if (tareaRepository.existsById(tareaDTO.getIdentificador())) {
            throw new IllegalArgumentException("La tarea ya existe");
        }
        return save(tareaDTO);
    }

    @Override
    public Optional<TareaDTO> update(TareaDTO tareaDTO) {
        if (tareaDTO.getIdentificador() == null) {
            throw new IllegalArgumentException("La tarea a actualizar debe tener un identificador");
        }
        Optional<Tarea> responseTarea = tareaRepository.findById(tareaDTO.getIdentificador());
        if (responseTarea.isPresent()) {
            Tarea result = tareaRepository.save(tareaMapper.toTarea(tareaDTO));
            return Optional.of(tareaMapper.toTareaDTO(result));
        } else {
            throw new IllegalArgumentException("La tarea no exite");
        }
    }

    @Override
    public Optional<TareaDTO> save(TareaDTO tareaDTO) {
        if (tareaDTO.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La tarea necesita una descripcion");
        }
        Tarea result = tareaRepository.save(tareaMapper.toTarea(tareaDTO));
        return Optional.of(tareaMapper.toTareaDTO(result));
    }

    // No se deben borrar registros
    @Override
    public void remove(TareaDTO tareaDTO) throws IllegalArgumentException {
        Optional<Tarea> searchtarea = tareaRepository.findById(tareaDTO.getIdentificador());
        if (searchtarea.isPresent()) {
            searchtarea.get().setDeleted(true);
            tareaRepository.save(searchtarea.get());
        } else {
            throw new RecordNotFoundException("La tarea con identificador '" + tareaDTO.getIdentificador() + "' no existe");
        }
    }
}