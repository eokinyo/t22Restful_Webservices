package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.io.*;

@Path("/downloadservice")
public class DownloadService {
	@GET
	@Path("/downloadfile")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
//	@Produces("image/png")
	public Response downloadFile() {
        String fileName = "berry.png";
        File file = new File("F:/images/"+fileName);
        ResponseBuilder response = Response.ok((Object) file);
//The header “Content-Disposition” is to tell Browser the name of file
        response.header("Content-Disposition", "attachment;filename=" + fileName);
//The next is easier to Console app client or a servlet client - no need to split header into pieces        
        response.header("filename", fileName);
        return response.build();
	}
}