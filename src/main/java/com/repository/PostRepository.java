package com.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    @Transactional
    List<Post> findAllByOrderByIdDesc();

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
    @Query(value = "SELECT * "
    +"FROM post pt JOIN secciones s "
    +"ON pt.id_seccion = s.id_secciones "
    +"WHERE s.nombre NOT IN ('ADMIN') AND s.id_secciones = :nombre", nativeQuery = true)
    List<Post> findAllBySeccion(int nombre);

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
    @Query(value = "SELECT * "
    + "FROM post pt JOIN secciones s "
    + "ON pt.id_seccion = s.id_secciones "
    + "WHERE s.nombre IN ('ADMIN') ORDER BY 1 DESC", nativeQuery = true)
    List<Post> findAllBySeccionAdmin();



}
