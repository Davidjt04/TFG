package com.david.tfg.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.david.tfg.entities.Cart;

public interface RepoCart extends JpaRepository<Cart, Integer> {

    // ✅ Esto SI es correcto si tu Cart tiene relación con Usuario
    Optional<Cart> findByUserIdUsuario(Integer idUsuario);
}
