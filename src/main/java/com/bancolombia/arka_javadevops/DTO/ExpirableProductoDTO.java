package com.bancolombia.arka_javadevops.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 4: Se crea el DTO de la clase extendida
//SIGUIENTE PASO: Ver ProductoService.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpirableProductoDTO extends ProductoDTO {

    private Date fechaExpiracion;

}
