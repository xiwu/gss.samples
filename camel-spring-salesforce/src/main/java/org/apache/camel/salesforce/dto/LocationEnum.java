/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist Location
 */
public enum LocationEnum {

    // HeapDump
    HEAPDUMP("HeapDump"),
    // Monitoring
    MONITORING("Monitoring"),
    // Preserved
    PRESERVED("Preserved"),
    // Profiling
    PROFILING("Profiling"),
    // SystemLog
    SYSTEMLOG("SystemLog");

    final String value;

    private LocationEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static LocationEnum fromValue(String value) {
        for (LocationEnum e : LocationEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
