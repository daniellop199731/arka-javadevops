package com.bancolombia.arka_javadevops.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGenericObject<T> {

    private boolean successfully = false;

    private String msj;

    private T obj = null;

    public void setOnlyAsSuccessfully(){
        this.successfully = true;
    }

    public void setOnlyAsNotSuccessfully(){
        this.successfully = false;
    }    

    /**
     * Metodo que devuelve una respuesta exitosa
     * @param msj Mensaje para mostrar String
     * @param obj Objeto tipo T para mostrar
     */
    public void setAsSuccessfully(String msj, T obj){
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
    }    

}
