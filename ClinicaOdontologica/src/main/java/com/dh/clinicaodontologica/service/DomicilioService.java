package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Domicilio;
import com.dh.clinicaodontologica.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    public Domicilio guardarDomicilio(Domicilio domicilio){
        return domicilioRepository.save(domicilio);
    }

    public Optional<Domicilio> buscarPorID(Long id){
        return domicilioRepository.findById(id);
    }

    public void actualizarDomicilio(Domicilio domicilio){
        domicilioRepository.save(domicilio);
    }

    public void eliminarDomicilio(Long id){
        domicilioRepository.deleteById(id);
    }

    public List<Domicilio> listarTodos(){
        return domicilioRepository.findAll();
    }

}
