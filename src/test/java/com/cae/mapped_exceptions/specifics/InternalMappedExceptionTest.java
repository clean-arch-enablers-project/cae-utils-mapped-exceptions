package com.cae.mapped_exceptions.specifics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InternalMappedExceptionTest {

    @Test
    void shouldReturnTheDetailsSet(){
        var briefPublicMessage = "Once again, a public, brief, message of the exception";
        var details = "woah! These are some nasty details that should be kept internally available";
        var testSubject = new InternalMappedException(briefPublicMessage, details);
        Assertions.assertEquals(briefPublicMessage, testSubject.getBriefPublicMessage());
        Assertions.assertTrue(testSubject.getDetails().isPresent());
        Assertions.assertEquals(details, testSubject.getDetails().get());
    }

    @Test
    void shouldSetTheFullMessageCorrectlyWhenBothBriefAndDetailsTextsAreSet(){
        var briefPublicMessage = "There we go one more time: another public, brief, message of the exception";
        var details = "Even nastier details that should be kept internally available";
        var testSubject = new InternalMappedException(briefPublicMessage, details);
        Assertions.assertEquals(briefPublicMessage + " | " + details, testSubject.getMessage());
    }

    @Test
    void shouldReturnTheOriginalExceptionSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Well... at least that's a fully new kind of scenario for me, a brief, public message of a mapped exception";
        var details = "Even nastier details that should be kept internally available";
        var testSubject = new InternalMappedException(briefPublicMessage, details, originalException);
        Assertions.assertTrue(testSubject.getOriginalException().isPresent());
        Assertions.assertEquals(originalException, testSubject.getOriginalException().get());
    }

    @Test
    void shouldCorrectlySetTheGeneralMessageWhenBothTheOriginalExceptionAndDetailsAreSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Okay...";
        var details = "Hello world! ^^";
        var testSubject = new InternalMappedException(briefPublicMessage, details, originalException);
        Assertions.assertEquals(briefPublicMessage + " | " + details + " | Original: " + originalException, testSubject.getMessage());
    }

    @Test
    void shouldNotSetAnyOriginalExceptionWhenNoneIsProvided(){
        var briefPublicMessage = "Right...";
        var details = "Olá mundo! ><";
        var testSubjectWithDetails = new InternalMappedException(briefPublicMessage, details);
        Assertions.assertFalse(testSubjectWithDetails.getOriginalException().isPresent());
    }

}
