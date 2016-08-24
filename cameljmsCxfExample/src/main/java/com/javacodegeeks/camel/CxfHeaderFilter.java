package com.javacodegeeks.camel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.component.cxf.common.header.SoapMessageHeaderFilter;
import org.apache.camel.spi.HeaderFilterStrategy.Direction;
import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

public class CxfHeaderFilter extends SoapMessageHeaderFilter {

	Logger log = Logger.getLogger(getClass());

	@Override
	public void filter(Direction direction, List<Header> headers) {
		log.info("==============================");		
		super.filter(direction, headers);
		
		if (Direction.IN.equals(direction)) {
			log.info("not filtering IN headers");
			return;
		}
	
		Iterator<Header> iterator = headers.iterator();
        while (iterator.hasNext()) {
        	Header header = iterator.next();
        	
        	for (String headerName : this.headers) {

        		if (header == null || header.getName() == null)
        			continue;
        		
        		if (header.getName().toString().contains(headerName)) {
        			iterator.remove();
        			log.info("removed header " + header);
        		} else
        			log.info("kept header " + header);
        	}
        }
	}

	private List<String> headers = new ArrayList<String>();
	
	public void setHeaders(List<String> headers) {
		this.headers = headers;
		
		log.info("Loaded headers to filter: " + Arrays.toString(headers.toArray()));
	}
	
	public CxfHeaderFilter() {
//		 <value>dam.dms</value>
//		  <value>documentId</value>
//		  <value>iteration</value>
//		  <value>metadata.</value>
//		  <value>publish.</value>
//		  <value>dam.dms.get.doc.retry.delay.buffer</value>
//		  <value>singularityheader</value>
		
		setHeaders(Arrays.asList("dam.dms", "dam-dms", "documentId", "iteration", "metadata.", "publish.", "dam.dms.get.doc.retry.delay.buffer", "X-Forwarded"));
	}
	
	
}
