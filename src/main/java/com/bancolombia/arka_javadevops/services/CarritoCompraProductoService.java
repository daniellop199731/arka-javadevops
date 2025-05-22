package com.bancolombia.arka_javadevops.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.CarritoCompraProductoRepository;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
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
    private static ResponseGenericObject<UsuarioDTO> rgObjUsuarioDto;

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

    public ResponseObject carritosPorProducto(int idProducto){
        rObj = productoService.obtenerProductoPorId(idProducto);
        if(rObj.getSuccessfully()){
            List<CarritoCompraProducto> carritoCompraProductos = carritoCompraProductoRepository.findByProductoCarritoCompra((Producto) rObj.getObj());
            if(carritoCompraProductos.isEmpty()){
                rObj.setAsSuccessfully();
                rObj.setMsj("No se encontraron carritos con el producto ");
                rObj.setObj(carritoCompraProductos);
            }
            rObj.setAsSuccessfully();
            rObj.setMsj("Carritos encontrados");
            rObj.setObj(carritoCompraProductos);
        }
        
        return rObj;
    }

    public ResponseObject agregarProductoCarrito(int idUsuario, int idProducto, int unidades){
        rObj = new ResponseObject();
        rObj = usuarioService.obtenerUsuarioPorIdWitOutDto(idUsuario);
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

        if(producto.getStockProducto() < unidades){
            rObj.setAsNotSuccessfully();
            rObj.setMsj("No existen suficientes unidades de ".concat(producto.getNombreProducto()));
            rObj.setObj("");
            return rObj;
        }

        rObj = carritoCompraService.carritoActual(idUsuario);
        if(!rObj.getSuccessfully()){
            rObj = carritoCompraService.crearNuevo(idUsuario);
        }

        if(rObj.getSuccessfully()){
            CarritoCompra carritoCompra = (CarritoCompra) rObj.getObj();
            CarritoCompraProducto carritoCompraProducto = new CarritoCompraProducto();

            carritoCompraProducto.setCarritoCompra(carritoCompra);
            carritoCompraProducto.setProductoCarritoCompra(producto);
            carritoCompraProducto.setUnidadesProducto(unidades);
            
            rObj = productoService.descontarUnidadesStock(idProducto, producto, unidades);
            if(rObj.getSuccessfully()){
                rObj.setAsSuccessfully();
                rObj.setMsj("Producto agregado al carrito de compras con éxito");
                rObj.setObj(carritoCompraProductoRepository.save(carritoCompraProducto));
            }
            return rObj;

        }  
        return rObj;
    }

}
