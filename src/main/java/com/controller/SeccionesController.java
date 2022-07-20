package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.SeccionesDto;
import com.service.SeccionesService;
import com.util.GenericResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("secciones")
@Tag(name = "SeccionesWS", description = "Control y mantenimiento de secciones")
public class SeccionesController {
    
    @Autowired
    private SeccionesService seccionesService;

    @PostMapping(path = "save")
    public ResponseEntity<GenericResponse<String>> guardarSecciones( @RequestBody SeccionesDto  seccionesDto){
        return new ResponseEntity<GenericResponse<String>> (seccionesService.saveSecciones(seccionesDto), HttpStatus.OK);
    }

    @GetMapping(path = "listar-secciones")
	public ResponseEntity<GenericResponse<List<SeccionesDto>>> listarSecciones(@RequestParam String isAdmin){
		return new ResponseEntity<GenericResponse<List<SeccionesDto>>>(seccionesService.listAllSecciones(isAdmin), HttpStatus.OK);
	}

    @PutMapping(path = "updateSecciones")
	public ResponseEntity<GenericResponse<String>> updatePost(@RequestBody SeccionesDto seccionesDto){
		return new ResponseEntity<GenericResponse<String>>(seccionesService.updateSecciones(seccionesDto), HttpStatus.OK);
	}
}
