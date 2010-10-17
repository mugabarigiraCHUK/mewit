/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.rest.service;

import com.thoughtworks.xstream.XStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.azrul.epice.rest.dto.DownloadFileRequest;
import org.azrul.epice.rest.dto.DownloadFileResponse;
import org.azrul.epice.rest.dto.UploadFileRequest;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("downloadFile")
public class DownloadFileResource {

    @Context
    private UriInfo context;
    @Context
    javax.servlet.http.HttpServletResponse servletResponse;

    /** Creates a new instance of DownloadFileResource */
    public DownloadFileResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.DownloadFileResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("DownloadFileResponse", DownloadFileResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("DownloadFileRequest", DownloadFileRequest.class);

        DownloadFileResponse errorDownloadFileResponse = new DownloadFileResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("DOWNLOAD FILE ERROR");
        errorDownloadFileResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();
        try {
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            DownloadFileRequest oRequest = (DownloadFileRequest) reader.fromXML(request);
            DownloadFileResponse oResponse = doService(oRequest);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return writer.toXML(errorDownloadFileResponse);
    }

    public DownloadFileResponse doService(DownloadFileRequest oRequest) {
        DataInputStream in = null;
        DownloadFileResponse oResponse = new DownloadFileResponse();
        try {
            ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
           
            File file = new File(props.getString("FILE_REPOSITORY") + "/" + oRequest.getFile());
            byte[] bbuf = new byte[1024];
            in = new DataInputStream(new FileInputStream(file));
            int length = 0;
            while ((in != null) && ((length = in.read(bbuf)) != -1)) {
                servletResponse.getOutputStream().write(bbuf);
            }
            in.close();
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
            oResponse.setSessionId(oRequest.getSessionId());
            return oResponse;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadFileResource.class.getName()).log(Level.SEVERE, null, ex);
            List<String> errors = new ArrayList<String>();
            errors.add("DOWNLOAD FILE ERROR");
            errors.add("File not found");
            oResponse.setErrors(errors);
            return oResponse;
        } catch (IOException ex) {
            Logger.getLogger(DownloadFileResource.class.getName()).log(Level.SEVERE, null, ex);
            List<String> errors = new ArrayList<String>();
            errors.add("DOWNLOAD FILE ERROR");
            errors.add("IO exception");
            oResponse.setErrors(errors);
            return oResponse;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(DownloadFileResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * PUT method for updating or creating an instance of DownloadFileResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
