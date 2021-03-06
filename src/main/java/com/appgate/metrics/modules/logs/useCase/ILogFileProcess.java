package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * Log file processor interface
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
public interface ILogFileProcess {
    SimpleResponseDTO processFiles(MultipartFile[] files) throws IOException, ParseException, NoSuchAlgorithmException;
}
