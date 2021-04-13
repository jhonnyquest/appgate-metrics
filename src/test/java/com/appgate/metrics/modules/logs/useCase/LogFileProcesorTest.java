package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.constants.ResponseCodeEnum;
import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import com.appgate.metrics.modules.logs.helpers.FileHelper;
import com.appgate.metrics.modules.logs.model.ConnectionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import test.util.DataAcrossTestUtil;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Junit test of: Log files processor component
 *
 * @author blanclabs
 * @version 1.0
 * @since 2019-06-19
 */
@ExtendWith(MockitoExtension.class)
public class LogFileProcesorTest {

    private final DataAcrossTestUtil data = new DataAcrossTestUtil();

    @Mock
    private FileHelper fileHelperMock;

    @InjectMocks
    private LogFileProcess logFileProcess;

    @Test
    public void processFilesTest() throws IOException, URISyntaxException {
        doNothing().when(fileHelperMock).inserDataInTableFileStorage(
                Mockito.any(String.class),
                Mockito.any(ConnectionDTO.class));

        when(fileHelperMock.convertToFile(any())).thenReturn(data.getTestFile());

        MultipartFile mockFile = data.getMultipartSourceFileMock();
        MultipartFile[] files = {mockFile};

        SimpleResponseDTO response = logFileProcess.processFiles(files);
        Assertions.assertEquals(
                data.getSimpleResponse(ResponseCodeEnum.SUCCESS).toString(),
                response.toString());
    }

}
