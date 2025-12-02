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
public class ToledoService {
    
    private final ToledoRepository toledoRepository;
    private Toledo toledo;

        public ToledoService(ToledoRepository toledoRepository) {
            this.toledoRepository = toledoRepository;
        }

        


        //GET ALL

        public List<Toledo> findAll() {
            return toledoRepository.findAll();
        }

        //GET CONDICION
        public List <Toledo> findByCondiciones(Condicion Condicion){
            return toledoRepository.findByCondicion(Condicion);
        }
        //GET FECHA
        public List<Toledo> findByFecha(Date fecha){
            return toledoRepository.findByFecha(fecha);
        }

        //POST
        public Toledo save(Toledo toledo){
            return toledoRepository.save(toledo);
        }

        //PUT ESTADO
        public Toledo updateCondicion(Long id, String nuevoCondicion){
            if (nuevoCondicion == null) {
                return null;
            }
        
            String condicionNormal = nuevoCondicion.trim().toUpperCase();

            if (!condicionNormal.equals("LLUVIA") && !condicionNormal.equals("SOLEADO") && !condicionNormal.equals("NUBLADO") && !condicionNormal.equals("TORMENTA") && !condicionNormal.equals("NIEVE") && !condicionNormal.equals("GRANIZO") && !condicionNormal.equals("GALERNA")) {
                return null;
            }

            Toledo toledo = toledoRepository.findById(id).orElse(null);

            toledo.setCondicion(Condicion.valueOf(condicionNormal));

            return toledoRepository.save(toledo);
        }


        public boolean existsById(Long id) {
            return toledoRepository.findById(id) != null;
        }


        public void deleteById(Long id) {
    if (toledoRepository.existsById(id)) {
        toledoRepository.deleteById(id);
    } else {
        throw new EntityNotFoundException("Toledo con id " + id + " no existe");
    }
}



        
}
