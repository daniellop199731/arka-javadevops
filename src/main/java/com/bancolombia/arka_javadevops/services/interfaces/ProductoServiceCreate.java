package com.bancolombia.arka_javadevops.services.interfaces;

import com.bancolombia.arka_javadevops.models.ExpirableProducto;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

public interface ProductoServiceCreate {

    ResponseObject crearNuevo(Producto producto);

    ResponseObject crearNuevoV2(ExpirableProducto producto);

    ResponseObject crearNuevoDto(Producto producto);

}
