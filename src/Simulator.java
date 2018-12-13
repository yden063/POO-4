import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Simulator extends Thread {
	private List<Adapteur> adapteurs;
	private int frequency;
	private String folderPath;
	private List<File> listFiles;

	public Simulator(int frequence, String folderPath) {
		this.frequency = frequence;
		this.folderPath = folderPath;

		this.listFiles = new ArrayList<File>();
		this.adapteurs = new ArrayList<Adapteur>();
	}

	public List<Adapteur> getAdapteurs() {
		return adapteurs;
	}

	public void setAdapteurs(List<Adapteur> adapteurs) {
		this.adapteurs = adapteurs;
	}

	@Override
	public void run() {
		while (true) {
			// Get all new files
			List<File> newFiles = getNewFiles();
			listFiles.addAll(newFiles);

			// Extract data from the files
			newFiles.forEach(file -> {
				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					BufferedReader bf = new BufferedReader(new InputStreamReader(fis));

					// SID
					String line = bf.readLine();
					String sid = line.split(":")[1];

					// Frequency
					line = bf.readLine();
					int frq = Integer.parseInt(line.split(":")[1]);

					// Path to sensor
					line = bf.readLine();
					String pathToSensor = line.split(":")[1];

					// Transform file to object
					System.out.println("Adapter creation " + sid);
					Adapteur adapteur = new Adapteur(sid, frq, pathToSensor);
					adapteur.start();
					

					// Add object to the list
					adapteurs.add(adapteur);

					bf.close();
					fis.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			// Every frequency time it will execute
			try {
				Thread.sleep(this.frequency * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private List<File> getNewFiles() {
		File folder = new File(this.folderPath);
		File temporaryFiles[] = folder.listFiles();
		List<File> tmpFileObject = new ArrayList<File>();

		for (int i = 0; i < temporaryFiles.length; i++) {
			tmpFileObject.add(temporaryFiles[i]);
		}

		tmpFileObject.removeAll(this.listFiles);

		return tmpFileObject;
	}

	public static void main(String[] args) {
		Simulator simulator = new Simulator(2, "./resources/files_descriptor");
		simulator.start();
	}

}