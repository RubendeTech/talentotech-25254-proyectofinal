package com.talentoTech.ecommerce.controller;

import com.talentoTech.ecommerce.entity.Product;
import com.talentoTech.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//con @RestController hacemos que esta clase se convierta realmente en un controlador
//de las peticiones http que recibimos desde el cliente

@RestController
public class ProductController {

    private final ProductService service;

    //Defino el CONSTRUCTOR:
    //---------------------
    //hacemos inyecciòn de dependencia y delegación de tareas
    //debo anotar con @service a ProductService.java
    public ProductController (ProductService service) {
        this.service = service;
    }

    //configuramos nuestros endpoint (las rutas a las que el usuario ingresa) para ejecutar acciones
    //cuando el usuario entra a localhost:8080/hello springboot lo trae aqui.
    @GetMapping("/hello")
    public String helloWord() {
        return "hola mundo desde spring boot";
    }

    //creacion de un producto:
    @PostMapping("/products")
    public Product crearProducto(@RequestBody Product product) {
        return this.service.crearProducto(product);
    }

    //Obtención de un producto por ID:
    //--------------------------------
    @GetMapping("/products/{id}")
    public Product obtenerProducto(@PathVariable Integer id) {
        return this.service.obtenerProductoPorId(id);
    }

    //Obtención de una lista de productos:
    //------------------------------------
    @GetMapping("/products")
    public List<Product> obtenerTodosLosProductos(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String category ) {
        return this.service.devolverTodosLosProductos(name, category);
    }

    //Modificamos datos de un Producto identificando por su Id:
    //--------------------------------------------------------
    @PutMapping("/products/{id}")
    public Product editarProducto(@PathVariable(name = "id") int productId,
                                  @RequestBody Product prodAModificar) {

        return this.service.modificarProducto(productId, prodAModificar);

    }

    //Eliminamos un Producto identificándolo por su Id:
    //------------------------------------------------
    @DeleteMapping("/products/{id}")
    public Product borrarProducto(@PathVariable int id) {

        return this.service.eliminarProducto(id);

    }
}

//GET para obtener información de un recurso. También podemos Obtener una lista (lista de productos p.e.)
//POST para crear un recurso (crear un producto p.e.)
//PUT  para editar un recurso
//PATCH para hacer modificaciones en un recurso, a veces se utiliza el post igual
//DELETE para eliminar un recurso


