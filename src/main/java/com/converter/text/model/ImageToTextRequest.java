package com.converter.text.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageToTextRequest {
    private String pathToFile;
    private String language;
    private String apiKey;
    private String apiUrl;
    private String copyToIfSuccess;
}
