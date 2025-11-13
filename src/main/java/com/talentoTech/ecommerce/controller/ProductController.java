package com.talentoTech.ecommerce.controller;

import com.talentoTech.ecommerce.model.Product;
import com.talentoTech.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//con RestController hacemos que esta clase se convierta realmente un controlador
// de las peticiones http que van a recibir desde el cliente
@RestController
public class ProductController {

    private ProductService service;

    //hacemos inyecciòn de dependencia y delegacion de tareas
    //anotando como @service a ProductService.java
    public ProductController (ProductService service) {
        this.service = service;
    }

    //configuramos nuestros endpoint (las rutas a las que el usuario ingresa) para ejecutar acciones
    //cuando el usuario entra a localhost:8080/hello springboot lo trae aqui.
    @GetMapping("/hello")
    public String helloWord() {
        return "hola mundo desde spring boot";
    }

    @PostMapping("/products")
    public Product crearProducto(@RequestBody Product product) {
        return this.service.crearProducto(product);
    }

    //ArrayList
    //formas de enviar datos: path variable, tambièn con ?nombre="fadfaf"&precio="123" y se recuperan con requestParam
    @GetMapping("/products")
    public List<Product> listarProductos(
               @RequestParam(required = false, defaultValue = "") String nombre,
               @RequestParam(required = false, defaultValue = "0") Double precio) {
        //return List.of(nombre, String.valueOf(precio));
        return this.service.listarProductos(nombre, precio);
    }
/*
    @GetMapping("/products")
    public List<String> listarProductos(@RequestParam String nombre) {
        return List.of("Producto1", nombre);
    }

 */
/*
    @GetMapping("/products/precio")
    public List<String> listarProductos(@RequestParam int precio) {
        return List.of("Producto1", String.valueOf(precio));
    }
    */

    //fix
    /*
    @GetMapping("/products")
    public List<String> buscarProductoPorNombre() {
        return List.of("producto1");
    }
    */
    @PutMapping("/products/{id}")
    public Product editarProducto(@PathVariable(name = "id") int productId,
                                  @RequestBody Product prodAModificar) {

        return this.service.editarNombreProducto(productId, prodAModificar);

    }

    @DeleteMapping("/products/{id}")
    public Product borrarProducto(@PathVariable int id) {

        return this.service.borrarProducto(id);

    }
}
//GET para obtener infrmaciòn del servidor. listado, obtener producto por nombre
//POST para crear un producto p.e.
//PUT editar el producto
//PATCH para hacer modificaciones, a veces se utiliza el post igual
//DELETE


