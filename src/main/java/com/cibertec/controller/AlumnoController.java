package com.cibertec.controller;

import com.cibertec.model.Alumno;
import com.cibertec.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("")
    public ResponseEntity<List<Alumno>> listarAlumnos(){
        List<Alumno> alumnosList = new ArrayList<>();
        alumnoService.listarAlumnos().forEach(alumnosList::add);
        if(alumnosList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alumnosList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumnoxId(@PathVariable("id")Integer id){
        return new ResponseEntity<>(alumnoService.buscarAlumnoxId(id).get(),
                HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Alumno> registrarAlumno(@RequestBody Alumno alumno){
        Alumno newAlumno = new Alumno();
        newAlumno.setNomalumno(alumno.getNomalumno());
        newAlumno.setApealumno(alumno.getApealumno());
        newAlumno.setIdesp(alumno.getIdesp());
        newAlumno.setLocales(alumno.getLocales());
        return new ResponseEntity<>(alumnoService.registrar(newAlumno), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable("id") Integer id,@RequestBody Alumno alumno){
        Alumno updateAlumno = alumnoService.buscarAlumnoxId(id).get();
        updateAlumno.setNomalumno(alumno.getNomalumno());
        updateAlumno.setApealumno(alumno.getApealumno());
        updateAlumno.setIdesp(alumno.getIdesp());
        updateAlumno.setLocales(alumno.getLocales());
        return new ResponseEntity<>(alumnoService.registrar(updateAlumno), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.eliminarxId(id));
    }
}