package com.david.tfg;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.david.tfg.entities.Article;
import com.david.tfg.repos.RepoArticle;


@SpringBootTest
class TfgApplicationTests {

	@Autowired
	private RepoArticle repoArticle;

	@Test
	void testComprobarPersistenciaBD() {
        // Crear entidad
        Article art = new Article();
        art.setNombre("Laptop");
        art.setDescripcion("Laptop de prueba");
        art.setImagen("imagen.jpg");
        art.setPrecio(1200);
        art.setCategoria("Electrónica");
        art.setCantidad(10);

        // Guardar en la BD
        Article saved = repoArticle.save(art);

        // Leer de la BD
        Optional<Article> retrieved = repoArticle.findById(saved.getIdArticulo());

        // Verificar
        assertTrue(retrieved.isPresent());
        assertEquals("Laptop", retrieved.get().getNombre());

		System.out.println("Test ejecutado correctamente");
    }
}