package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.Cart;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoCart;

@Service
public class CartService implements Crud <Cart,Integer>{
//inyeccion de dependencias
    private final RepoCart repo;

    public CartService(RepoCart repo) {
        this.repo = repo;
    }
    @Override
    public void save(Cart entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Cart> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Cart> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repo.deleteAll();
    }

    public void actualizarCantidadTotal(Cart cart) {
        if (cart == null) return;

        int total = 0;

        // Evitar NullPointerException si no hay artículos
        if (cart.getCartHasArticles() != null) {
            total = cart.getCartHasArticles()
                        .stream()
                        .mapToInt(c -> c.getCantidad())  // suma de cantidades
                        .sum();
        }

        cart.setCantidad_Total(total);

        // Guardar cambios en la base de datos
        save(cart);
    }

    public Optional<Cart> findByUsuarioId(int userId) {
    System.out.println("📦 CartService: buscando carrito para usuario " + userId);
    Optional<Cart> result = repo.findByUserIdUsuario(userId);
    System.out.println("📦 CartService: carrito encontrado: " + (result.isPresent() ? result.get().getIdCarrito() : "ninguno"));
    return result;
}


}

