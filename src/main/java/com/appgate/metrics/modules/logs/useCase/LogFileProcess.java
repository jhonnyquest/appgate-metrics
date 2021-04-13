package com.appgate.metrics.modules.logs.useCase;

import com.appgate.metrics.crosscutting.constants.ResponseCodeEnum;
import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import com.appgate.metrics.modules.logs.constants.ConnectionStatusEnum;
import com.appgate.metrics.modules.logs.constants.EntryTypeEnum;
import com.appgate.metrics.modules.logs.helpers.FileHelper;
import com.appgate.metrics.modules.logs.model.ConnectionDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.appgate.metrics.modules.logs.constants.EntryTypeEnum.*;
import static com.appgate.metrics.modules.logs.helpers.parseHelper.getAttribute;
import static com.appgate.metrics.modules.logs.helpers.parseHelper.identifyEntry;

/**
 * Log file processor implementation
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
@Component
public class LogFileProcess implements ILogFileProcess {

    @Autowired
    private FileHelper fileHelper;

    private LineIterator it;
    @Override
    public SimpleResponseDTO processFiles(MultipartFile[] files) throws IOException {
        for (MultipartFile file: files) {
            it = FileUtils.lineIterator(fileHelper.convertToFile(file), "UTF-8");
            try {
                while (it.hasNext()) {
                    String line = it.nextLine();
                    EntryTypeEnum type = identifyEntry(line);
                    if(Objects.nonNull(type)) {
                        storeEntry(type, line);
                    }
                }
            } finally {
                LineIterator.closeQuietly(it);
            }
        }
        /** TODO: Replace by proper response handler */
        return SimpleResponseDTO.builder()
                .success(true)
                .message(ResponseCodeEnum.SUCCESS.getDescription())
                .responseCode(ResponseCodeEnum.SUCCESS.getValue())
                .build();
    }

    private void storeEntry(EntryTypeEnum type, String entry) throws IOException {
        ConnectionDTO connection = new ConnectionDTO();
        connection.setDate(entry.substring(0, 17));
        switch(type.name()) {
            case "CONNECTION_SUCCESS":
                connection.setUserName(entry.substring(42).split(" ")[1]);
                connection.setStatus(ConnectionStatusEnum.SUCCESS.name());
                setConnectionData(connection);
                break;
            case "CONNECTION_FAIL":
                connection.setUserName(entry.substring(42).split(" ")[6]);
                connection.setServerIp(entry.substring(42).split(" ")[8]);
                connection.setStatus(ConnectionStatusEnum.FAILED.name());
                break;
            default:
        }
        fileHelper.inserDataInTableFileStorage("connections", connection);
    }

    private void setConnectionData(ConnectionDTO connection) {
        String attribute;
        for(int i = 0; i < 20; i++) {
            String newLine = it.nextLine();

            EntryTypeEnum newEntryType = identifyEntry(newLine);
            if(Objects.nonNull(newEntryType) && newEntryType.equals(LOGIN_CLIENT_IP)) {
                connection.setIpAddress(getAttribute(newLine));
            } else if(Objects.nonNull(newEntryType) && newEntryType.equals(LOGIN_SERVER_IP)) {
                connection.setServerIp(getAttribute(newLine));
            } else if (Objects.nonNull(newEntryType) && newEntryType.equals(LOGIN_SERVER_NAME)) {
                connection.setServerName(getAttribute(newLine));
            } else if (Objects.nonNull(newEntryType) && newEntryType.equals(CLIENT)) {
                connection.setClient(getAttribute(newLine));
            }
        }
        String[] userIpArray = Optional.of(connection.getIpAddress().split("\\."))
                .orElse(null);
        String[] serverIpArray = Optional.of(connection.getServerIp().split("\\."))
                .orElse(null);
        connection.setInternal(
                userIpArray[0].equals(serverIpArray[0]) && userIpArray[1].equals(serverIpArray[1]) ?
                    Boolean.TRUE.toString() : Boolean.FALSE.toString()
        );
    }
}
