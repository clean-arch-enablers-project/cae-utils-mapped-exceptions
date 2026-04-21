package com.cae.mapped_exceptions.specifics;

import com.cae.mapped_exceptions.MappedException;

public class NotAuthenticatedMappedException extends MappedException {

    public NotAuthenticatedMappedException(String briefPublicMessage, String details) {
        super(briefPublicMessage, details);
    }

    public NotAuthenticatedMappedException(String briefPublicMessage) {
        super(briefPublicMessage);
    }

    public NotAuthenticatedMappedException(String briefPublicMessage, String details, Exception originalException){
        super(briefPublicMessage, details, originalException);
    }

    public NotAuthenticatedMappedException(String briefPublicMessage, Exception originalException){
        super(briefPublicMessage, originalException);
    }

}
