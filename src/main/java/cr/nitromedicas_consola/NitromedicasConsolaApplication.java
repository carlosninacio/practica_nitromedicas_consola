package cr.nitromedicas_consola;

import cr.nitromedicas_consola.modelo.Producto;
import cr.nitromedicas_consola.servicio.IProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class NitromedicasConsolaApplication implements CommandLineRunner {

    @Autowired
    private IProductoServicio productoServicio;
    private static final Logger logger = LoggerFactory.getLogger(NitromedicasConsolaApplication.class);
    String nl = System.lineSeparator();

	public static void main(String[] args) {
        logger.info("Iniciando Aplicación");
		SpringApplication.run(NitromedicasConsolaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        nitromedicasApp();
    }

    private void nitromedicasApp() {
        var salir = false;
        var consola = new Scanner(System.in);
        while(!salir) {
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(nl);
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("""
        \nNITROMEDICAS G.A
        \t1. Listar Productos
        \t2. Buscar Producto
        \t3. Agregar Producto
        \t4. Modificar Producto
        \t5. Eliminar Producto
        \t6. Salir
        * Elige una opción: """);
        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
            switch (opcion) {
                case 1 -> {
                    logger.info("--- Listado de Productos ---" + nl);
                    List<Producto> productos = productoServicio.listarProductos();
                    productos.forEach(producto -> logger.info(producto.toString()));
                }
                case 2 -> {
                    logger.info("--- Buscar Producto ---" + nl);
                    logger.info("Id del producto: ");
                    var idProducto = Integer.parseInt(consola.nextLine());
                    Producto producto = productoServicio.buscarProductoPorId(idProducto);
                    if(producto != null) {
                        logger.info("Producto encontrado: " + producto);
                    } else {
                        logger.info("Producto NO encontrado: " + idProducto);
                    }
                }
                case 3 -> {
                    logger.info("--- Agregar Producto ---" + nl);
                    logger.info("Nombre: ");
                    var nombre = consola.nextLine();
                    logger.info("Existencias: ");
                    var existencias = Integer.parseInt(consola.nextLine());
                    logger.info("Precio: ");
                    var precio = Double.parseDouble(consola.nextLine());
                    var producto = new Producto();
                    producto.setNombre(nombre);
                    producto.setExistencias(existencias);
                    producto.setPrecio(precio);
                    productoServicio.guardarProducto(producto);
                    logger.info("Producto agregado: " + producto);
                }
                case 4 -> {
                    logger.info("--- Modificar Prodcuto ---" + nl);
                    logger.info("Id del producto: ");
                    var idProducto = Integer.parseInt(consola.nextLine());
                    Producto producto = productoServicio.buscarProductoPorId(idProducto);
                    if(producto != null) {
                        logger.info("Nombre: ");
                        var nombre = consola.nextLine();
                        logger.info("Existencias: ");
                        var existencias = Integer.parseInt(consola.nextLine());
                        logger.info("Precio: ");
                        var precio = Double.parseDouble(consola.nextLine());
                        producto.setNombre(nombre);
                        producto.setExistencias(existencias);
                        producto.setPrecio(precio);
                        productoServicio.guardarProducto(producto);
                        logger.info("Producto Modificado: " + producto);
                    } else {
                        logger.info("Producto NO encontrado: " + idProducto);
                    }
                }
                case 5 -> {
                    logger.info("--- Eliminar Prodcuto ---" + nl);
                    logger.info("Id del producto: ");
                    var idProducto = Integer.parseInt(consola.nextLine());
                    Producto producto = productoServicio.buscarProductoPorId(idProducto);
                    if(producto != null) {
                        productoServicio.eliminarProducto(producto);
                        logger.info("Producto Eliminado: " + producto);
                    } else {
                        logger.info("Producto NO encontrado: " + idProducto);
                    }
                }
                case 6 -> {
                    logger.info("Hasta Pronto!!" + nl + nl);
                    salir = true;
                }
                default -> logger.info("Opción no reconocida: " + opcion + nl);
            }
        return salir;
    }
}