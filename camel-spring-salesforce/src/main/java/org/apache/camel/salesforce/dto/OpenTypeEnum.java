/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist OpenType
 */
public enum OpenTypeEnum {

    // newWindow
    NEWWINDOW("newWindow"),
    // noSidebar
    NOSIDEBAR("noSidebar"),
    // onClickJavaScript
    ONCLICKJAVASCRIPT("onClickJavaScript"),
    // replace
    REPLACE("replace"),
    // sidebar
    SIDEBAR("sidebar");

    final String value;

    private OpenTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static OpenTypeEnum fromValue(String value) {
        for (OpenTypeEnum e : OpenTypeEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}