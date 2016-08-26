/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist Salutation
 */
public enum SalutationEnum {

    // Dr.
    DR_("Dr."),
    // Mr.
    MR_("Mr."),
    // Mrs.
    MRS_("Mrs."),
    // Ms.
    MS_("Ms."),
    // Prof.
    PROF_("Prof.");

    final String value;

    private SalutationEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static SalutationEnum fromValue(String value) {
        for (SalutationEnum e : SalutationEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
