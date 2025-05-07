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
}
