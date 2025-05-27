package com.bancolombia.arka_javadevops.models;

import java.sql.Date;

import com.bancolombia.arka_javadevops.models.enums.TipoProducto;

import jakarta.persistence.Entity;
import lombok.Data;

//PASO 3: Se crea la nueva clase que extiende de Producto
//SIGUIENTE PASO: Ver ExpirableProductoDTO.java
@Entity
@Data
public class ExpirableProducto extends Producto {

    public ExpirableProducto(){
        super.tipoProducto = TipoProducto.EXPIRABLE;
    }

    public ExpirableProducto(Date fechaExpiracion){
        super.tipoProducto = TipoProducto.EXPIRABLE;
        this.fechaExpiracion = fechaExpiracion;
    }

    private Date fechaExpiracion;

}
