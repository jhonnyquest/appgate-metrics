package com.appgate.metrics.modules.test.model;

import lombok.*;

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
