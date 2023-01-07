package com.dh.IntegradorBackend.service;


import com.dh.IntegradorBackend.entity.Odontologo;
import com.dh.IntegradorBackend.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar(Odontologo odontologo){
        LOGGER.info("se registro un odontologo con nombre: "+odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo>  buscar(Long id){
        LOGGER.info("Se busco un odontologo con ID: "+id);
        return odontologoRepository.findById(id);
    }

    public void actualizar(Odontologo odontologo){
        LOGGER.warn("Se actualizo el odontologo con iD: " +odontologo.getId());
        odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id){
        LOGGER.warn("Se elimino el odontologo con iD: " +id);
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> buscarTodos(){
        LOGGER.info("Se realizo la busqueda de todos los odontologos");
        return odontologoRepository.findAll();
    }
}
