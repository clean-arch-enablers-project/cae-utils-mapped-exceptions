package com.cae.mapped_exceptions.specifics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotFoundMappedExceptionTest {

    @Test
    void shouldReturnTheBriefPublicMessageSet(){
        var briefPublicMessage = "That's a public, brief, message of the exception";
        var testSubject = new NotFoundMappedException(briefPublicMessage);
        Assertions.assertEquals(briefPublicMessage, testSubject.getBriefPublicMessage());
    }

    @Test
    void shouldNotSetDetailsWhenOnlyTheBriefPublicMessageWasSet(){
        var briefPublicMessage = "That's a public, brief, message of the exception again";
        var testSubject = new NotFoundMappedException(briefPublicMessage);
        Assertions.assertFalse(testSubject.getDetails().isPresent());
    }

    @Test
    void shouldReturnTheDetailsSet(){
        var briefPublicMessage = "Once again, a public, brief, message of the exception";
        var details = "woah! These are some nasty details that should be kept internally available";
        var testSubject = new NotFoundMappedException(briefPublicMessage, details);
        Assertions.assertEquals(briefPublicMessage, testSubject.getBriefPublicMessage());
        Assertions.assertTrue(testSubject.getDetails().isPresent());
        Assertions.assertEquals(details, testSubject.getDetails().get());
    }

    @Test
    void shouldSetTheFullMessageCorrectlyWhenOnlyABriefMessageIsSet(){
        var briefPublicMessage = "Once again, a public, brief, message of the exception";
        var testSubject = new NotFoundMappedException(briefPublicMessage);
        Assertions.assertEquals(briefPublicMessage, testSubject.getMessage());
    }

    @Test
    void shouldSetTheFullMessageCorrectlyWhenBothBriefAndDetailsTextsAreSet(){
        var briefPublicMessage = "There we go one more time: another public, brief, message of the exception";
        var details = "Even nastier details that should be kept internally available";
        var testSubject = new NotFoundMappedException(briefPublicMessage, details);
        Assertions.assertEquals(briefPublicMessage + " | " + details, testSubject.getMessage());
    }

    @Test
    void shouldReturnTheOriginalExceptionSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Well... at least that's a fully new kind of scenario for me, a brief, public message of a mapped exception";
        var testSubject = new NotFoundMappedException(briefPublicMessage, originalException);
        Assertions.assertTrue(testSubject.getOriginalException().isPresent());
        Assertions.assertEquals(originalException, testSubject.getOriginalException().get());
    }

    @Test
    void shouldCorrectlySetTheGeneralMessageWhenTheOriginalExceptionSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Well... that's a kinda of new scenario for me still";
        var testSubject = new NotFoundMappedException(briefPublicMessage, originalException);
        Assertions.assertEquals(briefPublicMessage + " | Original: " + originalException, testSubject.getMessage());
    }

    @Test
    void shouldCorrectlySetTheGeneralMessageWhenBothTheOriginalExceptionAndDetailsAreSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Okay...";
        var details = "Hello world! ^^";
        var testSubject = new NotFoundMappedException(briefPublicMessage, details, originalException);
        Assertions.assertEquals(briefPublicMessage + " | " + details + " | Original: " + originalException, testSubject.getMessage());
    }

    @Test
    void shouldNotSetAnyOriginalExceptionWhenNoneIsProvided(){
        var briefPublicMessage = "Right...";
        var details = "Olá mundo! ><";
        var testSubjectWithDetails = new NotFoundMappedException(briefPublicMessage, details);
        var testSubjectWithoutDetails = new NotFoundMappedException(briefPublicMessage);
        Assertions.assertFalse(testSubjectWithoutDetails.getOriginalException().isPresent());
        Assertions.assertFalse(testSubjectWithDetails.getOriginalException().isPresent());
    }
    
}
