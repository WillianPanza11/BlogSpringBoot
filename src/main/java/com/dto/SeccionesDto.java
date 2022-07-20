package com.dto;

public class SeccionesDto {
    private Long idSecciones;
    private String nombre;
    //private Long idPost;

    public SeccionesDto() {
    }

    public SeccionesDto(Long idSecciones, String nombre) {
        this.idSecciones = idSecciones;
        this.nombre = nombre;
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

}
