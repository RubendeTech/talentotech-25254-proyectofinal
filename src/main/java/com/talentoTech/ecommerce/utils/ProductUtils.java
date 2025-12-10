package com.talentoTech.ecommerce.utils;

import com.talentoTech.ecommerce.entity.Product;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
    public class ProductUtils {
        private final int LONG_NAME = 50;
        private final int LONG_DESCRIPTION = 150;
    public boolean validarDatosProducto(Product producto) {
        System.out.println("Validando producto " + producto);
        if(producto == null) {
            throw new ValidationException("Se debe informar los datos del producto a crear");
        }

        if(isEmpty(producto.getName())) {
            throw new ValidationException("Debe informar nombre del producto");
        }

        String strValido = quitarEspacios(producto.getName());
        if(!validaLongName(strValido)) {
            throw new ValidationException("El nombre del producto no debe superar los " + LONG_NAME + " caracteres " + strValido);
        }
        producto.setName(strValido);

        if(isEmpty(producto.getDescription())) {
            throw new ValidationException("Debe informar la descripción del producto");
        }

        strValido = producto.getDescription();
        if(!validaLongDescription(strValido)) {
            throw new ValidationException("La descripción del producto no debe superar los " + LONG_DESCRIPTION + " caracteres " + strValido);
        }
        producto.setDescription(strValido);

        if(!validaPrecio(producto.getPrice())) {
            throw new ValidationException("El precio del producto debe ser mayor a cero");
        }

        return true;
    }

    public static boolean isEmpty(String textoAValidar){
        return textoAValidar == null || textoAValidar.isBlank();
    }

    public String quitarEspacios(String string) {
        return string.trim();

    }
    public boolean validaLongName(String name){
        return name.length() <= LONG_NAME;
    }

    public boolean validaLongDescription(String name){
        return name.length() <= LONG_DESCRIPTION;
    }

    public boolean validaPrecio(Double precio){
        return precio != null && precio > 0;
    }
}
