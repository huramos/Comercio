package com.comercio.bff_service.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.comercio.bff_service.model.Promocion;

@FeignClient(name = "promocion-service", url = "${promocion.service.url}")
public interface PromocionClient {
    @GetMapping("/api/promociones")
    List<Promocion> getAllPromociones();
}

