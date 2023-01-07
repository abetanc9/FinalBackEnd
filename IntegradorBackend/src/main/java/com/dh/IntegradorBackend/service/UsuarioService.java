package com.dh.IntegradorBackend.service;

import com.dh.IntegradorBackend.entity.Usuario;
import com.dh.IntegradorBackend.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    private static final Logger LOGGER=Logger.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Se busca un usuario con email: "+ username);
        Optional<Usuario> usuarioBuscado=usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }
        else{
            throw new UsernameNotFoundException("No existe el usuario con dicho correo en la bases de datos");
        }
    }
}
