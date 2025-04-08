package com.example.segundoparcial.repository;

import com.example.segundoparcial.model.Usuario;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
