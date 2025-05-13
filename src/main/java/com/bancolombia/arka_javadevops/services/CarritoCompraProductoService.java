package com.bancolombia.arka_javadevops.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.CarritoCompraProductoRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoCompraProductoService {

    private final CarritoCompraProductoRepository carritoCompraProductoRepository;

    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final CarritoCompraService carritoCompraService;

    private static ResponseObject rObj;

    public ResponseObject obtenerProductosCarrito(int idCarrito){
        rObj = new ResponseObject();
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(idCarrito);
        List<CarritoCompraProducto> carritoCompraProductos = carritoCompraProductoRepository.findByCarritoCompra(carritoCompra);
        rObj.setAsSuccessfully();
        rObj.setObj(carritoCompraProductos);
        if(carritoCompraProductos.isEmpty()){
            rObj.setMsj("El carrito no tiene productos");
        } else {
            rObj.setMsj("Consulta ejecutada con exito");
        }
        return rObj;
    }

    public ResponseObject agregarProductoCarrito(int idUsuario, int idProducto, int unidades){
        rObj = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(!rObj.getSuccessfully()){
            return rObj;
        }
        rObj = productoService.obtenerProductoPorId(idProducto);
        if(!rObj.getSuccessfully()){
            return rObj;
        }
        if(unidades <= 0){
            rObj.setAsNotSuccessfully();
            rObj.setMsj("Las unidades deben ser mayores a cero");
            rObj.setObj("");
            return rObj;
        }
        Producto producto = (Producto) rObj.getObj();

        rObj = carritoCompraService.carritoActual(idUsuario);
        if(!rObj.getSuccessfully()){
            rObj = carritoCompraService.crearNuevo(idUsuario);
        }

        CarritoCompra carritoCompra = (CarritoCompra) rObj.getObj();
        CarritoCompraProducto carritoCompraProducto = new CarritoCompraProducto();

        carritoCompraProducto.setCarritoCompra(carritoCompra);
        carritoCompraProducto.setProductoCarritoCompra(producto);
        carritoCompraProducto.setUnidadesProducto(unidades);

        rObj.setAsSuccessfully();
        rObj.setMsj("Producto agregado al carrito");
        rObj.setObj(carritoCompraProductoRepository.save(carritoCompraProducto));
        return rObj;
    }

}
