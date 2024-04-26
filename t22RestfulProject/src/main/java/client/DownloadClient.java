package client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.io.*;

public class DownloadClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uri = "http://127.0.0.1:8081/rest/downloadservice/downloadfile";
	    
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Response response=b.get();
		//Getting file name from the Response header. Header 'filename' is set in the service.
		String fileName=response.getHeaderString("filename");
		//Getting the InputStream object from the Response
		InputStream is=response.readEntity(InputStream.class);
	    try{
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        OutputStream out = new FileOutputStream(new File("C:/temp/"+fileName));
	        //Writing the bytes from the InputStream in one kB pieces 
	        while ((read = is.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();//Flushing the OutputStream to the mass media
	        out.close();//And closing the stream
	        
	    } 
	    catch (IOException e){
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }		
		
		System.out.println("File "+fileName+" downloaded!");
	}

}