package com.dh.IntegradorBackend.controller;

import com.dh.IntegradorBackend.entity.Paciente;
import com.dh.IntegradorBackend.exections.BadRequestException;
import com.dh.IntegradorBackend.exections.ResourceNotFoundException;
import com.dh.IntegradorBackend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }




    /*@GetMapping
    public String buscarPorEmail(Model model, @RequestParam("email") String  email){
        Paciente paciente=pacienteService.buacarXemail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        //devolver el nombre del template
        return "index";
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Paciente>  buscarPaciente(@PathVariable Long id) throws BadRequestException {
        Optional<Paciente> buscarPacient=pacienteService.buacarPaciente(id);
        if (buscarPacient.isPresent()) {
            return ResponseEntity.ok(buscarPacient.get());
        }
        else {
            throw new BadRequestException("la peticion que acaba de realizar tiene datos incorrectos");
        }
    }

    @PostMapping
    public ResponseEntity<Paciente>  registarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacientBuscar=pacienteService.buacarXemail(paciente.getEmail());
        if (pacientBuscar.isPresent()){
            throw new ResourceNotFoundException("Error. "+"Ya existe  el paciente con email= "+ paciente.getEmail());
        }
        else {
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }
    }

    @PutMapping
    public ResponseEntity<String>  actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        //preguntar si existe el paciente
        Optional<Paciente> buscarpaciente=pacienteService.buacarPaciente(paciente.getId());
        if (buscarpaciente.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("se actualizo el paciente con id= "+paciente.getId());
        }
        else {
            throw new BadRequestException("la peticion que acaba de realizar tiene datos incorrectos");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> buscarpaciente=pacienteService.buacarPaciente(id);
        if (buscarpaciente.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el paciente" +
                    " con id= "+id);
        }
        else {
            throw new ResourceNotFoundException("Error. "+"No existe  el paciente con id= "+id);

        }

    }

    @GetMapping
    public ResponseEntity<List<Paciente>>  buscarTodosLosPacientes(){
        return  ResponseEntity.ok(pacienteService.buscarPacientes(1l));
    }

    @GetMapping("buscar/{email}")
    public ResponseEntity<Paciente> buscarPorCorreo(@PathVariable String email) throws ResourceNotFoundException {
        Optional<Paciente> buscarPacient=pacienteService.buacarXemail(email);
        if (buscarPacient.isPresent()) {
            return ResponseEntity.ok(buscarPacient.get());
        }
        else {
            throw new ResourceNotFoundException("Error. "+"No existe  el paciente con email= "+ email);
        }
    }

}
