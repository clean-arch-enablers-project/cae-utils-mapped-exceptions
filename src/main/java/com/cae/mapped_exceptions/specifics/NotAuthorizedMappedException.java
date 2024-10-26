package com.cae.mapped_exceptions.specifics;

import com.cae.mapped_exceptions.MappedException;

public class NotAuthorizedMappedException extends MappedException {

    public NotAuthorizedMappedException(String briefPublicMessage, String details) {
        super(briefPublicMessage, details);
    }

    public NotAuthorizedMappedException(String briefPublicMessage) {
        super(briefPublicMessage);
    }

    public NotAuthorizedMappedException(String briefPublicMessage, String details, Exception originalException){
        super(briefPublicMessage, details, originalException);
    }

    public NotAuthorizedMappedException(String briefPublicMessage, Exception originalException){
        super(briefPublicMessage, originalException);
    }

}
