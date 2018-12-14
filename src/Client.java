import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private String adresseIp;
	private int port;
	private OutputStream os;
	private Socket socket;
	private PrintWriter pw;

	public Client(String adresseIp, int port) {
		this.adresseIp = adresseIp;
		this.port = port;
	}

	public void connect() {
		try {
			socket = new Socket(this.adresseIp, this.port);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);

			pw.println("GET:S1:002");
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 32745);
		client.connect();
	}
}
