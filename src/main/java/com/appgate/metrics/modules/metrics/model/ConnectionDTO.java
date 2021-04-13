package com.appgate.metrics.modules.metrics.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ConnectionDTO {
    private String username;
    private String datetime;
    private String userip;
    private String serverip;
    private String authtype;
    private String status;
    private String internal;
    private String servername;
}
