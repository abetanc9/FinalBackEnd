package com.dh.IntegradorBackend.service;


import com.dh.IntegradorBackend.dto.TurnoDTO;
import com.dh.IntegradorBackend.entity.Odontologo;
import com.dh.IntegradorBackend.entity.Paciente;
import com.dh.IntegradorBackend.entity.Turno;
import com.dh.IntegradorBackend.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public List<TurnoDTO> listarTurnos(){
        LOGGER.info("Se realizo la busqueda de todos los turnos");
        List<Turno> turnosEncontrados= turnoRepository.findAll();

        List<TurnoDTO> respuesta= new ArrayList<>();

        for (Turno turno: turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(turno));
        }
        return  respuesta;
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
        LOGGER.info("Se busco un turno con ID: "+id);
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            //existe el turno
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            //no existe turno, es nulo
            return Optional.empty();
        }
    }
    public void eliminarTurno(Long id){
        LOGGER.warn("Se elimino el turno con iD: " +id);
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(TurnoDTO turnodto){
        LOGGER.warn("Se actualizo el turno con iD: " +turnodto.getId());
        turnoRepository.save(turnoDTOATurno(turnodto));
    }
    public TurnoDTO guardarTurno(TurnoDTO turnodto){
        LOGGER.info("se registro un turno para el paciente con ID: "+turnodto.getPacienteId());
        Turno turnoGuardado=turnoRepository.save(turnoDTOATurno(turnodto));
        return turnoATurnoDTO(turnoGuardado);
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir el turno a un turnoDTO
        TurnoDTO respuesta= new TurnoDTO();
        //cargar la información de turno al turno DTO
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFecha(turno.getFecha());
        //devolución
        return respuesta;
    }
    private Turno turnoDTOATurno(TurnoDTO turnodto){
        Turno respuesta= new Turno();
        //cargar la información de turno DTO al turno
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        odontologo.setId(turnodto.getOdontologoId());
        paciente.setId(turnodto.getPacienteId());
        respuesta.setFecha(turnodto.getFecha());
        respuesta.setId(turnodto.getId());
        //no debemos olvidarnos de agregar ambos objetos
        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        //salida
        return respuesta;
    }


}
