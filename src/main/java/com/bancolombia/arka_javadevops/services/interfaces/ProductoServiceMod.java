package com.bancolombia.arka_javadevops.services.interfaces;

import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

public interface ProductoServiceMod {

    ResponseObject actualizar(int idProducto, Producto producto);

    ResponseObject eliminar(int idProducto);

    ResponseObject descontarUnidadesStock(int idProducto, Producto producto, int unidades);

}
