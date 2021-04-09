package com.appgate.metrics.modules.test.api.rest;

import com.appgate.metrics.modules.test.model.PostResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/test")
public class test {
    @GetMapping()
    public ResponseEntity<Object> testGet() {
        return ResponseEntity.ok("test performed successfully...");
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> testPost(@RequestBody PostResponseDTO objectDto) {
        objectDto.setId(UUID.randomUUID().toString());
        return ResponseEntity.ok(objectDto);    }
}
