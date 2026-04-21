package com.cae.mapped_exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MappedExceptionTest {

    @Test
    void shouldReturnTheBriefPublicMessageSet(){
        var briefPublicMessage = "That's a public, brief, message of the exception";
        var testSubject = new TestSubjectException(briefPublicMessage);
        Assertions.assertEquals(briefPublicMessage, testSubject.getBriefPublicMessage());
    }

    @Test
    void shouldNotSetDetailsWhenOnlyTheBriefPublicMessageWasSet(){
        var briefPublicMessage = "That's a public, brief, message of the exception again";
        var testSubject = new TestSubjectException(briefPublicMessage);
        Assertions.assertFalse(testSubject.getDetails().isPresent());
    }

    @Test
    void shouldReturnTheDetailsSet(){
        var briefPublicMessage = "Once again, a public, brief, message of the exception";
        var details = "woah! These are some nasty details that should be kept internally available";
        var testSubject = new TestSubjectException(briefPublicMessage, details);
        Assertions.assertEquals(briefPublicMessage, testSubject.getBriefPublicMessage());
        Assertions.assertTrue(testSubject.getDetails().isPresent());
        Assertions.assertEquals(details, testSubject.getDetails().get());
    }

    @Test
    void shouldSetTheFullMessageCorrectlyWhenOnlyABriefMessageIsSet(){
        var briefPublicMessage = "Once again, a public, brief, message of the exception";
        var testSubject = new TestSubjectException(briefPublicMessage);
        Assertions.assertEquals(briefPublicMessage, testSubject.getMessage());
    }

    @Test
    void shouldSetTheFullMessageCorrectlyWhenBothBriefAndDetailsTextsAreSet(){
        var briefPublicMessage = "There we go one more time: another public, brief, message of the exception";
        var details = "Even nastier details that should be kept internally available";
        var testSubject = new TestSubjectException(briefPublicMessage, details);
        Assertions.assertEquals(briefPublicMessage + " | " + details, testSubject.getMessage());
    }

    @Test
    void shouldReturnTheOriginalExceptionSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Well... at least that's a fully new kind of scenario for me, a brief, public message of a mapped exception";
        var testSubject = new TestSubjectException(briefPublicMessage, originalException);
        Assertions.assertTrue(testSubject.getOriginalException().isPresent());
        Assertions.assertEquals(originalException, testSubject.getOriginalException().get());
    }

    @Test
    void shouldCorrectlySetTheGeneralMessageWhenTheOriginalExceptionSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Well... that's a kinda of new scenario for me still";
        var testSubject = new TestSubjectException(briefPublicMessage, originalException);
        Assertions.assertEquals(briefPublicMessage + " | Original: " + originalException, testSubject.getMessage());
    }

    @Test
    void shouldCorrectlySetTheGeneralMessageWhenBothTheOriginalExceptionAndDetailsAreSet(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Okay...";
        var details = "Hello world! ^^";
        var testSubject = new TestSubjectException(briefPublicMessage, details, originalException);
        Assertions.assertEquals(briefPublicMessage + " | " + details + " | Original: " + originalException, testSubject.getMessage());
    }

    @Test
    void shouldNotSetAnyOriginalExceptionWhenNoneIsProvided(){
        var briefPublicMessage = "Right...";
        var details = "Olá mundo! ><";
        var testSubjectWithDetails = new TestSubjectException(briefPublicMessage, details);
        var testSubjectWithoutDetails = new TestSubjectException(briefPublicMessage);
        Assertions.assertFalse(testSubjectWithoutDetails.getOriginalException().isPresent());
        Assertions.assertFalse(testSubjectWithDetails.getOriginalException().isPresent());
    }

    @Test
    void shouldReturnStackTraceFromItselfAsExpected(){
        var briefPublicMessage = "Oh boy";
        var testSubjectWithoutDetails = new TestSubjectException(briefPublicMessage);
        var numberOfLines = 5;
        var expectedLinesOfStackTrace = this.getLines(testSubjectWithoutDetails, numberOfLines);
        var actualLinesRetrieved = testSubjectWithoutDetails.getLinesFromStackTrace(numberOfLines);
        expectedLinesOfStackTrace.forEach(line -> Assertions.assertTrue(actualLinesRetrieved.stream().anyMatch(actualLine -> actualLine.equals(line))));
    }

    @Test
    void shouldReturnStackTraceFromOriginalExceptionAsExpected(){
        var originalException = new RuntimeException("Opsie... hi-hi-hi");
        var briefPublicMessage = "Here we go again";
        var testSubjectWithoutDetails = new TestSubjectException(briefPublicMessage, originalException);
        var numberOfLines = 5;
        var expectedLinesOfStackTrace = this.getLines(originalException, numberOfLines);
        var actualLinesRetrieved = testSubjectWithoutDetails.getLinesFromStackTraceFromOriginalException(numberOfLines);
        expectedLinesOfStackTrace.forEach(line -> Assertions.assertTrue(actualLinesRetrieved.stream().anyMatch(actualLine -> actualLine.equals(line))));
    }

    @Test
    void shouldReturnStackTraceAsStringAsExpected(){
        var briefPublicMessage = "When will this end?";
        var testSubjectWithoutDetails = new TestSubjectException(briefPublicMessage);
        var expectedFullStackTraceAsString = this.getFullStackTraceAsString(testSubjectWithoutDetails);
        var actualFullStackTraceAsString = testSubjectWithoutDetails.getFullStackTraceAsString();
        Assertions.assertEquals(expectedFullStackTraceAsString, actualFullStackTraceAsString);
    }

    @Test
    void shouldNotBreakIfNumberOfLinesRequestedAreGreaterThanActualAvailableLines(){
        var briefPublicMessage = "Finally";
        var testSubjectWithoutDetails = new TestSubjectException(briefPublicMessage);
        var numberOfLinesWayGreaterThanItShouldBe = 100000;
        Assertions.assertDoesNotThrow(() -> testSubjectWithoutDetails.getLinesFromStackTraceFromOriginalException(numberOfLinesWayGreaterThanItShouldBe));
    }

    public static class TestSubjectException extends MappedException{

        public TestSubjectException(String briefPublicMessage, String details) {
            super(briefPublicMessage, details);
        }

        public TestSubjectException(String briefPublicMessage) {
            super(briefPublicMessage);
        }

        public TestSubjectException(String briefPublicMessage, String details, Exception originalException) {
            super(briefPublicMessage, details, originalException);
        }

        public TestSubjectException(String briefPublicMessage, Exception originalException) {
            super(briefPublicMessage, originalException);
        }
    }

    private List<String> getLines(Exception exception, Integer numberOfLines){
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

    private String getFullStackTraceAsString(MappedException mappedException){
        var stringWriter = new StringWriter();
        mappedException.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}

