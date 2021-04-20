package com.appgate.metrics.modules.metrics.useCase;

import com.appgate.metrics.modules.metrics.helpers.ConnectionsCsvQueryEngine;
import com.appgate.metrics.modules.metrics.model.ConnectionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.util.DataAcrossTestUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static test.util.DataAcrossTestUtil.*;

/**
 * Junit test of: Indicators information component
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-20
 */
@ExtendWith(MockitoExtension.class)
public class IndicatorsInformationTest {

    private final DataAcrossTestUtil data = new DataAcrossTestUtil();

    @Mock
    ConnectionsCsvQueryEngine queryEngineMock;

    @InjectMocks
    IndicatorsInformation indicatorsInformation;

    @Test
    public void isUserKnownTest() throws IOException {
        when(queryEngineMock.isKnown(eq(USERNAME_LITERAL), Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        Boolean response = indicatorsInformation.isUserKnown(TEST_NAME);
        Assertions.assertEquals(Boolean.TRUE,
                Objects.requireNonNull(response));
    }

    @Test
    public void isIpKnownTest() throws IOException {
        when(queryEngineMock.isKnown(eq(USER_IP_LITERAL), Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        Boolean response = indicatorsInformation.isIpKnown(TEST_IP);
        Assertions.assertEquals(Boolean.TRUE,
                Objects.requireNonNull(response));
    }

    @Test
    public void isIPInternalTest() throws IOException {
        when(queryEngineMock.isInternalIp(Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        Boolean response = indicatorsInformation.isIPInternal(TEST_IP);
        Assertions.assertEquals(Boolean.TRUE,
                Objects.requireNonNull(response));
    }

}
