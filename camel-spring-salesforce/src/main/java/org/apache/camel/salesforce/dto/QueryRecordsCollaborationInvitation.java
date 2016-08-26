/*
 * Salesforce Query DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.camel.component.salesforce.api.dto.AbstractQueryRecordsBase;

import java.util.List;

/**
 * Salesforce QueryRecords DTO for type CollaborationInvitation
 */
public class QueryRecordsCollaborationInvitation extends AbstractQueryRecordsBase {

    @XStreamImplicit
    private List<CollaborationInvitation> records;

    public List<CollaborationInvitation> getRecords() {
        return records;
    }

    public void setRecords(List<CollaborationInvitation> records) {
        this.records = records;
    }
}
