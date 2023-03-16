package com.converter.text.service;

import com.converter.text.model.ConvertedText;
import com.converter.text.model.GenericText;
import com.converter.text.model.ImageToTextRequest;
import com.converter.text.model.ImageToTextResponse;
import com.converter.text.repository.ConvertedTextRepository;
import com.converter.text.repository.GenericTextRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConverterTextService {
    private final ConvertedTextRepository convertedTextRepository;
    private final GenericTextRepository genericTextRepository;
    private final FileService fileService;

    @EventListener(ApplicationReadyEvent.class)
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
            if(!convert.getOCRExitCode().equals("1")){
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
        HashMap<String, Object> hashMap = new HashMap();
        String parsedText = convert.getParsedResults().get(0).getParsedText();

        parsedText = parsedText.replace("\r\n", " ");

        parsedText = setNumber(parsedText, hashMap);
        setData(parsedText, hashMap);

        return hashMap;
    }

    private static void setData(String parsedText, HashMap<String, Object> hashMap) {
        hashMap.put("data", parsedText);
        hashMap.put("level", "CRAZY");
        hashMap.put("condition", "new");
    }

    private static String setNumber(String parsedText, HashMap<String, Object> hashMap) {
        List<String> split = Arrays.stream(parsedText.split(" ")).toList();
        if (split.size() > 0) {
            int i = NumberUtils.toInt(split.get(0), Integer.MAX_VALUE);
            if (i != Integer.MAX_VALUE) {
                hashMap.put("number", i);
                parsedText = parsedText.replaceFirst(split.get(0), "");
            }
        }
        return parsedText;
    }

    public static void main(String[] args) {
        String s = "27 Задание для парня. Представь,что твоя девушка - это твоя одноклассница. Ты трахнул бы ее в школе? Хотел бы, чтобы она ласкала твой член под партой? На физкультуре лапал бы ее в раздевалке? Поделись своими фантазиями, прижимаясь к партнерше ";
        System.out.println();
    }
}
