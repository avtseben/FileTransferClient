import java.io.*;
import java.net.*;

public class ClientSide {

    private Socket s;
    private String host;
    private int port;
    private String fileName = new String();

    public ClientSide(String _host, int _port) {
	host = _host;
	port = _port;
	fileName = "testfile";
	//transferSocket = new Socket();
    }
    public void run() {
	transferManager();
    }
    private void transferManager() {
	try (
	     Socket transferSocket = new Socket(host,port);
	     PrintWriter out = new PrintWriter(transferSocket.getOutputStream(), true);
	     BufferedReader in = new BufferedReader(
	         new InputStreamReader(transferSocket.getInputStream()));
	) {  
	  if(in.readLine().equals("sayFileName")) {
		 out.println("fileNameIs");
		 out.println(fileName);
		 if(in.readLine().equals("youCanStartTransfer"))
		    transferFile(out);
	     }

	} catch (Exception e) {
	    System.err.println("Cannot connect");
	    System.err.println(e);
	}
    }
    public void transferFile(PrintWriter out) {
	try {
	    BufferedReader in = new BufferedReader(new FileReader(fileName));//TODO Transfer name of file
	    try {
		String line = new String();
		      while((line = in.readLine()) != null) {
			out.println(line);
		    }
		out.close();

	    } catch (Exception e) {
		System.err.println("Cannot connect");
		System.err.println(e);
	    }
	    in.close();
	} catch (Exception e) {
	    System.out.println("Cannot open input file");
	}
    }

}
