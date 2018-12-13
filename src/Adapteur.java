import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Adapteur extends Thread {
	private String SID;
	private int FRQ;
	private String pathToSensor;
	private boolean occupied;

	public Adapteur(String SID, int FRQ, String pathToSensor) {
		this.SID = SID;
		this.FRQ = FRQ;
		this.pathToSensor = pathToSensor;
		this.occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getPathToSensor() {
		return pathToSensor;
	}

	public int getFRQ() {
		return FRQ;
	}

	public String getSID() {
		return SID;
	}

	@Override
	public void run() {
		System.out.println(this.SID + " started!");
		while (true) {

			try {

				FileInputStream fis = new FileInputStream(new File(this.pathToSensor));
				BufferedReader bf = new BufferedReader(new InputStreamReader(fis));

				int value = Integer.parseInt(bf.readLine());

				bf.close();
				fis.close();

				if (value == 1)
					setOccupied(true);
				else
					setOccupied(false);
			} catch (FileNotFoundException e1) {
				System.out.println("Sensor not found! : " + this.pathToSensor);
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("SID : " + this.SID + " occupied : " + this.occupied);
			try {
				Thread.sleep(this.FRQ * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Adapteur adapteur = new Adapteur("", 4, "./resources/sensors/sensor002.txt");
		adapteur.start();
	}

}
