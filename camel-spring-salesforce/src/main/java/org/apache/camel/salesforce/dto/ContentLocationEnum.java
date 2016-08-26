/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist ContentLocation
 */
public enum ContentLocationEnum {

    // E
    E("E"),
    // S
    S("S");

    final String value;

    private ContentLocationEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static ContentLocationEnum fromValue(String value) {
        for (ContentLocationEnum e : ContentLocationEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
