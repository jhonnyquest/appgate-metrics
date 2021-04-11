package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ILogFileProcessor {
    SimpleResponseDTO processFiles(MultipartFile[] files);
}
