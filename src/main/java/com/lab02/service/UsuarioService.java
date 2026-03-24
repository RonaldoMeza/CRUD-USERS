package com.lab02.service;

import com.lab02.dto.UsuarioRequest;
import com.lab02.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> getAllUsuarios();

    UsuarioResponse getUsuarioById(Long id);

    UsuarioResponse createUsuario(UsuarioRequest usuarioRequest);

    UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioRequest);

    void deleteUsuario(Long id);
}
