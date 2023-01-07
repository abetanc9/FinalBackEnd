package com.dh.IntegradorBackend.service;


import com.dh.IntegradorBackend.entity.Paciente;
import com.dh.IntegradorBackend.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente){
        LOGGER.info("se registro un paciente con nombre: "+paciente.getNombre());
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente>  buacarPaciente(Long id){
        LOGGER.info("Se busco un paciente con ID: "+id);
        return pacienteRepository.findById(id);
    }

    public void eliminarPaciente(Long id){
        LOGGER.warn("Se elimino el paciente con iD: " +id);
        pacienteRepository.deleteById(id);
    }

    public void actualizarPaciente(Paciente paciente){
        LOGGER.warn("Se actualizo el paciente con iD: " +paciente.getId());
        pacienteRepository.save(paciente);
    }
    public List<Paciente> buscarPacientes(long l){
        LOGGER.info("Se realizo la busqueda de todos los pacientes");
        return pacienteRepository.findAll();
    }
    public Optional<Paciente>  buacarXemail(String email){
        LOGGER.info("se busco el paciente con correo: "+email);
        return pacienteRepository.findByEmail(email);
    }
}
