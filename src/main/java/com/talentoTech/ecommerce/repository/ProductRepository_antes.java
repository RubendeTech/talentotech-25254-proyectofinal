package com.talentoTech.ecommerce.repository;

import com.talentoTech.ecommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//_Repository
public class ProductRepository_antes {

    private static int nextId =1;

    ArrayList<Product> productos;

    public ProductRepository_antes() {

        this.productos = obtenerProductosTecnologicos();
    }

    private ArrayList<Product> obtenerProductosTecnologicos() {
        ArrayList<Product> productos = new ArrayList<>();

        productos.add(new Product(
                "Laptop Lenovo ThinkPad X1 Carbon",
                1899.99,
                "Ultrabook empresarial de alto rendimiento con diseño liviano y duradero.",
                "Computadoras"));

        productos.add(new Product(
                "Mouse inalámbrico LG Logitech MX Master 3",
                99.99,
                "Mouse LG ergonómico con múltiples botones programables y conectividad Bluetooth.",
                "Accesorios"));

        productos.add(new Product(
                "Teclado mecánico Razer BlackWidow V4",
                179.99,
                "Teclado gaming mecánico con retroiluminación RGB y teclas programables.",
                "Periféricos"));

        productos.add(new Product(
                "Monitor Samsung UltraWide 34 pulgadas",
                499.99,
                "Monitor panorámico con resolución QHD ideal para multitarea y productividad.",
                "Monitores"));

        productos.add(new Product(
                "Smartphone Samsung Galaxy S23 Ultra",
                1199.99,
                "Teléfono inteligente de gama alta con cámara de 200 MP y pantalla AMOLED.",
                "Smartphones"));

        productos.add(new Product(
                "Tablet Apple iPad Pro 12.9\"",
                1399.99,
                "Tableta potente con chip M2 y pantalla Liquid Retina XDR.",
                "Tablets"));

        productos.add(new Product(
                "Disco duro LG externo Seagate 2TB",
                79.99,
                "Almacenamiento portátil confiable con conectividad USB 3.0.",
                "Almacenamiento"));

        productos.add(new Product(
                "Memoria RAM Corsair Vengeance 16GB",
                69.99,
                "Módulo de memoria DDR4 ideal para gamers y entusiastas del rendimiento.",
                "Componentes"));

        productos.add(new Product(
                "Cargador inalámbrico Samsung Boost Up",
                39.99,
                "Base de carga inalámbrica rápida compatible con dispositivos Qi.",
                "Accesorios"));

        productos.add(new Product(
                "Auriculares Bluetooth Sony WH-1000XM5",
                349.99,
                "Auriculares con cancelación activa de ruido y audio de alta resolución.",
                "Audio"));

        for(Product producto : productos) {
            this.updateId(producto);
        }
        return productos;
    }

    public ArrayList<Product> obtenerProductos() {
        return this.productos;
    }
/*
    public Product obtenerProductById(int id) {
        for(Product producto : this.productos) {
            if(producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

 */

    public ArrayList<Product> obtenerProductosPorNombreYPrecio(String nombre, double precio) {
        ArrayList<Product> productosEncontrados = new ArrayList<>();
        for(Product producto : this.productos) {
            if(siEstaIncluido(producto.getNombre(), nombre) && producto.getPrecio() <= precio) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    public ArrayList<Product> obtenerProductosPorNombre(String nombre) {
        ArrayList<Product>  productosEncontrados = new ArrayList<>();
        for(Product producto : this.productos){
            if(siEstaIncluido(producto.getNombre(), nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    public ArrayList<Product> obtenerProductosPorPrecio(double precio) {
        ArrayList<Product> productosEncontrados = new ArrayList<>();
        for(Product producto : this.productos) {
            if(producto.getPrecio() <= precio) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    public Product buscarProductoPorId(int id) {
        for(Product producto : this.productos) {
            if(producto.getId() == id) {
                return producto;
            }
        }
        System.out.println("en buscarProductoPorId: no se encuentra " + id);
        return null;
    }

    public Product borrarProducto(Product productoEliminado) {
        this.productos.remove(productoEliminado);
        return productoEliminado;
    }

    public void actualizarProducto(Product producto) {
        System.out.println("Se actualizó el producto " + producto.getId() + " " + producto.getNombre());
    }

    public Product guardarProducto(Product producto) {
        this.updateId(producto);
        productos.add(producto);
        return producto;
    }


    public void updateId(Product producto) {
        producto.setId(nextId);
        nextId++;
    }

    public boolean siEstaIncluido(String nombreCompleto, String nombreABuscar) {
        String ncformateado = formatoBusqueda(nombreCompleto);
        return ncformateado.contains(formatoBusqueda(nombreABuscar));
    }

    public String formatoBusqueda(String texto) {
        return texto.trim().toLowerCase();
    }
}
