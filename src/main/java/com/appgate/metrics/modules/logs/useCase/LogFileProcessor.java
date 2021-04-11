package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.enums.ResponseCodeEnum;
import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Log file processor implementation
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
@Component
public class LogFileProcessor implements ILogFileProcessor{
    @Override
    public SimpleResponseDTO processFiles(MultipartFile[] files) throws IOException {
        for (MultipartFile file: files) {
            generateData(file);
        }
        return SimpleResponseDTO.builder()
                .success(true)
                .message(ResponseCodeEnum.SUCCESS.getDescription())
                .responseCode(ResponseCodeEnum.SUCCESS.getValue())
                .build();
    }

    private void generateData(MultipartFile file) throws IOException {
        LineIterator it = FileUtils.lineIterator(convertToFile(file), "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                /** TODO: Replace this by entry processing function */
                System.out.println(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }

    private File convertToFile(MultipartFile inputFile) {
        String fileName = inputFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            inputFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
