/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist MiddleSize
 */
public enum MiddleSizeEnum {

    // Medium
    MEDIUM("Medium"),
    // Narrow
    NARROW("Narrow"),
    // Wide
    WIDE("Wide");

    final String value;

    private MiddleSizeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static MiddleSizeEnum fromValue(String value) {
        for (MiddleSizeEnum e : MiddleSizeEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
