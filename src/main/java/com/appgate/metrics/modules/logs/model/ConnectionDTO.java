package com.appgate.metrics.modules.logs.model;

import lombok.*;
import java.util.Objects;

import static com.appgate.metrics.modules.logs.constants.ConnectionStatusEnum.FAILED;
import static com.appgate.metrics.modules.logs.constants.ConnectionStatusEnum.SUCCESS;

/**
 * Connections data transfer object
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-11
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ConnectionDTO {
    private String userName;
    private String date;
    private String ipAddress;
    private String serverIp;
    private String client;
    private String status;
    private String internal;
    private String serverName;

    public String getCSVString() {
        return (Objects.nonNull(userName) ? userName : "NULL") + ","
                + (Objects.nonNull(date) ? date : "NULL") + ","
                + (Objects.nonNull(ipAddress) ? ipAddress : "NULL") + ","
                + (Objects.nonNull(serverIp) ? serverIp : "NULL") + ","
                + (Objects.nonNull(client) ? client : "NULL") + ","
                + (Objects.nonNull(status) ? SUCCESS.name() : FAILED.name()) + ","
                + (Objects.nonNull(internal) ? internal : "NULL") + ","
                + (Objects.nonNull(serverName) ? serverName : "NULL");
    }
}
