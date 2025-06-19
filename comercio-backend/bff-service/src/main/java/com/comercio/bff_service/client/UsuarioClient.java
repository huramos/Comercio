package com.comercio.bff_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.comercio.bff_service.model.Usuario;


@FeignClient(name = "usuario-service", url = "${usuario.service.url}")
public interface UsuarioClient {
    @GetMapping("/usuarios")
    List<Usuario> getAllUsuarios();
}
