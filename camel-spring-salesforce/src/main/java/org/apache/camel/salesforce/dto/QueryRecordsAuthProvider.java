/*
 * Salesforce Query DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.camel.component.salesforce.api.dto.AbstractQueryRecordsBase;

import java.util.List;

/**
 * Salesforce QueryRecords DTO for type AuthProvider
 */
public class QueryRecordsAuthProvider extends AbstractQueryRecordsBase {

    @XStreamImplicit
    private List<AuthProvider> records;

    public List<AuthProvider> getRecords() {
        return records;
    }

    public void setRecords(List<AuthProvider> records) {
        this.records = records;
    }
}
