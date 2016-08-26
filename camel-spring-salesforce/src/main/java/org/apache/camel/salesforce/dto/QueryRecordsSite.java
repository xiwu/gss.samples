/*
 * Salesforce Query DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.camel.component.salesforce.api.dto.AbstractQueryRecordsBase;

import java.util.List;

/**
 * Salesforce QueryRecords DTO for type Site
 */
public class QueryRecordsSite extends AbstractQueryRecordsBase {

    @XStreamImplicit
    private List<Site> records;

    public List<Site> getRecords() {
        return records;
    }

    public void setRecords(List<Site> records) {
        this.records = records;
    }
}
