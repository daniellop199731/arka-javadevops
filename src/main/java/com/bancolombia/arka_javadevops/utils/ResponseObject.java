package com.bancolombia.arka_javadevops.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {

    private Boolean successfully = false;
    private Object msj;
    private Object obj;  

    public void setAsSuccessfully(){
        this.successfully = true;
    }

    public void setAsNotSuccessfully(){
        this.successfully = false;
    }

    /**
     * Metodo que devuelve una respuesta exitosa
     * @param msj Mensaje para mostrar String
     * @param obj Objeto tipo T para mostrar
     */
    public void setAsSuccessfully(String msj, Object obj){
        this.successfully = true;
        this.setMsj(msj);
        this.setObj(obj);
    }

    /**
     * Metodo que devuelve una respuesta no exitosa
     * @param msj Mensaje para mostrar String
     * @param obj Objeto tipo T para mostrar
     */    
    public void setAsNotSuccessfully(String msj){
        this.successfully = false;
        this.setMsj(msj);
        this.setObj(null);
    }     
}
