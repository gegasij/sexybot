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

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        String s = "{\"ParsedResults\":[{\"TextOverlay\":{\"Lines\":[],\"HasOverlay\":false,\"Message\":\"Text overlay is not provided as it is not requested\"},\"TextOrientation\":\"0\",\"FileParseExitCode\":1,\"ParsedText\":\"30\\r\\nЗадание для девушки.\\r\\nВстань к нему спиной\\r\\nи, пританцовывая, сними с себя\\r\\nвсю одежду, которая\\r\\nприкрывает твою попку,\\r\\nи даже трусики!\\r\\n\",\"ErrorMessage\":\"\",\"ErrorDetails\":\"\"}],\"OCRExitCode\":1,\"IsErroredOnProcessing\":false,\"ProcessingTimeInMilliseconds\":\"21906\",\"SearchablePDFURL\":\"Searchable PDF not generated as it was not requested.\"}";
        ImageToTextResponse imageToTextResponse = objectMapper.readValue(s, ImageToTextResponse.class);
        System.out.println(imageToTextResponse);

    }
}
