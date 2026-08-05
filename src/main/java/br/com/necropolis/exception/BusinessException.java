package br.com.necropolis.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
    
}
