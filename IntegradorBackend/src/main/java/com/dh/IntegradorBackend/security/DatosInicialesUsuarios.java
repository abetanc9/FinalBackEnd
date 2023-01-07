package com.dh.IntegradorBackend.security;

import com.dh.IntegradorBackend.entity.Usuario;
import com.dh.IntegradorBackend.entity.UsuarioRole;
import com.dh.IntegradorBackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUsuarios implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario como si fuera real
        //guardarlo en la base
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passSinCifrar="digital";
        String passCifrada=cifrador.encode(passSinCifrar);
        Usuario usuarioAInsertar=new Usuario("Brad",
                "Brad",
                "brad.pitt86@gmail.com",passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
        //CREAR USUARIO TIPO ADMIN
        String passCifrada2=cifrador.encode("gato");
        Usuario usuarioAInsertar2=new Usuario("Santiago","Betancur",
                "santibeta133@gmail.com",passCifrada2,UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);

    }
}
