package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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

			System.out.println("----------- Welcome, please select a service to execute (S1 or S2): ----------- ");
			displayServices();

			Scanner scanner = new Scanner(System.in);
			String selectedService = scanner.nextLine();

			// GET:S1:002
			// GET:S2
			// GET:S3

			String messageToSend = "";

			switch (selectedService) {
			case "S1":
				System.out.println("Which sensor do you want information : (Example : '003')");
				String sensorNum = scanner.nextLine();
				messageToSend = "GET:S1:" + sensorNum;
				break;
			case "S2":
				messageToSend = "GET:S2";
				break;
			default:
				System.out.println("This service doesn't exist ! You will receive a null response");
				break;
			}

			pw.println(messageToSend);
			pw.flush();

			is = socket.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String response = bf.readLine();

			System.out.println();
			System.out.println("Received => " + response);
			System.out.println("Thank you!");
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayServices() {
		System.out.print("Service 1 (S1) : State of a given sensor \t ");
		System.out.print("Service 2 (S2) : Get all sensors with their informations");
	}

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 32745);
		client.connect();
	}
}
