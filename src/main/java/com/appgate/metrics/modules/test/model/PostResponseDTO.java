package com.appgate.metrics.modules.test.model;

import lombok.*;

/**
 * Test response data transfer object model
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-12
 */
@Getter
@Setter
@Builder
@ToString
public class PostResponseDTO {
    private String id;
    @NonNull
    private String message;
    @NonNull
    private String code;
}
