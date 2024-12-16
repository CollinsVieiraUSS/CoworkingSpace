package com.coworkingspace.users_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.coworkingspace.users_service.Entity.User;
import com.coworkingspace.users_service.Service.UserService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    // Obtener todos los clientes con paginación
    @GetMapping
    public ResponseEntity<List<User>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<User> registros = service.findAll(page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // Obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User registro = service.findById(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User registro) {
        User cliente = service.Save(registro);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User registro) {
        // Se actualiza el cliente
        registro.setId(id);
        User cliente = service.Save(registro);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    // Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        service.Delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @GetMapping("/reports-user/{idUser}")
    public ResponseEntity<?> findReportsByIdUser(@PathVariable("idUser") int idUser){
    	return ResponseEntity.ok(service.findReportsByIdUser(idUser));
    }
}
