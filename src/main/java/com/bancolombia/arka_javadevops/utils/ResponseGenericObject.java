package com.bancolombia.arka_javadevops.utils;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGenericObject<T> {

    private boolean successfully = false;

    private String msj;

    @JsonIgnore
    private HttpStatus httpStatus;

    private T obj = null;

    public void setOnlyAsSuccessfully(){
        this.successfully = true;
    }

    public void setOnlyAsNotSuccessfully(){
        this.successfully = false;
    }    

    /**
     * Metodo que devuelve una respuesta exitosa con estatus Http 200 por defecto
     * @param msj Mensaje para mostrar String
     * @param obj Objeto tipo T para mostrar
     */
    public void setAsSuccessfully(String msj, T obj){
        this.successfully = true;
        this.setMsj(msj);
        this.setHttpStatus(HttpStatus.OK);
        this.setObj(obj);
    }    

    /**
     * Metodo que devuelve una respuesta exitosa
     * @param msj Mensaje para mostrar String
     * @param httpStatus estatus Http de la peticion
     * @param obj Objeto tipo T para mostrar
     */
    public void setAsSuccessfully(String msj, HttpStatus httpStatus, T obj){
        this.successfully = true;
        this.setMsj(msj);
        this.setHttpStatus(httpStatus);
        this.setObj(obj);
    }

    public void setAsNotSuccessfully(String msj, HttpStatus httpStatus){
        this.successfully = false;
        this.setMsj(msj);
        this.setHttpStatus(httpStatus);
    }    

}
