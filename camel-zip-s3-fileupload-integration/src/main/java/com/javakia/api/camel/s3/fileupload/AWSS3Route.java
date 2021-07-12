package com.javakia.api.camel.s3.fileupload;

import java.util.Iterator;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Constants;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.dataformat.ZipFileDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that moves an object from an endpoint to AWS S3 Bucket
 * Spring boot provides auto-configuration for Apache Camel and the camel route
 * will be started automatically.
 * The required dependencies are provided in the pom.xml file
 * <p/>
 * 
 * @Component annotation will make spring boot/camel auto detect this route when
 *            starting.
 */

@Component
public class AWSS3Route extends RouteBuilder {

   
	@Override
	public void configure() throws Exception {
		

		
		
		from("{{from.component}}:{{from.endpoint}}").routeId("camelS3Route")
				.setHeader(S3Constants.CONTENT_LENGTH, simple("${in.header.CamelFileLength}"))
				//This is the filename on the S3 bucket
				.setHeader(S3Constants.KEY, simple("${in.header.CamelFileNameOnly}"))
				//aws-s3 the camel component and  and that is how you tell camel where you want to upload. 
				.to("aws-s3://{{awsS3BucketName}}?deleteAfterWrite=false&region={{awsRegion}}&accessKey={{awsAccessKey}}&secretKey=RAW({{awsAccessSecretKey}})")
				//optionally logging success message
				.log("${in.header.CamelFileNameOnly} succesfully uploaded to S3 {{awsS3BucketName}} bucket");
		
		
        ZipFileDataFormat zipFile = new ZipFileDataFormat();
        zipFile.setUsingIterator(true);

        from("file:///Users/wuxiaohui/Downloads/testzip").routeId("ROUTE_ID_SFTP_MAIN")
     .unmarshal(zipFile).split(bodyAs(Iterator.class)).streaming().convertBodyTo(String.class).to("direct:" + "routemain");

        
        
		from("direct:" + "routemain")
        .log("**body we receive: ${body}")
        .marshal().zipFile()
        .setHeader(Exchange.CONTENT_TYPE, constant("application/zip"))
        .to("file:///Users/wuxiaohui/Downloads/test");
		
	}

}
