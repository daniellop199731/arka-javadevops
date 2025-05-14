package com.bancolombia.arka_javadevops.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.CarritoCompraProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoCompraProductoService {

    private final CarritoCompraProductoRepository carritoCompraProductoRepository;

    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final CarritoCompraService carritoCompraService;

    public List<CarritoCompraProducto> obtenerProductosCarrito(int idCarrito){
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(idCarrito);
        return carritoCompraProductoRepository.findByCarritoCompra(carritoCompra);
    }

    public List<CarritoCompraProducto> carritosPorProducto(int idProducto){
        Producto producto = productoService.obtenerProductoPorId(idProducto);
        if( producto != null){
            return carritoCompraProductoRepository.findByProductoCarritoCompra(producto);
        }
        return null;
    }

    public Object agregarProductoCarrito(int idUsuario, int idProducto, int unidades){
        if(usuarioService.obtenerUsuarioPorId(idUsuario) == null){
            return "No existe el usuario con el id ".concat(idUsuario+"");
        }

        Producto producto = productoService.obtenerProductoPorId(idProducto);
        if(producto == null){
            return "No existe el producto con el id ".concat(idProducto+"");
        }

        if(unidades <= 0){
            return "Las unidades deben ser mayores a cero";
        }

        if(producto.getStockProducto() < unidades){
            return "No existen suficientes unidades de ".concat(producto.getNombreProducto());
        }

        CarritoCompra carritoCompra = carritoCompraService.carritoActual(idUsuario);
        if(carritoCompra == null){
            carritoCompra = carritoCompraService.crearNuevo(idUsuario);
        }

        if(carritoCompra != null){
            
            CarritoCompraProducto carritoCompraProducto = new CarritoCompraProducto();

            carritoCompraProducto.setCarritoCompra(carritoCompra);
            carritoCompraProducto.setProductoCarritoCompra(producto);
            carritoCompraProducto.setUnidadesProducto(unidades);
            
            if(productoService.descontarUnidadesStock(idProducto, producto, unidades)){
                carritoCompraProductoRepository.save(carritoCompraProducto);
                return true;
            }
            return null;
        }  
        return null;
    }

}
