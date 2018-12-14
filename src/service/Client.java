package service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private String adresseIp;
	private int port;
	private OutputStream os;
	private InputStream is;
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

			// GET:S1:002
			// GET:S2
			// GET:S3
			pw.println("GET:S1:002");
			pw.flush();

			is = socket.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String response = bf.readLine();
			System.out.println("Received " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 32745);
		client.connect();
	}
}
