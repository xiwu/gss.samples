package org.mycompany;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloBean implements Hello {

    private String say = "Hello World";
    private String bye = "Bye World";
    
    public String hello() {
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return say + " at " + sdf.format(new Date());
    }

    public String bye() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return bye + " at " + sdf.format(new Date());
    }


}
