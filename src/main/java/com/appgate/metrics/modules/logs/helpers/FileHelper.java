package com.appgate.metrics.modules.logs.helpers;

import com.appgate.metrics.modules.logs.model.ConnectionDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * File handling helper
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-11
 */
@Component
public class FileHelper {
    public File convertToFile(MultipartFile inputFile) {
        File file = null;
        try {
            String fileName = inputFile.getOriginalFilename();
            String prefix = Objects.requireNonNull(fileName)
                    .substring(fileName.lastIndexOf("."));
            file = File.createTempFile(fileName, prefix);
            inputFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void inserDataInTableFileStorage(String tableName, ConnectionDTO data) throws IOException {
        String outputFile = tableName + ".csv";
        FileWriter fileWriter = new FileWriter(outputFile, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.newLine();
        bw.write(data.getCSVString());
        bw.flush();
        bw.close();
    }
}
