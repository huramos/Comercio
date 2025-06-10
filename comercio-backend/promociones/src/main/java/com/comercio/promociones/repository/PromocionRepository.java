package com.comercio.promociones.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.comercio.promociones.model.Promocion;


public interface PromocionRepository extends JpaRepository<Promocion,Long>{
    Optional<Promocion> findByCodigo(String codigo);

    
}
