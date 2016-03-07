import java.net.Socket;

public class MainClass {
    public static void main (String[] args) {

	new ClientSide("localhost", 8189).run();
    }
}
