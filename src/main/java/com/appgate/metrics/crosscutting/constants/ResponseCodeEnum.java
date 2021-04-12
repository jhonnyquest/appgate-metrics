package com.appgate.metrics.crosscutting.constants;

/**
 * Canonical domain enum for supported response codes
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-10
 */
public enum ResponseCodeEnum {
    SUCCESS("MTS-001", "Your request was processed successfully"),
    FAIL("MTS-002", "There was an error trying to process you request"),
    NOT_FOUND("MTS-003", "Resource not found");

    private String value;
    private String description;

    ResponseCodeEnum(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public static ResponseCodeEnum map(final String value) {
        for (final ResponseCodeEnum beacon : ResponseCodeEnum.values()) {
            if (value.equals(beacon.value)) {
                return beacon;
            }
        }
        /** If value provided not found then return defailt value: FAIL */
        return ResponseCodeEnum.FAIL;
    }

    public String getDescription() {
        return this.description;
    }

    public String getValue() {
        return this.value;
    }
}
