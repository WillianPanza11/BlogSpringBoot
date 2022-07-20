package com.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.SeccionesDto;
import com.model.Secciones;
import com.repository.SeccionesRepository;
import com.util.GenericResponse;
import com.util.ParametersApp;

@Service
public class SeccionesService {
    private static final Logger log = LoggerFactory.getLogger(SeccionesService.class);

    @Autowired
    private SeccionesRepository seccionesRepository;

    public GenericResponse<String> saveSecciones(SeccionesDto seccionesDto) {
        GenericResponse<String> response = new GenericResponse<>();
        try {
            Secciones secciones = new Secciones();
            secciones.setIdSecciones(seccionesDto.getIdSecciones());
            secciones.setNombre(seccionesDto.getNombre());
            //secciones.setIdPost(seccionesDto.getIdPost());
            seccionesRepository.save(secciones);
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject("SECCIONES " + secciones.getIdSecciones() + " guardado correctamente ");
            response.setStatus(ParametersApp.SUCCESSFUL.value());
        } catch (Exception e) {
            // TODO: handle exception
            log.error("ERROR", e);
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }

    public GenericResponse<List<SeccionesDto>> listAllSecciones(String isAdmin) {
        GenericResponse<List<SeccionesDto>> response = new GenericResponse<>();
        try {
            List<SeccionesDto> seccionDto = new ArrayList<>();
            for (SeccionesDto secciones : seccionesRepository.seccionUA(isAdmin)) {
                if (secciones != null) {
                    SeccionesDto dto = new SeccionesDto();
                    dto.setIdSecciones(secciones.getIdSecciones());
                    dto.setNombre(secciones.getNombre());
                    //dto.setIdPost(secciones.getIdPost());
                    seccionDto.add(dto);
                    response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                    response.setObject(seccionDto);
                    response.setStatus(ParametersApp.SUCCESSFUL.value());
                } else {
                    response.setStatus(ParametersApp.SERVER_ERROR.value());
                }
            }
        } catch (Exception e) {
            log.error("ERROR", e);
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }

    public GenericResponse<String> updateSecciones(SeccionesDto seccionesDto) {
        GenericResponse<String> response = new GenericResponse<>();
        try {
            Secciones secciones = seccionesRepository.findById(seccionesDto.getIdSecciones()).get();
            if(secciones != null){
                secciones.setIdSecciones(seccionesDto.getIdSecciones());
                secciones.setNombre(seccionesDto.getNombre());
                //secciones.setIdPost(seccionesDto.getIdPost());
                seccionesRepository.save(secciones);
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject("SECCIONES " + secciones.getIdSecciones() + " actualizado correctamente ");
                response.setStatus(ParametersApp.SUCCESSFUL.value());
            }else{
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject("NO SE ENCONTRO LA SECCION CON ID: " + seccionesDto.getIdSecciones()); //
                response.setStatus(ParametersApp.SUCCESSFUL.value());
            }
        } catch (Exception e) {
            log.error("ERROR: ", e);
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }
}
