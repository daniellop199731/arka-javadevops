package com.bancolombia.arka_javadevops.utils;

public class ResponseObject {

    private Boolean successfully = false;
    private String msj;
    private Object obj;    

    public ResponseObject() {
    }

    public ResponseObject(String msj, Object obj) {        
        this.successfully = false;
        this.msj = msj;
        this.obj = obj;        
    }

    public Boolean getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(Boolean successfully) {
        this.successfully = successfully;
    }

    public void setAsSuccessfully(){
        this.successfully = true;
    }

    public void setAsNotSuccessfully(){
        this.successfully = false;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }    
}
