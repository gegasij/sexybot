package com.converter.text.service;

import com.converter.text.model.ConvertedText;
import com.converter.text.model.GenericText;
import com.converter.text.model.ImageToTextRequest;
import com.converter.text.model.ImageToTextResponse;
import com.converter.text.repository.ConvertedTextRepository;
import com.converter.text.repository.GenericTextRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConverterTextService {
    private final ConvertedTextRepository convertedTextRepository;
    private final GenericTextRepository genericTextRepository;
    private final FileService fileService;


    public void changeAllFiles() {
        List<ConvertedText> convertedTexts = convertedTextRepository.findAll();
        convertedTexts
                .stream()
                .filter(it -> it.getData().containsKey("type"))
                .filter(it->it.getData().get("type").equals("truthOrDare"))
                .peek(it -> it.getData().put("typeTOD", "Truth"))
                .toList();

        convertedTextRepository.saveAllAndFlush(convertedTexts);
    }

    public void convertAllFiles() {
        List<String> folderFilePaths = fileService.getFolderFilePaths("C:\\Users\\prost\\Downloads\\Telegram Desktop");

        ImageToTextRequest build = ImageToTextRequest.builder()
                .language("rus")
                .apiUrl("https://api.ocr.space/parse/image")
                .apiKey("K83594462688957")
                .copyToIfSuccess("C:\\Users\\prost\\OneDrive\\Рабочий стол\\chertovka\\обработанные")
                .build();

        for (String filePath : folderFilePaths) {
            build.setPathToFile(filePath);
            convert(build);
        }

    }


    public void convert(ImageToTextRequest request) {
        ImageToTextService imageToTextService = new ImageToTextService();
        try {
            ImageToTextResponse convert = imageToTextService.convert(request);
            if (!convert.getOCRExitCode().equals("1")) {
                System.exit(Integer.parseInt(convert.getOCRExitCode()));
            }
            genericTextRepository.save(GenericText.builder().text(convert.getParsedResults().get(0).getParsedText()).build());

            Map<String, Object> data = getData(convert);

            convertedTextRepository.save(ConvertedText.builder().data(data).build());
            fileService.moveFile(request.getPathToFile(), request.getCopyToIfSuccess());
        } catch (IOException e) {
            FileWriter.writeToFile("./src/main/resources/data.txt", "ошибка в файле " + request.getPathToFile() + "\n" + e.getMessage() + "\n");
        }
    }

    private static Map<String, Object> getData(ImageToTextResponse convert) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String parsedText = convert.getParsedResults().get(0).getParsedText();

        parsedText = parsedText.replace("\r\n", " ");

        //parsedText = setNumber(parsedText, hashMap);
        parsedText = deleteStart(parsedText);
        setData(parsedText, hashMap);

        return hashMap;
    }

    public static String deleteStart(String parsedString) {
        String s = "действие";
        List<String> possibleStringOptions = getPossibleStringOptions(s);
        return possibleStringOptions.
                stream()
                .filter(parsedString::startsWith)
                .findAny()
                .map(it -> parsedString.replaceFirst(it + " ", ""))
                .orElse(parsedString);
    }

    public static List<String> getPossibleStringOptions(String s) {
        List<String> list = new ArrayList<>();
        list.add(s);
        for (int i = 0; i < s.toCharArray().length; i++) {
            StringBuilder myName = new StringBuilder(s);
            myName.setCharAt(i, ' ');
            list.add(myName.toString());
        }
        return list;
    }

    private static void setData(String parsedText, HashMap<String, Object> hashMap) {
        hashMap.put("data", parsedText);
        hashMap.put("level", "HARD");
        hashMap.put("forWho", "female");
        hashMap.put("type", "truthOrDare");
        hashMap.put("typeTOD", "Dare");
    }

    private static String setNumber(String parsedText, HashMap<String, Object> hashMap) {
        List<String> split = Arrays.stream(parsedText.split(" ")).toList();
        if (split.size() > 0) {
            int i = NumberUtils.toInt(split.get(0), Integer.MAX_VALUE);
            if (i != Integer.MAX_VALUE) {
                hashMap.put("number", i);
                parsedText = parsedText.replaceFirst(split.get(0) + " ", "");
            }
        }
        return parsedText;
    }
}
