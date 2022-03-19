import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OperatingSystem {
	
	public static Queue<Process> readyQueue = new LinkedList<Process>();
	public static ArrayList<Thread> ProcessTable;
	
	public static String readFile (String name) {
		
		String Data = "";
		File file = new File(name);
		
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				Data += scan.nextLine() + "\n";
			}
			scan.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return Data;
	}
	
	public static void writefile (String name, String data) {
		
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void printText(String text) {

		System.out.println(text);
	}
	
	@SuppressWarnings("resource")
	public static String TakeInput() {
		
		Scanner in = new Scanner(System.in);
		String data = in.nextLine();
		return data;
	}
	
	private static void createProcess (int processID){
		
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p, ProcessState.Ready);
		readyQueue.add(p);
		//p.start() for threading
		//p.start();
	}
	
	private static void fcfsSecheduler () {
		
		Process p = null;
		
		if (readyQueue.size() != 0) {
			p = readyQueue.remove();
			p.start();
		}
		
		do {
			
			if (p.status != ProcessState.Terminated)
				continue;
			
			p = readyQueue.remove();
			p.start();
		} 
		while (readyQueue.size() > 0);
	}
	
	public static void main (String[] args) {
		
   		ProcessTable = new ArrayList<Thread>();

		createProcess(1);
		createProcess(2);
		createProcess(3);
		createProcess(4);
		createProcess(5);
		
		fcfsSecheduler ();
	}
}



