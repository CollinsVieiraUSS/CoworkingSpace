package com.coworkingspace.users_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coworkingspace.users_service.Entity.Role;
import com.coworkingspace.users_service.Service.RoleService;


@RestController
@RequestMapping("/v2/roles")
public class RoleController {
	@Autowired
    private RoleService service;

    // Obtener todos los roles con paginación
    @GetMapping
    public ResponseEntity<List<Role>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Role> registros = service.findAll(page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // Obtener un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") int id) {
        Role registro = service.findById(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    // Crear un nuevo rol
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role registro) {
        Role role = service.Save(registro);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    // Actualizar un rol existente
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable("id") int id, @RequestBody Role registro) {
        // Se actualiza el rol
        registro.setId(id);
        Role role = service.Save(registro);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Eliminar un rol por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        service.Delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
