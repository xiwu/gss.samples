/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist StepStatus
 */
public enum StepStatusEnum {

    // Approved
    APPROVED("Approved"),
    // Fault
    FAULT("Fault"),
    // Held
    HELD("Held"),
    // NoResponse
    NORESPONSE("NoResponse"),
    // Pending
    PENDING("Pending"),
    // Reassigned
    REASSIGNED("Reassigned"),
    // Rejected
    REJECTED("Rejected"),
    // Removed
    REMOVED("Removed"),
    // Started
    STARTED("Started");

    final String value;

    private StepStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static StepStatusEnum fromValue(String value) {
        for (StepStatusEnum e : StepStatusEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
