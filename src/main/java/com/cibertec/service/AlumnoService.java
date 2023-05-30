package com.cibertec.service;

import com.cibertec.model.Alumno;
import com.cibertec.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;


    public List<Alumno> listarAlumnos(){return alumnoRepository.findAll();}

    public Optional<Alumno> buscarAlumnoxId(Integer id){
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if(alumno.isEmpty()){
            return Optional.empty();
        }else {
            return alumno;
        }
    }

    public Alumno registrar(Alumno alumno){return alumnoRepository.save(alumno);}

    public HashMap<String, String> eliminarxId(Integer id){
        HashMap<String, String> respuesta = new HashMap<String, String>();
        alumnoRepository.deleteById(id);
        respuesta.put("mensaje", "Alumno eliminado correctamente");
        return respuesta;
    }
}
