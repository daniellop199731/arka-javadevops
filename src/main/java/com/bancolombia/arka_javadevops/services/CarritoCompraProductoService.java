package com.bancolombia.arka_javadevops.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.mappers.CarritoCompraProductoMapper;
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
    private final CarritoCompraProductoMapper carritoCompraProductoMapper;

    private static ResponseObject rObj;
    private static ResponseGenericObject<List<CarritoCompraProducto>> rgObjCarritoCompraProductos;
    

    public ResponseObject obtenerProductosCarrito(int idCarrito){
        rObj = new ResponseObject();
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(idCarrito);
        List<CarritoCompraProducto> carritoCompraProductos = carritoCompraProductoRepository.findByCarritoCompra(carritoCompra);
        if(carritoCompraProductos.isEmpty()){
            rObj.setAsNotSuccessfully("El carrito o no existe no tiene productos");
            return rObj;
        }
        rObj.setAsSuccessfully("Consulta ejecutada con exito"
            , carritoCompraProductoMapper.toDTO(carritoCompraProductos));
        return rObj;
    }

    public ResponseGenericObject<List<CarritoCompraProducto>> obtenerProductosCarritoWithoutDTO(int idCarrito){
        rgObjCarritoCompraProductos = new ResponseGenericObject<>();
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(idCarrito);
        List<CarritoCompraProducto> carritoCompraProductos = carritoCompraProductoRepository.findByCarritoCompra(carritoCompra);
        if(carritoCompraProductos.isEmpty()){
            rgObjCarritoCompraProductos.setAsNotSuccessfully("El carrito o no existe no tiene productos");
            return rgObjCarritoCompraProductos;
        }
        rgObjCarritoCompraProductos.setAsSuccessfully("Consulta ejecutada con exito"
            , carritoCompraProductos);
        return rgObjCarritoCompraProductos;
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

    public ResponseObject eliminarProductosCarrito(int idCarrito){
        rObj = new ResponseObject();
        rgObjCarritoCompraProductos = this.obtenerProductosCarritoWithoutDTO(idCarrito);
        if(rgObjCarritoCompraProductos.isSuccessfully()){
            carritoCompraProductoRepository.deleteAll(rgObjCarritoCompraProductos.getObj());
            rObj.setAsSuccessfully("Productos eliminados con exito", null);
        }
        return rObj;
    }    

    public ResponseObject agregarProductoCarrito(int idUsuario, List<CarritoCompraProducto> carritoCompraProductos){
        rObj = new ResponseObject();
        rObj = usuarioService.obtenerUsuarioPorIdWitOutDto(idUsuario);
        if(!rObj.getSuccessfully()){
            return rObj;
        }

        if(carritoCompraProductos.size() <= 0){
            rObj.setAsNotSuccessfully("No hay productos para agregar");
            return rObj;
        }

        rObj = carritoCompraService.carritoActual(idUsuario);
        if(!rObj.getSuccessfully()){
            rObj = carritoCompraService.crearNuevo(idUsuario);
        }      
        
        if(rObj.getSuccessfully()){        

            CarritoCompra carritoCompraActual = (CarritoCompra) rObj.getObj();
            List<CarritoCompraProducto> productosParaCarrito = new ArrayList<>();
            
            this.eliminarProductosCarrito(carritoCompraActual.getIdCarritoCompra());

            for (CarritoCompraProducto carritoCompraProducto : carritoCompraProductos) {
                rObj = productoService.obtenerProductoPorId(carritoCompraProducto.getProductoCarritoCompra().getIdProducto());

                if(!rObj.getSuccessfully()){
                    return rObj;
                }

                Producto producto = (Producto) rObj.getObj();
                if(carritoCompraProducto.getUnidadesProducto() <= 0){                    
                    rObj.setAsNotSuccessfully(
                        "Para el producto con id ".concat(producto.getIdProducto()+"")
                        .concat(" no se asignaron unidades")
                    );
                    return rObj;                
                }

                carritoCompraProducto.setCarritoCompra(carritoCompraActual);
                carritoCompraProducto.setProductoCarritoCompra(producto);
                productosParaCarrito.add(carritoCompraProducto);
            }
            rObj.setAsSuccessfully("Productos agregados con exito"
            , carritoCompraProductoMapper.toDTO(
                (List<CarritoCompraProducto>) carritoCompraProductoRepository.saveAll(productosParaCarrito)
                )
            );
        }
        return rObj;
    }

}
