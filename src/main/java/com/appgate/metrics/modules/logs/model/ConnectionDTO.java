package com.appgate.metrics.modules.logs.model;

import lombok.*;
import java.util.Objects;

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
        return (Objects.nonNull(userName) ? userName : "") + ","
                + (Objects.nonNull(date) ? date : "") + ","
                + (Objects.nonNull(ipAddress) ? ipAddress : "") + ","
                + (Objects.nonNull(serverIp) ? serverIp : "") + ","
                + (Objects.nonNull(client) ? client : "") + ","
                + (Objects.nonNull(status) ? status : "") + ","
                + (Objects.nonNull(internal) ? internal : "") + ","
                + (Objects.nonNull(serverName) ? serverName : "");
    }
}
