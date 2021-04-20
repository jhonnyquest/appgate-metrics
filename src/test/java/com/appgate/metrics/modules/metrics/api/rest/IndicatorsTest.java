package com.appgate.metrics.modules.metrics.api.rest;

import com.appgate.metrics.modules.metrics.useCase.IndicatorsInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import static org.mockito.Mockito.when;
import static test.util.DataAcrossTestUtil.*;

/**
 * Junit test of: Indicators API component
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-20
 */
@ExtendWith(MockitoExtension.class)
public class IndicatorsTest {

    @Mock
    IndicatorsInformation IndicatorsInformationMock;

    @InjectMocks
    Indicators indicatorsApi;

    @Test
    public void isUserKnownTest() throws IOException {
        when(IndicatorsInformationMock.isUserKnown(Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        ResponseEntity<Object> response = indicatorsApi.isUserKnown(TEST_NAME);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Boolean.TRUE,
                Objects.requireNonNull(response.getBody()));
    }

    @Test
    public void isIpKnownTest() throws IOException {
        when(IndicatorsInformationMock.isIpKnown(Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        ResponseEntity<Object> response = indicatorsApi.isIpKnown(TEST_IP);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Boolean.TRUE,
                Objects.requireNonNull(response.getBody()));
    }

    @Test
    public void isIpInternalTest() throws IOException {
        when(IndicatorsInformationMock.isIPInternal(Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        ResponseEntity<Object> response = indicatorsApi.isIPInternal(TEST_NAME);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Boolean.TRUE,
                Objects.requireNonNull(response.getBody()));
    }

    @Test
    public void lastSuccessfulLoginDateTest() throws IOException, ParseException {
        Date testDate = new Date();
        when(IndicatorsInformationMock.getLastSuccessfulLoginDate(Mockito.anyString()))
                .thenReturn(testDate);
        ResponseEntity<Object> response = indicatorsApi.getLastSuccessfulLoginDate(TEST_NAME);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(testDate,
                Objects.requireNonNull(response.getBody()));
    }

    @Test
    public void lastFailedLoginDateTest() throws IOException, ParseException {
        Date testDate = new Date();
        when(IndicatorsInformationMock.getLastFailedLoginDate(Mockito.anyString()))
                .thenReturn(testDate);
        ResponseEntity<Object> response = indicatorsApi.getLastFailedLoginDate(TEST_NAME);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(testDate,
                Objects.requireNonNull(response.getBody()));
    }
}
