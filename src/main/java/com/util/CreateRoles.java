package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.security.entity.Rol;
import com.security.enums.RolNombre;
import com.security.service.RolService;



/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        // Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        // Rol rolUser = new Rol(RolNombre.ROLE_USER);
        // rolService.save(rolAdmin);
        // rolService.save(rolUser);
         
    }
}
