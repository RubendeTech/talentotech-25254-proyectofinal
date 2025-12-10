package com.talentoTech.ecommerce.service;

import com.talentoTech.ecommerce.entity.Product;
import com.talentoTech.ecommerce.repository.ProductRepository;
import com.talentoTech.ecommerce.utils.ProductUtils;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.talentoTech.ecommerce.utils.ProductUtils.isEmpty;


@Service
public class ProductService {

    private final ProductRepository repository;

    private final ProductUtils productUtils;

    ProductService(ProductRepository productRepository, ProductUtils productUtils) {
        this.repository = productRepository;
        this.productUtils = productUtils;
    }

    public Product crearProducto(Product producto) {

        if(productUtils.validarDatosProducto(producto)) {
            //productSaveWithId
            return this.repository.save(producto);
        }
        System.out.println("En la creación, encontró algún error de datos " + producto);
        //si encontró un error de validación envío el producto recibido
        return producto;
    }

    public Product obtenerProductoPorId(Integer id) {
        Optional<Product> productoOptional = this.repository.findById(id);
        if(productoOptional.isEmpty()) {
            throw new RuntimeException("Producto con Id: " + id + " no encontrado");
        }
        return productoOptional.get();
    }

    public List<Product> devolverTodosLosProductos(String name, String categoria) {
        if(!name.isBlank() && !categoria.isBlank()) {
            return this.repository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, categoria);
        }
        if(!name.isBlank()) {
            return this.repository.findByNameContainingIgnoreCase(name);
        }
        if(!categoria.isBlank()) {
            return this.repository.findByCategoryContainingIgnoreCase(categoria);
        }
        return this.repository.findAll();
    }

    public Product modificarProducto(Integer id, Product prodNuevosDatos) {
        Optional<Product> prodOptional = this.repository.findById(id);
        if(prodOptional.isEmpty()) {
            throw new RuntimeException("No se encuentra el producto con Id: " + id);
        }
        String nombre = this.productUtils.quitarEspacios(prodNuevosDatos.getName());
        if(productUtils.validaLongName(nombre)) {
            System.out.println("El nuevo nombre del Producto no cumple con el estándar definidos");
        }
        prodNuevosDatos = prodOptional.get();
        prodNuevosDatos.setName(nombre);
        return this.repository.save(prodNuevosDatos);
    }

    public Product eliminarProducto(Integer id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("No se encuentra el producto a eliminar " + id);
        }
        Product productoAEliminar = optionalProduct.get();
        this.repository.delete(productoAEliminar);
        return productoAEliminar;
    }
}


