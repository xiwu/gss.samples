/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist MaxAccessLevel
 */
public enum MaxAccessLevelEnum {

    // All
    ALL("All"),
    // Delete
    DELETE("Delete"),
    // Edit
    EDIT("Edit"),
    // None
    NONE("None"),
    // Read
    READ("Read"),
    // Transfer
    TRANSFER("Transfer");

    final String value;

    private MaxAccessLevelEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static MaxAccessLevelEnum fromValue(String value) {
        for (MaxAccessLevelEnum e : MaxAccessLevelEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
