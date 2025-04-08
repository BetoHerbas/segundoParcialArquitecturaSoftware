package com.example.segundoparcial.contoller;

import com.example.segundoparcial.api.UsuarioApi;
import com.example.segundoparcial.dto.UsuarioDto;
import com.example.segundoparcial.model.Usuario;
import com.example.segundoparcial.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioApi {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<UsuarioDto> usuarios = usuarioService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtener(@PathVariable Long id) {
        return usuarioService.obtener(id)
                .map(usuario -> ResponseEntity.ok(toDto(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<UsuarioDto> crear(@Valid @RequestBody UsuarioDto usuarioDto) {
        usuarioDto.setId(null);
        Usuario nuevo = usuarioService.crear(fromDto(usuarioDto));
        return ResponseEntity.ok(toDto(nuevo));
    }

    @Override
    @PutMapping("/{id}")  // Usamos PUT para actualizar
    public ResponseEntity<UsuarioDto> editar(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioExistente = usuarioService.obtener(id).orElse(null);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioExistente.setNombre(usuarioDto.getNombre());
        usuarioExistente.setCorreo(usuarioDto.getCorreo());

        Usuario usuarioEditado = usuarioService.editar(usuarioExistente);

        return ResponseEntity.ok(toDto(usuarioEditado));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Usuario usuarioExistente = usuarioService.obtener(id).orElse(null);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    private UsuarioDto toDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        return dto;
    }

    private Usuario fromDto(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        return usuario;
    }
}

