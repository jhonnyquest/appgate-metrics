package com.appgate.metrics.modules.logs.api.rest;

import com.appgate.metrics.modules.logs.useCase.ILogFileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * Logs file processor Rest API definition
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
@RestController
@RequestMapping(value = "/logs")
public class Logs {
    @Autowired
    private ILogFileProcessor fileProcessor;

    @PostMapping()
    public ResponseEntity<Object> uploadCustomeerLogFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.ok ( fileProcessor.processFiles(files));
    }
}
