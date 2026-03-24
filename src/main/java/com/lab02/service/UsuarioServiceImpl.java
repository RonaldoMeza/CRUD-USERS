package com.lab02.service;

import com.lab02.dto.UsuarioRequest;
import com.lab02.dto.UsuarioResponse;
import com.lab02.exception.BadRequestException;
import com.lab02.exception.ResourceNotFoundException;
import com.lab02.model.Usuario;
import com.lab02.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return mapToResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponse createUsuario(UsuarioRequest usuarioRequest) {
        if (usuarioRepository.existsByEmail(usuarioRequest.getEmail())) {
            throw new BadRequestException("El email ya está registrado");
        }
        Usuario usuario = mapToEntity(usuarioRequest);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToResponse(savedUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (!usuario.getEmail().equals(usuarioRequest.getEmail()) && usuarioRepository.existsByEmail(usuarioRequest.getEmail())) {
            throw new BadRequestException("El email ya está siendo utilizado por otro usuario");
        }

        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setEdad(usuarioRequest.getEdad());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return mapToResponse(updatedUsuario);
    }

    @Override
    @Transactional
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponse mapToResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .edad(usuario.getEdad())
                .build();
    }

    private Usuario mapToEntity(UsuarioRequest usuarioRequest) {
        return Usuario.builder()
                .nombre(usuarioRequest.getNombre())
                .email(usuarioRequest.getEmail())
                .edad(usuarioRequest.getEdad())
                .build();
    }
}
