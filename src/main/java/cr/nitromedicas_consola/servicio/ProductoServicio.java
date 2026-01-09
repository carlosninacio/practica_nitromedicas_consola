package cr.nitromedicas_consola.servicio;

import cr.nitromedicas_consola.modelo.Producto;
import cr.nitromedicas_consola.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = productoRepositorio.findAll();
        return productos;
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        return productoRepositorio.findById(idProducto).orElse(null);
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoRepositorio.delete(producto);
    }
}