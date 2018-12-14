package adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Adapteur extends Thread {
	private String SID;
	private int FRQ;
	private String pathToSensor;
	private boolean occupied;
	private Queue<Character> queue;

	public Adapteur(String SID, int FRQ, String pathToSensor) {
		this.SID = SID;
		this.FRQ = FRQ;
		this.pathToSensor = pathToSensor;
		this.occupied = false;

		queue = new ArrayDeque<Character>();
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

				if (value == 1) {
					setOccupied(true);
					queue.add('1');
				} else {
					setOccupied(false);
					queue.add('0');
				}

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

	public Queue<Character> getQueue() {
		return queue;
	}

}
