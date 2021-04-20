package com.appgate.metrics.modules.metrics.model;

import lombok.*;

/**
 * Connection table model definition
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-12
 */
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
