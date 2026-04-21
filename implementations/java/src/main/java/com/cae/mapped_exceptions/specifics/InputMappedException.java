package com.cae.mapped_exceptions.specifics;


import com.cae.mapped_exceptions.MappedException;

public class InputMappedException extends MappedException {

    public InputMappedException(String briefPublicMessage, String details){
        super(briefPublicMessage, details);
    }

    public InputMappedException(String briefPublicMessage){
        super(briefPublicMessage);
    }

    public InputMappedException(String briefPublicMessage, String details, Exception originalException){
        super(briefPublicMessage, details, originalException);
    }

    public InputMappedException(String briefPublicMessage, Exception originalException){
        super(briefPublicMessage, originalException);
    }


}
