package com.cae.mapped_exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MappedException extends RuntimeException{

    protected final String briefPublicMessage;

    protected final String details;

    protected final Exception originalException;

    public MappedException(String briefPublicMessage, String details){
        super(briefPublicMessage + " | " + details);
        this.briefPublicMessage = briefPublicMessage;
        this.details = details;
        this.originalException = null;
    }

    public MappedException(String briefPublicMessage){
        super(briefPublicMessage);
        this.briefPublicMessage = briefPublicMessage;
        this.details = null;
        this.originalException = null;
    }

    public MappedException(String briefPublicMessage, String details, Exception originalException){
        super(briefPublicMessage + " | " + details + " | Original: " + originalException);
        this.briefPublicMessage = briefPublicMessage;
        this.details = details;
        this.originalException = originalException;
    }

    public MappedException(String briefPublicMessage, Exception originalException){
        super(briefPublicMessage + " | Original: " + originalException);
        this.briefPublicMessage = briefPublicMessage;
        this.details = null;
        this.originalException = originalException;
    }

    public String getBriefPublicMessage(){
        return this.briefPublicMessage;
    }

    /**
     * Getter method for the more detailed explanation of the exception.
     * @return Optional of the detailed message.
     */
    public Optional<String> getDetails() {
        return Optional.ofNullable(this.details);
    }

    public Optional<Exception> getOriginalException(){
        return Optional.ofNullable(this.originalException);
    }

    public List<String> getLinesFromStackTraceFromOriginalException(Integer numberOfLines){
        return Optional.ofNullable(this.originalException)
                .map(originalE -> this.getLinesFromStackTraceAsString(originalE, numberOfLines))
                .orElse(new ArrayList<>());
    }

    public List<String> getLinesFromStackTrace(Integer numberOfLines){
        return this.getLinesFromStackTraceAsString(this, numberOfLines);
    }

    public List<String> getLinesFromStackTraceAsString(Exception exception, Integer numberOfLines){
        var allLines = List.of(exception.getStackTrace());
        var linesToReturn = new ArrayList<String>();
        var counter = 0;
        var maxCount = allLines.size() < numberOfLines? allLines.size() : numberOfLines;
        while (counter < maxCount){
            var line = allLines.get(counter);
            linesToReturn.add(line.toString());
            counter ++;
        }
        if (allLines.size() > numberOfLines){
            var lastLine = allLines.size() - numberOfLines + " hidden line(s)";
            linesToReturn.add(lastLine);
        }
        return linesToReturn;
    }

    protected String getFullStackTraceAsString(){
        var stringWriter = new StringWriter();
        this.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
