import java.io.*;
import java.net.*;

public class ClientSide {

    private Socket s;
    private String host;
    private int port;
    private boolean byteTransferMode = false;

    public ClientSide(String _host, int _port) {
	host = _host;
	port = _port;
	s = new Socket();
    }
    public void run() {
	try {
	    BufferedReader in = new BufferedReader(new FileReader("infile"));//TODO Transfer name of file
	    try {
		s.connect(new InetSocketAddress(host,port));
		OutputStream outStream = s.getOutputStream();
		PrintWriter out = new PrintWriter(outStream, true);
		String line = new String();
		      while((line = in.readLine()) != null) {
			if(byteTransferMode){
			  byte [] lineArrayByte = line.getBytes("utf-8"); 
			  for(int i = 0; i < lineArrayByte.length; i++)  
			      outStream.write(lineArrayByte[i]);
			  outStream.write('\n');
			} else {
			    out.println(line);
			}
		    }
		s.close();
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
