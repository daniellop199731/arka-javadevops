package com.bancolombia.arka_javadevops.services.interfaces;

import com.bancolombia.arka_javadevops.models.Producto;

public interface ProductoServiceUtils {

    boolean existeProductoPorReferencia(String referenciaProducto);

    boolean existeProductoPorReferenciaParaActualizar(Producto productoActualizar);

}
