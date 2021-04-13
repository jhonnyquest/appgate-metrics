package com.appgate.metrics.modules.metrics.useCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Metrics information interface
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-12
 */
public interface IMetrics {
    Boolean isUserKnown(final String userName) throws IOException;
    Boolean isIpKnown(final String ipAddress) throws IOException;
    Boolean isIPInternal(final String ipAddress) throws IOException;
    Date getLastSuccessfulLoginDate(final String userName) throws FileNotFoundException, ParseException;
    Date getLastFailedLoginDate(final String userName) throws FileNotFoundException, ParseException;
}
