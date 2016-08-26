/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist TemplateStyle
 */
public enum TemplateStyleEnum {

    // formalLetter
    FORMALLETTER("formalLetter"),
    // freeForm
    FREEFORM("freeForm"),
    // newsletter
    NEWSLETTER("newsletter"),
    // none
    NONE("none"),
    // products
    PRODUCTS("products"),
    // promotionLeft
    PROMOTIONLEFT("promotionLeft"),
    // promotionRight
    PROMOTIONRIGHT("promotionRight");

    final String value;

    private TemplateStyleEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static TemplateStyleEnum fromValue(String value) {
        for (TemplateStyleEnum e : TemplateStyleEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}