package cr.nitromedicas_consola.servicio;

import cr.nitromedicas_consola.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarProductos();
    public Producto buscarProductoPorId(Integer idProducto);
    public void guardarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
}