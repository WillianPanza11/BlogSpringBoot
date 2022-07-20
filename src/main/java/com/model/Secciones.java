package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.ColumnResult;

import com.dto.SeccionesDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedNativeQueries({
    @NamedNativeQuery(name = "Secciones.seccionUA", query = "", resultSetMapping = "seccionUA"),
})

@SqlResultSetMappings({
    @SqlResultSetMapping(name = "seccionUA", classes = {
        @ConstructorResult(targetClass = SeccionesDto.class, columns = {
            @ColumnResult(name = "idSecciones", type = Long.class),
            @ColumnResult(name = "nombre", type = String.class),
        })
    })
})

@NamedNativeQuery(
        name = "Secciones.seccionUA",
        query = "SELECT * FROM showseccion(:isAdmin) as(idSecciones bigint, nombre character varying(100))",
        resultSetMapping = "seccionUA"
)

@Entity
@Table(name = "SECCIONES")
public class Secciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSecciones;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    // @Column(name = "ID_POST", unique = false)
	// private Long idPost;

    @JsonIgnore
	@OneToMany(mappedBy = "secc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Post> post;

    public Secciones() {
    }

    public Secciones(Long idSecciones, String nombre, List<Post> post) {
        this.idSecciones = idSecciones;
        this.nombre = nombre;
        this.post = post;
    }

    public Long getIdSecciones() {
        return idSecciones;
    }

    public void setIdSecciones(Long idSecciones) {
        this.idSecciones = idSecciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    
}
