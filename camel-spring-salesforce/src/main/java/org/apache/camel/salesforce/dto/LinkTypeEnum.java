/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist LinkType
 */
public enum LinkTypeEnum {

    // API
    API("API"),
    // APIAsync
    APIASYNC("APIAsync"),
    // Download
    DOWNLOAD("Download"),
    // UI
    UI("UI");

    final String value;

    private LinkTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static LinkTypeEnum fromValue(String value) {
        for (LinkTypeEnum e : LinkTypeEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
