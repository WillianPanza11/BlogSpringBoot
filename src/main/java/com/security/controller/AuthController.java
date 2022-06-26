package com.security.controller;

import com.util.GenericResponse;
import com.util.ParametersApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.security.dto.JwtDto;
import com.security.dto.LoginUsuario;
import com.security.dto.NuevoUsuario;
import com.security.entity.Rol;
import com.security.entity.Usuario;
import com.security.enums.RolNombre;
import com.security.jwt.JwtProvider;
import com.security.service.RolService;
import com.security.service.UsuarioService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public GenericResponse<String> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        GenericResponse<String> response = new GenericResponse<>();
        if (bindingResult.hasErrors()) {
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject("CAMPOS MAL FORMADOS!!");
            response.setStatus(ParametersApp.SUCCESSFUL.value());
            return response;
        } else if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject("ESE NOMBRE YA EXISTE!!");
            response.setStatus(ParametersApp.SUCCESSFUL.value());
            return response;
        } else if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject("ESE EMAIL YA EXISTE!!");
            response.setStatus(ParametersApp.SUCCESSFUL.value());
            return response;
        } else {
            Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
                    nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()), 
                    nuevoUsuario.getCelular());
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            if (nuevoUsuario.getRoles().contains("admin")) {
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            }
            usuario.setRoles(roles);
            usuarioService.save(usuario);
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject("USUARIO GUARDADO " + usuario.getIdUsuario());
            response.setStatus(ParametersApp.SUCCESSFUL.value());
            return response;
        }
    }

    // @PostMapping("/nuevos")
    // public ResponseEntity<?> nuevos(@Valid @RequestBody NuevoUsuario
    // nuevoUsuario,
    // BindingResult bindingResult) {
    // if (bindingResult.hasErrors())
    // return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"),
    // HttpStatus.BAD_REQUEST);
    // if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
    // return new ResponseEntity(new Mensaje("ese nombre ya existe"),
    // HttpStatus.BAD_REQUEST);
    // if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
    // return new ResponseEntity(new Mensaje("ese email ya existe"),
    // HttpStatus.BAD_REQUEST);
    // Usuario usuario = new Usuario(nuevoUsuario.getNombre(),
    // nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
    // passwordEncoder.encode(nuevoUsuario.getPassword()));

    // Set<Rol> roles = new HashSet<>();
    // roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    // if (nuevoUsuario.getRoles().contains("admin"))
    // roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
    // usuario.setRoles(roles);
    // usuarioService.save(usuario);
    // return new ResponseEntity(new Mensaje("usuario guardado"),
    // HttpStatus.CREATED);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario
    // loginUsuario, BindingResult bindingResult) {
    // if (bindingResult.hasErrors())
    // return new ResponseEntity(new Mensaje("campos mal puestos"),
    // HttpStatus.BAD_REQUEST);
    // Authentication authentication = authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
    // loginUsuario.getPassword()));
    // SecurityContextHolder.getContext().setAuthentication(authentication);
    // String jwt = jwtProvider.generateToken(authentication);
    // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    // JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(),
    // userDetails.getAuthorities());
    // return new ResponseEntity(jwtDto, HttpStatus.OK);
    // }

    @PostMapping("/login")
    public GenericResponse<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        GenericResponse<JwtDto> response = new GenericResponse<>();
        if (bindingResult.hasErrors()) {
            response.setStatus(ParametersApp.SERVER_ERROR.value());
            return response;
        } else {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
                                loginUsuario.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateToken(authentication);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject(jwtDto);
                response.setStatus(ParametersApp.SUCCESSFUL.value());
                return response;
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                response.setStatus(ParametersApp.SERVER_ERROR.value());
            }
            return response;
        }

    }
}