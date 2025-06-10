package com.comercio.promociones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.promociones.model.Promocion;
import com.comercio.promociones.service.PromocionService;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {
    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public List<Promocion> obtenerTodas(){
        return promocionService.listarPromociones();
    }

    @PostMapping
    public Promocion crear(@RequestBody Promocion promocion){
        return promocionService.crearPromocion(promocion);
    }

    @PutMapping("/{id}")
    public Promocion actualizar(@PathVariable Long id, @RequestBody Promocion promocion){
        return promocionService.actualizarPromocion(id, promocion);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        promocionService.eliminarPromocion(id);
        return ResponseEntity.ok().build();
    }
}
