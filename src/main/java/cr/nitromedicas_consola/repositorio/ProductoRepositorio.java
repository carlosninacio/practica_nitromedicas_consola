package cr.nitromedicas_consola.repositorio;

import cr.nitromedicas_consola.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}
