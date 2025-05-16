package com.bancolombia.arka_javadevops.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 2: Se crea extension de UsuarioDTO (ExpirableUsuarioDTO)
//Siguiente paso: ver UsuarioMapper
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpirableUsuarioDTO extends UsuarioDTO {

    private Date fechaExpiracion;

}
