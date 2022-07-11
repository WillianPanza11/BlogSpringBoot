package com.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.entity.Usuario;
import com.security.repository.UsuarioRepository;
import com.util.GenericResponse;
import com.util.ParametersApp;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public GenericResponse<Optional<Usuario>> getNombreUsuario(String nombreUsuario) {
        GenericResponse<Optional<Usuario>> response = new GenericResponse<>();
        try {
            Optional<Usuario> login = usuarioRepository.findByNombreUsuario(nombreUsuario);
            if (login.isPresent()) {
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject(login);
                response.setStatus(ParametersApp.SUCCESSFUL.value());
            }
        }catch (Exception e) {
            log.error("ERROR", e);
            response.setMessage(ParametersApp.SERVER_ERROR.getReasonPhrase());
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }
}
