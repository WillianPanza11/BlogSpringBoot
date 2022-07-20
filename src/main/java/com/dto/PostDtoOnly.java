package com.dto;

import java.time.Instant;

public class PostDtoOnly {
    private Long id;
    private String content;
    private String title;
    private String username;
    private Long iduser; 
    private Instant createdOn;
    private Instant updatedOn;
    private Long idSecciones;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getIduser() {
        return iduser;
    }
    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }
    public Instant getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
    public Instant getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }
    public Long getIdSecciones() {
        return idSecciones;
    }
    public void setIdSecciones(Long idSecciones) {
        this.idSecciones = idSecciones;
    }

    
}
