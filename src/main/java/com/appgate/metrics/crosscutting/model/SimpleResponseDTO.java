package com.appgate.metrics.crosscutting.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Data transfer object for generic simple responses
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
@Getter
@Setter
@Builder
@ToString
public class SimpleResponseDTO {
    private Boolean success;
    private String message;
    private String responseCode;
}
