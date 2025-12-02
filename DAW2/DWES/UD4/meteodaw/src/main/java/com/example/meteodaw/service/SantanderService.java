package com.example.meteodaw.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.meteodaw.Condicion;
import com.example.meteodaw.entity.Santander;
import com.example.meteodaw.entity.Toledo;
import com.example.meteodaw.repository.SantanderRepository;
import com.example.meteodaw.repository.ToledoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SantanderService {
    private final SantanderRepository SantanderRepository;
    

        public SantanderService(SantanderRepository SantanderRepository) {
            this.SantanderRepository = SantanderRepository;
        }

        


        //GET ALL

        public List<Santander> findAll() {
            return SantanderRepository.findAll();
        }

        //GET CONDICION
        public List <Santander> findByCondiciones(Condicion Condicion){
            return SantanderRepository.findByCondicion(Condicion);
        }
        //GET FECHA
        public List<Santander> findByFecha(Date fecha){
            return SantanderRepository.findByFecha(fecha);
        }

        //POST
        public Santander save(Santander santander){
            return SantanderRepository.save(santander);
        }

        //PUT ESTADO
        public Santander updateCondicion(Long id, String nuevoCondicion){
            if (nuevoCondicion == null) {
                return null;
            }
        
            String condicionNormal = nuevoCondicion.trim().toUpperCase();

            if (!condicionNormal.equals("LLUVIA") && !condicionNormal.equals("SOLEADO") && !condicionNormal.equals("NUBLADO") && !condicionNormal.equals("TORMENTA") && !condicionNormal.equals("NIEVE") && !condicionNormal.equals("GRANIZO") && !condicionNormal.equals("GALERNA")) {
                return null;
            }

            Santander santander = SantanderRepository.findById(id).orElse(null);

            santander.setCondicion(Condicion.valueOf(condicionNormal));

            return SantanderRepository.save(santander);
        }


        public boolean existsById(Long id) {
            return SantanderRepository.findById(id) != null;
        }


        public void deleteById(Long id) {
    if (SantanderRepository.existsById(id)) {
        SantanderRepository.deleteById(id);
    } else {
        throw new EntityNotFoundException("Santander con id " + id + " no existe");
    }
}
}
