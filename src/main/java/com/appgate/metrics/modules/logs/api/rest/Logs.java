package com.appgate.metrics.modules.logs.api.rest;

import com.appgate.metrics.crosscutting.enums.ResponseCodeEnum;
import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/logs")
public class Logs {
    @PostMapping()
    public ResponseEntity<Object> uploadCustomeerLogFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.ok (SimpleResponseDTO.builder()
                .success(true)
                .message(ResponseCodeEnum.SUCCESS.getDescription())
                .responseCode(ResponseCodeEnum.SUCCESS.getValue())
                .build()
        );
    }
}
