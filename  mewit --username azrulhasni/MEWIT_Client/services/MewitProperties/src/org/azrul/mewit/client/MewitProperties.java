package org.azrul.mewit.client;
import java.util.Properties;
import java.io.IOException;

/**
 * This is a client-facing service class.  All
 * public methods will be exposed to the client.  Their return
 * values and parameters will be passed to the client or taken
 * from the client, respectively.  This will be a singleton
 * instance, shared between all requests. 
 * 
 * To log, call the superclass method log(LOG_LEVEL, String) or log(LOG_LEVEL, String, Exception).
 * LOG_LEVEL is one of FATAL, ERROR, WARN, INFO and DEBUG to modify your log level.
 * For info on these levels, look for tomcat/log4j documentation
 */
public class MewitProperties extends com.wavemaker.runtime.javaservice.JavaServiceSuperClass {
    /* Pass in one of FATAL, ERROR, WARN,  INFO and DEBUG to modify your log level;
     *  recommend changing this to FATAL or ERROR before deploying.  For info on these levels, look for tomcat/log4j documentation
     */
    public MewitProperties() {
       super(INFO);
    }


    
    public static String getTemporaryUploadDirectory(){
       try{
         Properties props = new Properties();
         props.load(MewitProperties.class.getResourceAsStream("/org/azrul/epice/config/mewitclient.properties"));
         return props.getProperty("temporary.upload.directory"); 
       }catch(IOException e){
         MewitProperties m = new MewitProperties();
         m.log(ERROR, e.toString()); 
       }
       return null;
    }
    
    public static String getMewitUrl(){
       try{
         Properties props = new Properties();
         props.load(MewitProperties.class.getResourceAsStream("/org/azrul/epice/config/mewitclient.properties"));
         return props.getProperty("mewit.url"); 
       }catch(IOException e){
         MewitProperties m = new MewitProperties();
         m.log(ERROR, e.toString()); 
       }
       return null;
    }

}
