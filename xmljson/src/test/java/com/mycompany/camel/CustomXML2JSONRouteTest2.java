package com.mycompany.camel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
public class CustomXML2JSONRouteTest2 extends CamelTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new XML2JSONRoute();
    }
    @Test
    public void marshalXML2JSON() throws UnsupportedEncodingException, IOException{   

        
        String content = new String(Files.readAllBytes(Paths.get("/home/wuxiaohui/cases/02245510/new/XML_SingleElement.txt")), "UTF-8");
        String expected  = new String(Files.readAllBytes(Paths.get("/home/wuxiaohui/cases/02245510/new/JSON_SingleElement.txt")), "UTF-8");
        final String response = template.requestBody("direct:marshalEmployeexml2json", content, String.class);
        System.out.println("response is : " + response);
        assertEquals(expected, response);
    }
    @Test
    public void unMarshalJSON2XML() throws UnsupportedEncodingException, IOException{
//        final String request = "{\"name\":\"ABC\",\"id\":\"123 \",\"type\":\"senior\"}";
        
        String request = new String(Files.readAllBytes(Paths.get("/home/wuxiaohui/cases/02245510/new/JSON_SingleElement.txt")), "UTF-8");
        String expected  = new String(Files.readAllBytes(Paths.get("/home/wuxiaohui/cases/02245510/new/XML_SingleElement.txt")), "UTF-8");

        
        final String response = template.requestBody("direct:unMarshalEmployeejson2xml", request, String.class);
        System.out.println("response is : " + response);
        assertEquals(expected, response);
    }
}
