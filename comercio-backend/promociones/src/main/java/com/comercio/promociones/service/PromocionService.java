package com.comercio.promociones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comercio.promociones.model.Promocion;
import com.comercio.promociones.repository.PromocionRepository;

@Service
public class PromocionService {
    @Autowired
    private PromocionRepository promocionRepo;

    public List<Promocion> listarPromociones(){
        return promocionRepo.findAll();
    }

    public Promocion crearPromocion(Promocion promocion){
        return promocionRepo.save(promocion);
    }

    public Promocion actualizarPromocion(Long id, Promocion nuevaPromocion){
        Promocion promocion = promocionRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));
        promocion.setCodigo(nuevaPromocion.getCodigo());
        promocion.setDescuento(nuevaPromocion.getDescuento());
        promocion.setFechaInicio(nuevaPromocion.getFechaInicio());
        promocion.setFechaFin(nuevaPromocion.getFechaFin());
        return promocionRepo.save(promocion);
    }
    
    public void eliminarPromocion(long id){
        promocionRepo.deleteById(id);
    }
}
