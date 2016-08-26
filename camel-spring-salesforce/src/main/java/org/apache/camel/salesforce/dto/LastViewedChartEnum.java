/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist LastViewedChart
 */
public enum LastViewedChartEnum {

    // AccountsByIndustry
    ACCOUNTSBYINDUSTRY("AccountsByIndustry"),
    // CampaignsByNumberOfResponses
    CAMPAIGNSBYNUMBEROFRESPONSES("CampaignsByNumberOfResponses"),
    // CampaignsByStatus
    CAMPAIGNSBYSTATUS("CampaignsByStatus"),
    // CampaignsByType
    CAMPAIGNSBYTYPE("CampaignsByType"),
    // ContactsByAccount
    CONTACTSBYACCOUNT("ContactsByAccount"),
    // ContactsByLeadSource
    CONTACTSBYLEADSOURCE("ContactsByLeadSource"),
    // LeadsBySource
    LEADSBYSOURCE("LeadsBySource"),
    // PipelineByAccount
    PIPELINEBYACCOUNT("PipelineByAccount"),
    // PipelineByFiscalPeriod
    PIPELINEBYFISCALPERIOD("PipelineByFiscalPeriod"),
    // PipelineByStage
    PIPELINEBYSTAGE("PipelineByStage");

    final String value;

    private LastViewedChartEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static LastViewedChartEnum fromValue(String value) {
        for (LastViewedChartEnum e : LastViewedChartEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
