/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist NotifyForFields
 */
public enum NotifyForFieldsEnum {

    // All
    ALL("All"),
    // Referenced
    REFERENCED("Referenced"),
    // Select
    SELECT("Select"),
    // Where
    WHERE("Where");

    final String value;

    private NotifyForFieldsEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static NotifyForFieldsEnum fromValue(String value) {
        for (NotifyForFieldsEnum e : NotifyForFieldsEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
