package com.fgonzalez.pruebatecnica.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgonzalez.pruebatecnica.persistance.entity.Tarea;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Integer> {
    @Query(value = "SELECT c FROM Tarea c WHERE c.descripcion = :descripcion and deleted = false")
    Tarea findTareaByDescripotarea(@Param("descripcion") String descripcion);
}
