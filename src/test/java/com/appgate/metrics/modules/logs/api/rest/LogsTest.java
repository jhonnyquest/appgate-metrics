package com.appgate.metrics.modules.logs.api.rest;

import com.appgate.metrics.crosscutting.constants.ResponseCodeEnum;
import com.appgate.metrics.modules.logs.useCase.ILogFileProcess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import test.util.DataAcrossTestUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Objects;

import static org.mockito.Mockito.when;

/**
 * Junit test of: Logs API component
 *
 * @author jmunoz
 * @version 1.0
 * @since 2019-06-19
 */
@ExtendWith(MockitoExtension.class)
public class LogsTest {

    private final DataAcrossTestUtil data = new DataAcrossTestUtil();

    @Mock
    private ILogFileProcess fileProcessorMock;

    @InjectMocks
    private Logs logApi;

    @Test
    public void uploadCustomerLogFileTest() throws ParseException, NoSuchAlgorithmException, IOException {
        when(fileProcessorMock.processFiles(Mockito.any()))
                .thenReturn(data.getSimpleResponse(ResponseCodeEnum.SUCCESS));
        MultipartFile mockFile = data.getMultipartSourceFileMock();
        MultipartFile[] files = {mockFile};
        final ResponseEntity<?> response = logApi.uploadCustomerLogFile(files);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(
                data.getSimpleResponse(ResponseCodeEnum.SUCCESS).toString(),
                Objects.requireNonNull(response.getBody()).toString());
    }
}
