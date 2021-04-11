package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Log file processor interface
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
public interface ILogFileProcessor {
    SimpleResponseDTO processFiles(MultipartFile[] files) throws IOException;
}
