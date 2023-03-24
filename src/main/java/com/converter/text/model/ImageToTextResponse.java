package com.converter.text.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageToTextResponse {
    @JsonProperty("ParsedResults")
    private ArrayList<ParsedResult> parsedResults;
    @JsonProperty("OCRExitCode")
    private String oCRExitCode;
    @JsonProperty("IsErroredOnProcessing")
    private boolean isErroredOnProcessing;
    @JsonProperty("ErrorMessage")
    private Object errorMessage;
    @JsonProperty("ErrorDetails")
    private Object errorDetails;
    @JsonProperty("SearchablePDFURL")
    private String searchablePDFURL;
    @JsonProperty("ProcessingTimeInMilliseconds")
    private String processingTimeInMilliseconds;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ParsedResult {
        @JsonProperty("FileParseExitCode")
        private Object fileParseExitCode;
        @JsonProperty("ParsedText")
        private String parsedText;
        @JsonProperty("ErrorMessage")
        private String errorMessage;
        @JsonProperty("ErrorDetails")
        private String errorDetails;
        @JsonProperty("TextOverlay")
        private Object textOverlay;
    }
}
