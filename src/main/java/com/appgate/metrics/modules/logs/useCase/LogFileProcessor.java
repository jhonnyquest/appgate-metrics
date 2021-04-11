package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public class LogFileProcessor implements ILogFileProcessor{
    @Override
    public SimpleResponseDTO processFiles(MultipartFile[] files) {
        for (MultipartFile file: files) {
            System.out.println(file.getOriginalFilename());
        }
        return null;
    }
}
