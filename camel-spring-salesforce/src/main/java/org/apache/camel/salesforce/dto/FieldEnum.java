/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist Field
 */
public enum FieldEnum {

    // Account.AccountNumber
    ACCOUNT_ACCOUNTNUMBER("Account.AccountNumber"),
    // Account.AccountSource
    ACCOUNT_ACCOUNTSOURCE("Account.AccountSource"),
    // Account.Active__c
    ACCOUNT_ACTIVE__C("Account.Active__c"),
    // Account.AnnualRevenue
    ACCOUNT_ANNUALREVENUE("Account.AnnualRevenue"),
    // Account.BillingAddress
    ACCOUNT_BILLINGADDRESS("Account.BillingAddress"),
    // Account.CleanStatus
    ACCOUNT_CLEANSTATUS("Account.CleanStatus"),
    // Account.CustomerPriority__c
    ACCOUNT_CUSTOMERPRIORITY__C("Account.CustomerPriority__c"),
    // Account.DandbCompany
    ACCOUNT_DANDBCOMPANY("Account.DandbCompany"),
    // Account.Description
    ACCOUNT_DESCRIPTION("Account.Description"),
    // Account.DunsNumber
    ACCOUNT_DUNSNUMBER("Account.DunsNumber"),
    // Account.Fax
    ACCOUNT_FAX("Account.Fax"),
    // Account.Industry
    ACCOUNT_INDUSTRY("Account.Industry"),
    // Account.Jigsaw
    ACCOUNT_JIGSAW("Account.Jigsaw"),
    // Account.NaicsCode
    ACCOUNT_NAICSCODE("Account.NaicsCode"),
    // Account.NaicsDesc
    ACCOUNT_NAICSDESC("Account.NaicsDesc"),
    // Account.NumberOfEmployees
    ACCOUNT_NUMBEROFEMPLOYEES("Account.NumberOfEmployees"),
    // Account.NumberofLocations__c
    ACCOUNT_NUMBEROFLOCATIONS__C("Account.NumberofLocations__c"),
    // Account.Ownership
    ACCOUNT_OWNERSHIP("Account.Ownership"),
    // Account.Parent
    ACCOUNT_PARENT("Account.Parent"),
    // Account.Phone
    ACCOUNT_PHONE("Account.Phone"),
    // Account.Rating
    ACCOUNT_RATING("Account.Rating"),
    // Account.SLAExpirationDate__c
    ACCOUNT_SLAEXPIRATIONDATE__C("Account.SLAExpirationDate__c"),
    // Account.SLASerialNumber__c
    ACCOUNT_SLASERIALNUMBER__C("Account.SLASerialNumber__c"),
    // Account.SLA__c
    ACCOUNT_SLA__C("Account.SLA__c"),
    // Account.ShippingAddress
    ACCOUNT_SHIPPINGADDRESS("Account.ShippingAddress"),
    // Account.Sic
    ACCOUNT_SIC("Account.Sic"),
    // Account.SicDesc
    ACCOUNT_SICDESC("Account.SicDesc"),
    // Account.Site
    ACCOUNT_SITE("Account.Site"),
    // Account.TickerSymbol
    ACCOUNT_TICKERSYMBOL("Account.TickerSymbol"),
    // Account.Tradestyle
    ACCOUNT_TRADESTYLE("Account.Tradestyle"),
    // Account.Type
    ACCOUNT_TYPE("Account.Type"),
    // Account.UpsellOpportunity__c
    ACCOUNT_UPSELLOPPORTUNITY__C("Account.UpsellOpportunity__c"),
    // Account.Website
    ACCOUNT_WEBSITE("Account.Website"),
    // Account.YearStarted
    ACCOUNT_YEARSTARTED("Account.YearStarted"),
    // Asset.Account
    ASSET_ACCOUNT("Asset.Account"),
    // Asset.Contact
    ASSET_CONTACT("Asset.Contact"),
    // Asset.Description
    ASSET_DESCRIPTION("Asset.Description"),
    // Asset.InstallDate
    ASSET_INSTALLDATE("Asset.InstallDate"),
    // Asset.IsCompetitorProduct
    ASSET_ISCOMPETITORPRODUCT("Asset.IsCompetitorProduct"),
    // Asset.Price
    ASSET_PRICE("Asset.Price"),
    // Asset.Product2
    ASSET_PRODUCT2("Asset.Product2"),
    // Asset.ProductCode
    ASSET_PRODUCTCODE("Asset.ProductCode"),
    // Asset.ProductDescription
    ASSET_PRODUCTDESCRIPTION("Asset.ProductDescription"),
    // Asset.ProductFamily
    ASSET_PRODUCTFAMILY("Asset.ProductFamily"),
    // Asset.PurchaseDate
    ASSET_PURCHASEDATE("Asset.PurchaseDate"),
    // Asset.Quantity
    ASSET_QUANTITY("Asset.Quantity"),
    // Asset.SerialNumber
    ASSET_SERIALNUMBER("Asset.SerialNumber"),
    // Asset.Status
    ASSET_STATUS("Asset.Status"),
    // Asset.UsageEndDate
    ASSET_USAGEENDDATE("Asset.UsageEndDate"),
    // Campaign.ActualCost
    CAMPAIGN_ACTUALCOST("Campaign.ActualCost"),
    // Campaign.AmountAllOpportunities
    CAMPAIGN_AMOUNTALLOPPORTUNITIES("Campaign.AmountAllOpportunities"),
    // Campaign.AmountWonOpportunities
    CAMPAIGN_AMOUNTWONOPPORTUNITIES("Campaign.AmountWonOpportunities"),
    // Campaign.BudgetedCost
    CAMPAIGN_BUDGETEDCOST("Campaign.BudgetedCost"),
    // Campaign.Description
    CAMPAIGN_DESCRIPTION("Campaign.Description"),
    // Campaign.EndDate
    CAMPAIGN_ENDDATE("Campaign.EndDate"),
    // Campaign.ExpectedResponse
    CAMPAIGN_EXPECTEDRESPONSE("Campaign.ExpectedResponse"),
    // Campaign.ExpectedRevenue
    CAMPAIGN_EXPECTEDREVENUE("Campaign.ExpectedRevenue"),
    // Campaign.HierarchyActualCost
    CAMPAIGN_HIERARCHYACTUALCOST("Campaign.HierarchyActualCost"),
    // Campaign.HierarchyAmountAllOpportunities
    CAMPAIGN_HIERARCHYAMOUNTALLOPPORTUNITIES("Campaign.HierarchyAmountAllOpportunities"),
    // Campaign.HierarchyAmountWonOpportunities
    CAMPAIGN_HIERARCHYAMOUNTWONOPPORTUNITIES("Campaign.HierarchyAmountWonOpportunities"),
    // Campaign.HierarchyBudgetedCost
    CAMPAIGN_HIERARCHYBUDGETEDCOST("Campaign.HierarchyBudgetedCost"),
    // Campaign.HierarchyExpectedRevenue
    CAMPAIGN_HIERARCHYEXPECTEDREVENUE("Campaign.HierarchyExpectedRevenue"),
    // Campaign.HierarchyNumberOfContacts
    CAMPAIGN_HIERARCHYNUMBEROFCONTACTS("Campaign.HierarchyNumberOfContacts"),
    // Campaign.HierarchyNumberOfConvertedLeads
    CAMPAIGN_HIERARCHYNUMBEROFCONVERTEDLEADS("Campaign.HierarchyNumberOfConvertedLeads"),
    // Campaign.HierarchyNumberOfLeads
    CAMPAIGN_HIERARCHYNUMBEROFLEADS("Campaign.HierarchyNumberOfLeads"),
    // Campaign.HierarchyNumberOfOpportunities
    CAMPAIGN_HIERARCHYNUMBEROFOPPORTUNITIES("Campaign.HierarchyNumberOfOpportunities"),
    // Campaign.HierarchyNumberOfResponses
    CAMPAIGN_HIERARCHYNUMBEROFRESPONSES("Campaign.HierarchyNumberOfResponses"),
    // Campaign.HierarchyNumberOfWonOpportunities
    CAMPAIGN_HIERARCHYNUMBEROFWONOPPORTUNITIES("Campaign.HierarchyNumberOfWonOpportunities"),
    // Campaign.HierarchyNumberSent
    CAMPAIGN_HIERARCHYNUMBERSENT("Campaign.HierarchyNumberSent"),
    // Campaign.IsActive
    CAMPAIGN_ISACTIVE("Campaign.IsActive"),
    // Campaign.NumberOfContacts
    CAMPAIGN_NUMBEROFCONTACTS("Campaign.NumberOfContacts"),
    // Campaign.NumberOfConvertedLeads
    CAMPAIGN_NUMBEROFCONVERTEDLEADS("Campaign.NumberOfConvertedLeads"),
    // Campaign.NumberOfLeads
    CAMPAIGN_NUMBEROFLEADS("Campaign.NumberOfLeads"),
    // Campaign.NumberOfOpportunities
    CAMPAIGN_NUMBEROFOPPORTUNITIES("Campaign.NumberOfOpportunities"),
    // Campaign.NumberOfResponses
    CAMPAIGN_NUMBEROFRESPONSES("Campaign.NumberOfResponses"),
    // Campaign.NumberOfWonOpportunities
    CAMPAIGN_NUMBEROFWONOPPORTUNITIES("Campaign.NumberOfWonOpportunities"),
    // Campaign.NumberSent
    CAMPAIGN_NUMBERSENT("Campaign.NumberSent"),
    // Campaign.Parent
    CAMPAIGN_PARENT("Campaign.Parent"),
    // Campaign.StartDate
    CAMPAIGN_STARTDATE("Campaign.StartDate"),
    // Campaign.Status
    CAMPAIGN_STATUS("Campaign.Status"),
    // Campaign.Type
    CAMPAIGN_TYPE("Campaign.Type"),
    // Case.Account
    CASE_ACCOUNT("Case.Account"),
    // Case.Asset
    CASE_ASSET("Case.Asset"),
    // Case.BusinessHours
    CASE_BUSINESSHOURS("Case.BusinessHours"),
    // Case.ClosedDate
    CASE_CLOSEDDATE("Case.ClosedDate"),
    // Case.Contact
    CASE_CONTACT("Case.Contact"),
    // Case.Description
    CASE_DESCRIPTION("Case.Description"),
    // Case.EngineeringReqNumber__c
    CASE_ENGINEERINGREQNUMBER__C("Case.EngineeringReqNumber__c"),
    // Case.IsClosedOnCreate
    CASE_ISCLOSEDONCREATE("Case.IsClosedOnCreate"),
    // Case.IsEscalated
    CASE_ISESCALATED("Case.IsEscalated"),
    // Case.Origin
    CASE_ORIGIN("Case.Origin"),
    // Case.Parent
    CASE_PARENT("Case.Parent"),
    // Case.PotentialLiability__c
    CASE_POTENTIALLIABILITY__C("Case.PotentialLiability__c"),
    // Case.Priority
    CASE_PRIORITY("Case.Priority"),
    // Case.Product__c
    CASE_PRODUCT__C("Case.Product__c"),
    // Case.Reason
    CASE_REASON("Case.Reason"),
    // Case.SLAViolation__c
    CASE_SLAVIOLATION__C("Case.SLAViolation__c"),
    // Case.Subject
    CASE_SUBJECT("Case.Subject"),
    // Case.SuppliedCompany
    CASE_SUPPLIEDCOMPANY("Case.SuppliedCompany"),
    // Case.SuppliedEmail
    CASE_SUPPLIEDEMAIL("Case.SuppliedEmail"),
    // Case.SuppliedName
    CASE_SUPPLIEDNAME("Case.SuppliedName"),
    // Case.SuppliedPhone
    CASE_SUPPLIEDPHONE("Case.SuppliedPhone"),
    // Case.Type
    CASE_TYPE("Case.Type"),
    // Contact.Account
    CONTACT_ACCOUNT("Contact.Account"),
    // Contact.AssistantName
    CONTACT_ASSISTANTNAME("Contact.AssistantName"),
    // Contact.AssistantPhone
    CONTACT_ASSISTANTPHONE("Contact.AssistantPhone"),
    // Contact.Birthdate
    CONTACT_BIRTHDATE("Contact.Birthdate"),
    // Contact.CleanStatus
    CONTACT_CLEANSTATUS("Contact.CleanStatus"),
    // Contact.Department
    CONTACT_DEPARTMENT("Contact.Department"),
    // Contact.Description
    CONTACT_DESCRIPTION("Contact.Description"),
    // Contact.DoNotCall
    CONTACT_DONOTCALL("Contact.DoNotCall"),
    // Contact.Email
    CONTACT_EMAIL("Contact.Email"),
    // Contact.Fax
    CONTACT_FAX("Contact.Fax"),
    // Contact.HasOptedOutOfEmail
    CONTACT_HASOPTEDOUTOFEMAIL("Contact.HasOptedOutOfEmail"),
    // Contact.HasOptedOutOfFax
    CONTACT_HASOPTEDOUTOFFAX("Contact.HasOptedOutOfFax"),
    // Contact.HomePhone
    CONTACT_HOMEPHONE("Contact.HomePhone"),
    // Contact.JBoss_Fuse__c
    CONTACT_JBOSS_FUSE__C("Contact.JBoss_Fuse__c"),
    // Contact.Jigsaw
    CONTACT_JIGSAW("Contact.Jigsaw"),
    // Contact.Languages__c
    CONTACT_LANGUAGES__C("Contact.Languages__c"),
    // Contact.LeadSource
    CONTACT_LEADSOURCE("Contact.LeadSource"),
    // Contact.Level__c
    CONTACT_LEVEL__C("Contact.Level__c"),
    // Contact.MailingAddress
    CONTACT_MAILINGADDRESS("Contact.MailingAddress"),
    // Contact.MobilePhone
    CONTACT_MOBILEPHONE("Contact.MobilePhone"),
    // Contact.OtherAddress
    CONTACT_OTHERADDRESS("Contact.OtherAddress"),
    // Contact.OtherPhone
    CONTACT_OTHERPHONE("Contact.OtherPhone"),
    // Contact.Phone
    CONTACT_PHONE("Contact.Phone"),
    // Contact.ReportsTo
    CONTACT_REPORTSTO("Contact.ReportsTo"),
    // Contact.Title
    CONTACT_TITLE("Contact.Title"),
    // Contract.ActivatedBy
    CONTRACT_ACTIVATEDBY("Contract.ActivatedBy"),
    // Contract.ActivatedDate
    CONTRACT_ACTIVATEDDATE("Contract.ActivatedDate"),
    // Contract.BillingAddress
    CONTRACT_BILLINGADDRESS("Contract.BillingAddress"),
    // Contract.CompanySigned
    CONTRACT_COMPANYSIGNED("Contract.CompanySigned"),
    // Contract.CompanySignedDate
    CONTRACT_COMPANYSIGNEDDATE("Contract.CompanySignedDate"),
    // Contract.ContractTerm
    CONTRACT_CONTRACTTERM("Contract.ContractTerm"),
    // Contract.CustomerSigned
    CONTRACT_CUSTOMERSIGNED("Contract.CustomerSigned"),
    // Contract.CustomerSignedDate
    CONTRACT_CUSTOMERSIGNEDDATE("Contract.CustomerSignedDate"),
    // Contract.CustomerSignedTitle
    CONTRACT_CUSTOMERSIGNEDTITLE("Contract.CustomerSignedTitle"),
    // Contract.Description
    CONTRACT_DESCRIPTION("Contract.Description"),
    // Contract.EndDate
    CONTRACT_ENDDATE("Contract.EndDate"),
    // Contract.Name
    CONTRACT_NAME("Contract.Name"),
    // Contract.OwnerExpirationNotice
    CONTRACT_OWNEREXPIRATIONNOTICE("Contract.OwnerExpirationNotice"),
    // Contract.Pricebook2
    CONTRACT_PRICEBOOK2("Contract.Pricebook2"),
    // Contract.ShippingAddress
    CONTRACT_SHIPPINGADDRESS("Contract.ShippingAddress"),
    // Contract.SpecialTerms
    CONTRACT_SPECIALTERMS("Contract.SpecialTerms"),
    // Contract.StartDate
    CONTRACT_STARTDATE("Contract.StartDate"),
    // Event.Description
    EVENT_DESCRIPTION("Event.Description"),
    // Event.IsAllDayEvent
    EVENT_ISALLDAYEVENT("Event.IsAllDayEvent"),
    // Event.Location
    EVENT_LOCATION("Event.Location"),
    // Event.Type
    EVENT_TYPE("Event.Type"),
    // Event.What
    EVENT_WHAT("Event.What"),
    // Event.Who
    EVENT_WHO("Event.Who"),
    // Goal.CompletionDate
    GOAL_COMPLETIONDATE("Goal.CompletionDate"),
    // Goal.IsKeyCompanyGoal
    GOAL_ISKEYCOMPANYGOAL("Goal.IsKeyCompanyGoal"),
    // Goal.OrigGoalId__c
    GOAL_ORIGGOALID__C("Goal.OrigGoalId__c"),
    // Idea.Attachment
    IDEA_ATTACHMENT("Idea.Attachment"),
    // Idea.Status
    IDEA_STATUS("Idea.Status"),
    // Lead.Address
    LEAD_ADDRESS("Lead.Address"),
    // Lead.AnnualRevenue
    LEAD_ANNUALREVENUE("Lead.AnnualRevenue"),
    // Lead.CleanStatus
    LEAD_CLEANSTATUS("Lead.CleanStatus"),
    // Lead.CompanyDunsNumber
    LEAD_COMPANYDUNSNUMBER("Lead.CompanyDunsNumber"),
    // Lead.CurrentGenerators__c
    LEAD_CURRENTGENERATORS__C("Lead.CurrentGenerators__c"),
    // Lead.DandbCompany
    LEAD_DANDBCOMPANY("Lead.DandbCompany"),
    // Lead.Description
    LEAD_DESCRIPTION("Lead.Description"),
    // Lead.DoNotCall
    LEAD_DONOTCALL("Lead.DoNotCall"),
    // Lead.Email
    LEAD_EMAIL("Lead.Email"),
    // Lead.Fax
    LEAD_FAX("Lead.Fax"),
    // Lead.HasOptedOutOfEmail
    LEAD_HASOPTEDOUTOFEMAIL("Lead.HasOptedOutOfEmail"),
    // Lead.HasOptedOutOfFax
    LEAD_HASOPTEDOUTOFFAX("Lead.HasOptedOutOfFax"),
    // Lead.Industry
    LEAD_INDUSTRY("Lead.Industry"),
    // Lead.Jigsaw
    LEAD_JIGSAW("Lead.Jigsaw"),
    // Lead.LastTransferDate
    LEAD_LASTTRANSFERDATE("Lead.LastTransferDate"),
    // Lead.LeadSource
    LEAD_LEADSOURCE("Lead.LeadSource"),
    // Lead.MobilePhone
    LEAD_MOBILEPHONE("Lead.MobilePhone"),
    // Lead.NumberOfEmployees
    LEAD_NUMBEROFEMPLOYEES("Lead.NumberOfEmployees"),
    // Lead.NumberofLocations__c
    LEAD_NUMBEROFLOCATIONS__C("Lead.NumberofLocations__c"),
    // Lead.Phone
    LEAD_PHONE("Lead.Phone"),
    // Lead.Primary__c
    LEAD_PRIMARY__C("Lead.Primary__c"),
    // Lead.ProductInterest__c
    LEAD_PRODUCTINTEREST__C("Lead.ProductInterest__c"),
    // Lead.Rating
    LEAD_RATING("Lead.Rating"),
    // Lead.SICCode__c
    LEAD_SICCODE__C("Lead.SICCode__c"),
    // Lead.Title
    LEAD_TITLE("Lead.Title"),
    // Lead.Website
    LEAD_WEBSITE("Lead.Website"),
    // Metric.OrigMetricId__c
    METRIC_ORIGMETRICID__C("Metric.OrigMetricId__c"),
    // Opportunity.Account
    OPPORTUNITY_ACCOUNT("Opportunity.Account"),
    // Opportunity.Amount
    OPPORTUNITY_AMOUNT("Opportunity.Amount"),
    // Opportunity.Campaign
    OPPORTUNITY_CAMPAIGN("Opportunity.Campaign"),
    // Opportunity.Contract
    OPPORTUNITY_CONTRACT("Opportunity.Contract"),
    // Opportunity.CurrentGenerators__c
    OPPORTUNITY_CURRENTGENERATORS__C("Opportunity.CurrentGenerators__c"),
    // Opportunity.DeliveryInstallationStatus__c
    OPPORTUNITY_DELIVERYINSTALLATIONSTATUS__C("Opportunity.DeliveryInstallationStatus__c"),
    // Opportunity.Description
    OPPORTUNITY_DESCRIPTION("Opportunity.Description"),
    // Opportunity.ExpectedRevenue
    OPPORTUNITY_EXPECTEDREVENUE("Opportunity.ExpectedRevenue"),
    // Opportunity.IsPrivate
    OPPORTUNITY_ISPRIVATE("Opportunity.IsPrivate"),
    // Opportunity.LeadSource
    OPPORTUNITY_LEADSOURCE("Opportunity.LeadSource"),
    // Opportunity.MainCompetitors__c
    OPPORTUNITY_MAINCOMPETITORS__C("Opportunity.MainCompetitors__c"),
    // Opportunity.NextStep
    OPPORTUNITY_NEXTSTEP("Opportunity.NextStep"),
    // Opportunity.OrderNumber__c
    OPPORTUNITY_ORDERNUMBER__C("Opportunity.OrderNumber__c"),
    // Opportunity.Probability
    OPPORTUNITY_PROBABILITY("Opportunity.Probability"),
    // Opportunity.TotalOpportunityQuantity
    OPPORTUNITY_TOTALOPPORTUNITYQUANTITY("Opportunity.TotalOpportunityQuantity"),
    // Opportunity.TrackingNumber__c
    OPPORTUNITY_TRACKINGNUMBER__C("Opportunity.TrackingNumber__c"),
    // Opportunity.Type
    OPPORTUNITY_TYPE("Opportunity.Type"),
    // OpportunityLineItem.Description
    OPPORTUNITYLINEITEM_DESCRIPTION("OpportunityLineItem.Description"),
    // OpportunityLineItem.Discount
    OPPORTUNITYLINEITEM_DISCOUNT("OpportunityLineItem.Discount"),
    // OpportunityLineItem.ListPrice
    OPPORTUNITYLINEITEM_LISTPRICE("OpportunityLineItem.ListPrice"),
    // OpportunityLineItem.ProductCode
    OPPORTUNITYLINEITEM_PRODUCTCODE("OpportunityLineItem.ProductCode"),
    // OpportunityLineItem.ServiceDate
    OPPORTUNITYLINEITEM_SERVICEDATE("OpportunityLineItem.ServiceDate"),
    // OpportunityLineItem.Subtotal
    OPPORTUNITYLINEITEM_SUBTOTAL("OpportunityLineItem.Subtotal"),
    // OpportunityLineItem.TotalPrice
    OPPORTUNITYLINEITEM_TOTALPRICE("OpportunityLineItem.TotalPrice"),
    // Order.ActivatedBy
    ORDER_ACTIVATEDBY("Order.ActivatedBy"),
    // Order.ActivatedDate
    ORDER_ACTIVATEDDATE("Order.ActivatedDate"),
    // Order.BillToContact
    ORDER_BILLTOCONTACT("Order.BillToContact"),
    // Order.BillingAddress
    ORDER_BILLINGADDRESS("Order.BillingAddress"),
    // Order.CompanyAuthorizedBy
    ORDER_COMPANYAUTHORIZEDBY("Order.CompanyAuthorizedBy"),
    // Order.CompanyAuthorizedDate
    ORDER_COMPANYAUTHORIZEDDATE("Order.CompanyAuthorizedDate"),
    // Order.ContractEndDate
    ORDER_CONTRACTENDDATE("Order.ContractEndDate"),
    // Order.ContractName
    ORDER_CONTRACTNAME("Order.ContractName"),
    // Order.CustomerAuthorizedBy
    ORDER_CUSTOMERAUTHORIZEDBY("Order.CustomerAuthorizedBy"),
    // Order.CustomerAuthorizedDate
    ORDER_CUSTOMERAUTHORIZEDDATE("Order.CustomerAuthorizedDate"),
    // Order.Description
    ORDER_DESCRIPTION("Order.Description"),
    // Order.Name
    ORDER_NAME("Order.Name"),
    // Order.Opportunity
    ORDER_OPPORTUNITY("Order.Opportunity"),
    // Order.OrderReferenceNumber
    ORDER_ORDERREFERENCENUMBER("Order.OrderReferenceNumber"),
    // Order.PoDate
    ORDER_PODATE("Order.PoDate"),
    // Order.PoNumber
    ORDER_PONUMBER("Order.PoNumber"),
    // Order.ShipToContact
    ORDER_SHIPTOCONTACT("Order.ShipToContact"),
    // Order.ShippingAddress
    ORDER_SHIPPINGADDRESS("Order.ShippingAddress"),
    // Order.TotalAmount
    ORDER_TOTALAMOUNT("Order.TotalAmount"),
    // Order.Type
    ORDER_TYPE("Order.Type"),
    // OrderItem.Description
    ORDERITEM_DESCRIPTION("OrderItem.Description"),
    // OrderItem.EndDate
    ORDERITEM_ENDDATE("OrderItem.EndDate"),
    // OrderItem.ListPrice
    ORDERITEM_LISTPRICE("OrderItem.ListPrice"),
    // OrderItem.ProductCode
    ORDERITEM_PRODUCTCODE("OrderItem.ProductCode"),
    // OrderItem.ServiceDate
    ORDERITEM_SERVICEDATE("OrderItem.ServiceDate"),
    // Product2.Description
    PRODUCT2_DESCRIPTION("Product2.Description"),
    // Product2.Family
    PRODUCT2_FAMILY("Product2.Family"),
    // Product2.ProductCode
    PRODUCT2_PRODUCTCODE("Product2.ProductCode"),
    // Solution.IsPublished
    SOLUTION_ISPUBLISHED("Solution.IsPublished"),
    // Solution.IsPublishedInPublicKb
    SOLUTION_ISPUBLISHEDINPUBLICKB("Solution.IsPublishedInPublicKb"),
    // Solution.SolutionNote
    SOLUTION_SOLUTIONNOTE("Solution.SolutionNote"),
    // Task.ActivityDate
    TASK_ACTIVITYDATE("Task.ActivityDate"),
    // Task.Description
    TASK_DESCRIPTION("Task.Description"),
    // Task.Type
    TASK_TYPE("Task.Type"),
    // Task.What
    TASK_WHAT("Task.What"),
    // Task.Who
    TASK_WHO("Task.Who");

    final String value;

    private FieldEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static FieldEnum fromValue(String value) {
        for (FieldEnum e : FieldEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
