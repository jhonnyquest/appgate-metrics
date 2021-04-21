package com.appgate.metrics.modules.metrics.api.rest;

import com.appgate.metrics.modules.metrics.useCase.IMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * Metrics indicators Rest API endpoint definitions
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-12
 */
@RestController
@RequestMapping(value = "/metrics")
public class Indicators {

    @Autowired
    private IMetrics metrics;

    @GetMapping("/isUserKnown")
    public ResponseEntity<Object> isUserKnown(
            @RequestParam("username") final String userName) throws IOException {
        return ResponseEntity.ok(metrics.isUserKnown(userName));
    }

    @GetMapping("/isIpKnown")
    public ResponseEntity<Object> isIpKnown(
            @RequestParam("ip") final String ipAddress) throws IOException {
        return ResponseEntity.ok(metrics.isIpKnown(ipAddress));
    }

    @GetMapping("/isIPInternal")
    public ResponseEntity<Object> isIPInternal(
            @RequestParam("ip") final String ipAddress) throws IOException {
        return ResponseEntity.ok(metrics.isIPInternal(ipAddress));
    }

    @GetMapping("/lastSuccessfulLoginDate")
    public ResponseEntity<Object> getLastSuccessfulLoginDate(
            @RequestParam("username") final String userName) throws FileNotFoundException, ParseException {
        Date response = metrics.getLastSuccessfulLoginDate(userName);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lastFailedLoginDate")
    public ResponseEntity<Object> getLastFailedLoginDate(
            @RequestParam("username") final String userName) throws FileNotFoundException, ParseException {
        Date response = metrics.getLastFailedLoginDate(userName);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
