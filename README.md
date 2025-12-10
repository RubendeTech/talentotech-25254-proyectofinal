Java Backend - Talent Tech

La API permite las siguientes acciones:

> Alta de un producto: POST http://localhost:8080/products
body:
{
    "name" : "mouse gamer plus Nuevo",
    "description": "mouse plus description",
    "price": 12,
    "category": "tecno"
}
> Listar Productos: GET http://localhost:8080/products

> Obtener un producto: http://localhost:8080/products/20 (donde el 20 es el ID del producto)

> Editar un Producto (solo se puede modificar el nombre de un producto existente: PUT http://localhost:8080/products/25 (donde el 25 es el ID del producto)
body:
{
    "name" : "Puzzle 3D City Modificado"
}

> Eliminar un Producto existente: PUT http://localhost:8080/products/25 (donde el 25 es el ID del producto)

Me quedó por incorporar el manejo de errores, por lo tanto si se intenta acceder a un producto inexistente, la api finaliza con error 500 y en el log de la consola se emite el mensaje real del error encontrado.
