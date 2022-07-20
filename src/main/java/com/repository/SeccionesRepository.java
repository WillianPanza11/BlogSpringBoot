package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dto.SeccionesDto;
import com.model.Secciones;


public interface SeccionesRepository extends JpaRepository<Secciones, Long> {

    //@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
    @Query(nativeQuery = true)
    List<SeccionesDto> seccionUA(String isAdmin);
}
