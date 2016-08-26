/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.camel.component.salesforce.api.dto.AbstractSObjectBase;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Salesforce DTO for SObject StreamingChannel
 */
@XStreamAlias("StreamingChannel")
public class StreamingChannel extends AbstractSObjectBase {

    // IsDynamic
    private Boolean IsDynamic;

    @JsonProperty("IsDynamic")
    public Boolean getIsDynamic() {
        return this.IsDynamic;
    }

    @JsonProperty("IsDynamic")
    public void setIsDynamic(Boolean IsDynamic) {
        this.IsDynamic = IsDynamic;
    }

    // Description
    private String Description;

    @JsonProperty("Description")
    public String getDescription() {
        return this.Description;
    }

    @JsonProperty("Description")
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
