package com.example.segundoparcial.service;

import com.example.segundoparcial.model.Usuario;
import com.example.segundoparcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario crear(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtener(Long id){
        return usuarioRepository.findById(id);
    }

    public void eliminar(Long id){
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }
    }

    public Usuario editar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
