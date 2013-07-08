package properties;

import java.io.IOException;
import java.util.Properties;

public class MyProperties extends Properties{
	private String language = "spanish";
	
    public MyProperties(){
    	init();
    }
    
    public MyProperties(String language){
    	this.language = language;
    }
    
    public void init() {
    	switch(language) {
 	   	case "spanish":
 	   	   getProperties("spanish.properties");
 	   	   break;
 	   	case "english":
 		   getProperties("english.properties");
 		   break;
 	   	default:
 		   getProperties("spanish.properties");
	    	   break;
    	}
    }
 
    /* se leen las propiedades */
     private void getProperties(String idioma) {
        try {
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     }
     
     public void setLanguage(String language) {
    	 this.language = language;
     }
     
     public String getLanguage() {
    	 return this.language;
     }
 
}