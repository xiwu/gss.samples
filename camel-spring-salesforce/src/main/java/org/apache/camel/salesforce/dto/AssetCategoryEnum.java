/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist AssetCategory
 */
public enum AssetCategoryEnum {

    // LargeLogoImageId
    LARGELOGOIMAGEID("LargeLogoImageId"),
    // LoginFooterText
    LOGINFOOTERTEXT("LoginFooterText"),
    // LoginLogoImageId
    LOGINLOGOIMAGEID("LoginLogoImageId"),
    // MediumLogoImageId
    MEDIUMLOGOIMAGEID("MediumLogoImageId"),
    // MotifPrimaryColor
    MOTIFPRIMARYCOLOR("MotifPrimaryColor"),
    // MotifPrimaryComplementColor
    MOTIFPRIMARYCOMPLEMENTCOLOR("MotifPrimaryComplementColor"),
    // MotifQuaternaryColor
    MOTIFQUATERNARYCOLOR("MotifQuaternaryColor"),
    // MotifQuaternaryComplementColor
    MOTIFQUATERNARYCOMPLEMENTCOLOR("MotifQuaternaryComplementColor"),
    // MotifSecondaryColor
    MOTIFSECONDARYCOLOR("MotifSecondaryColor"),
    // MotifTertiaryColor
    MOTIFTERTIARYCOLOR("MotifTertiaryColor"),
    // MotifTertiaryComplementColor
    MOTIFTERTIARYCOMPLEMENTCOLOR("MotifTertiaryComplementColor"),
    // MotifZeronaryColor
    MOTIFZERONARYCOLOR("MotifZeronaryColor"),
    // MotifZeronaryComplementColor
    MOTIFZERONARYCOMPLEMENTCOLOR("MotifZeronaryComplementColor"),
    // PageFooter
    PAGEFOOTER("PageFooter"),
    // PageHeader
    PAGEHEADER("PageHeader"),
    // SmallLogoImageId
    SMALLLOGOIMAGEID("SmallLogoImageId");

    final String value;

    private AssetCategoryEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static AssetCategoryEnum fromValue(String value) {
        for (AssetCategoryEnum e : AssetCategoryEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
