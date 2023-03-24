package com.converter.text.repository;

import com.converter.text.model.ImageToTextRequest;
import com.converter.text.model.ImageToTextResponse;
import com.converter.text.service.ImageToTextService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ConvertedTextRepositoryTest {
    @Autowired
    private ImageToTextService imageToTextService;

    @Test
    void contextLoads() throws IOException {
        ImageToTextRequest build = ImageToTextRequest.builder()
                .language("rus")
                .apiUrl("https://api.ocr.space/parse/image")
                .apiKey("K83594462688957")
                .pathToFile("C:\\Users\\prost\\Downloads\\Telegram Desktop\\photo_23_2023-03-18_15-26-26.jpg")
                .build();
        ImageToTextResponse convert1 = imageToTextService.convert(build);
        build.setPathToFile("C:\\Users\\prost\\Downloads\\Telegram Desktop\\photo_9_2023-03-18_15-26-26.jpg");
        ImageToTextResponse convert2 = imageToTextService.convert(build);
        build.setPathToFile("C:\\Users\\prost\\Downloads\\Telegram Desktop\\photo_3_2023-03-18_15-26-26.jpg");
        ImageToTextResponse convert3 = imageToTextService.convert(build);

    }

    public String deleteStart(String parsedString) {
        String s = "правда";
        List<String> possibleStringOptions = getPossibleStringOptions(s);
        String finalParsedString = parsedString;
        return parsedString = possibleStringOptions.
                stream()
                .filter(parsedString::startsWith)
                .findAny()
                .map(it -> finalParsedString.replaceFirst(it + " ", ""))
                .orElse(parsedString);
    }

    public List<String> getPossibleStringOptions(String s) {
        List<String> list = new ArrayList<>();
        list.add(s);
        for (int i = 0; i < s.toCharArray().length; i++) {
            StringBuilder myName = new StringBuilder(s);
            myName.setCharAt(i, ' ');
            list.add(myName.toString());
        }
        return list;
    }

}