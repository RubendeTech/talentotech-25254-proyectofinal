package com.talentoTech.ecommerce.service;

import com.talentoTech.ecommerce.model.Product;
import com.talentoTech.ecommerce.repository.ProductRepository_antes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//springboot lo carga en su almacén de "beans"
//_Service
public class ProductService_antes {

    private ProductRepository_antes repository;

    //inyección de dependencia y delegacion de tareas anotando con @repository a ProductRepository.java
    //springboot al instanciar repository -constructor mediante, creó todos los productos iniciales
    public ProductService_antes(ProductRepository_antes repository) {
        this.repository = repository;

    }

    public Product crearProducto(Product producto) {
        System.out.println("Creando Nuevo Product");
        return this.repository.guardarProducto(producto);

    }
/*
    public ArrayList<Product> listarProductos() {
        return this.repository.obtenerProductos();
    }

 */

    public ArrayList<Product> listarProductos(String nombre, double precio) {

        if (!nombre.isBlank() && precio > 0.00) {
            return this.repository.obtenerProductosPorNombreYPrecio(nombre, precio);
        }
        if(!nombre.isBlank()) {
            return this.repository.obtenerProductosPorNombre(nombre);
        }
        if(precio > 0) {
            return this.repository.obtenerProductosPorPrecio(precio);
        }
        return this.repository.obtenerProductos();
    }

/*



    public static void buscarProductPorNombre(ArrayList<Product> products) {
        System.out.println("Ingrese un nombre de un producto: ");
        String busqueda = entrada.nextLine();
        ArrayList<Product> productosEncontrados = new ArrayList<>();

        for (Product product : products) {
            if (estaIncluido(product.getNombre(), busqueda)) {
                productoEncontrados.add(producto);
            }
        }

        listarProducts(productoEncontrados);
    }

*/
    public Product editarNombreProducto(int id, Product dataProducto) {

        //Validaciones:
        if(dataProducto.getNombre() == null || dataProducto.getNombre().isBlank()) {
            System.out.println("en la modificacion no se informó el nuevo nombre");
            return null;
        }

        Product productoParaModificar = this.repository.buscarProductoPorId(id);

        if (productoParaModificar == null) {
            System.out.println("No se encontró el producto a modificar.");
            return null;
        }
        //Fin validaciones

        productoParaModificar.setNombre(dataProducto.getNombre());

        this.repository.actualizarProducto(productoParaModificar);

        return productoParaModificar;

    }


    public Product borrarProducto(int id) {

        Product productoParaBorrar = this.repository.buscarProductoPorId(id);

        if (productoParaBorrar == null) {
            System.out.println("No se encuentra el producto a borrar " + id);
            return null;
        }
        // aca borramos el producto
        this.repository.borrarProducto(productoParaBorrar);
        System.out.println("Se borró el producto " + id + " " + productoParaBorrar.getNombre());
        return productoParaBorrar;
    }
/*
    public static void filtroPorPrecio(List<Product> products) {
        double precioFiltro = entrada.nextDouble();

        ArrayList<Product> productsFiltrados = new ArrayList<>();

        for (Product producto : products) {
            if (producto.getPrecio() <= precioFiltro) {
                productsFiltrados.add(producto);
            }
        }

        listarProducts(productsFiltrados);
    }

    // UTILIDADES
    // Busqueda por id - ahora mismo solo funciona con el indice, en el futuro se va a cambiar
    public static Product obtenerProductPorId(List<Product> products) {

        System.out.println("Ingrese el id del producto: ");
        int idBusqueda = entrada.nextInt();

        for (Product producto : products) {
            if (producto.coincideId(idBusqueda)) {
                return producto;
            }
        }

        System.out.println("No pudimos encontrar el producto con el id: " + idBusqueda);
        return null; // el null representa que no encontramos el producto
    }

    public static boolean estaIncluido(String nombreCompleto, String nombreParcial) {
        String nombreCompletoFormateado = formatoBusqueda(nombreCompleto);

        return nombreCompletoFormateado.contains(formatoBusqueda(nombreParcial));
    }

    public static String formatoBusqueda(String texto) {
        return texto.trim().toLowerCase();
    }

    public static void pausa() {
        System.out.println("Pulse ENTER para continuar...");
        entrada = new Scanner(System.in);
        entrada.nextLine();
        for (int i = 0; i < 20; ++i) {
            System.out.println();
        }
    }

*/
}
