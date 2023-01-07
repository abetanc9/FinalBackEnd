package com.dh.IntegradorBackend.controller;

import com.dh.IntegradorBackend.dto.TurnoDTO;
import com.dh.IntegradorBackend.exections.BadRequestException;
import com.dh.IntegradorBackend.exections.ResourceNotFoundException;
import com.dh.IntegradorBackend.service.OdontologoService;
import com.dh.IntegradorBackend.service.PacienteService;
import com.dh.IntegradorBackend.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;


    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }




    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        //si el odontologo o paciente no existe error
        //OdontologoService odontologoService=new OdontologoService();
        //PacienteService pacienteService=new PacienteService();
        if (odontologoService.buscar(turno.getOdontologoId()).isPresent()
            &&pacienteService.buacarPaciente(turno.getPacienteId()).isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else {
            throw new BadRequestException("Error en el ingreso de datos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoId(@PathVariable Long id) throws BadRequestException {
        Optional<TurnoDTO> buscarTurno=turnoService.buscarTurno(id);
        if (buscarTurno.isPresent()) {
            return ResponseEntity.ok(buscarTurno.get());
        }
        else {
            throw new  BadRequestException("No existe el id");
        }
    }


    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {

        //Podemos encontrar un problema con el id del turno
        //Podemos encontrar un problema con el id del odontologo y/o paciente

        Optional<TurnoDTO> buscarTurno=turnoService.buscarTurno(turno.getId());
        if (buscarTurno.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se a actualizado el turno con id= "+turno.getId());
        }
        else {
            throw new BadRequestException("la peticion que acaba de realizar tiene datos incorrectos");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> buscarTurno=turnoService.buscarTurno(id);
        if (buscarTurno.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó el turno" +
                    " con id= "+id);
        }
        else {
            throw new ResourceNotFoundException("No se puede eliminar el turno con id= "+id);
            //return ResponseEntity.badRequest().body("No se puede eliminar el turno con id= "+id);
        }
    }

}
