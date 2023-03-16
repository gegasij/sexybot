package com.converter.text.service;

import com.converter.text.model.ImageToTextResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.converter.text.model.ImageToTextRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ImageToTextService {
    public ImageToTextResponse convert(ImageToTextRequest request) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(request.getApiUrl());

        // Create a multi-part request entity
        File file = new File(request.getPathToFile());

        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName())
                .addTextBody("apikey", request.getApiKey())
                .addTextBody("language", request.getLanguage())
                .build();

        // Set the request entity
        httpPost.setEntity(httpEntity);

        // Send the request and get the response
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // Print the response status and body
            System.out.println(response.getStatusLine());
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String s = EntityUtils.toString(responseEntity);
                ObjectMapper objectMapper = new ObjectMapper()
                        .registerModule(new JavaTimeModule());
                return objectMapper.readValue(s, ImageToTextResponse.class);
            }
        }
        throw new RuntimeException("что то пошло не так");
    }
}
