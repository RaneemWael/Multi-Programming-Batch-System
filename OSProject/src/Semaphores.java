import java.util.LinkedList;
import java.util.Queue;

public class Semaphores {
	
	//used = 1
	public static int readSem = 0;
	public static int writeSem = 0;
	public static int printSem = 0;
	public static int inputSem = 0;
	public static Queue<Process> readQueue = new LinkedList<Process>();
	public static Queue<Process> writeQueue = new LinkedList<Process>();
	public static Queue<Process> printQueue = new LinkedList<Process>();
	public static Queue<Process> inputQueue = new LinkedList<Process>();
	
	public Semaphores () {}
	
	public static void semReadWait (Process p) {
		
		if (readSem == 0)
			readSem = 1;
		else {
			readQueue.add(p);
			Process.setProcessState(p, ProcessState.Waiting);
			p.suspend();
		}
	}
	
	public static void semReadPost () {
		
		readSem = 0;
		
		if (readQueue.size() != 0) {
			Process pReady = readQueue.remove();
			Process.setProcessState(pReady, ProcessState.Ready);
			OperatingSystem.readyQueue.add(pReady);
			pReady.resume();
		}
	}
	
	public static void semWriteWait (Process p) {
		
		if (writeSem == 0)
			writeSem = 1;
		else {
			writeQueue.add(p);
			Process.setProcessState(p, ProcessState.Waiting);
			p.suspend();
		}
	}
	
	public static void semWritePost () {
		
		writeSem = 0;
		
		if (writeQueue.size() != 0) {
			Process pReady = writeQueue.remove();
			Process.setProcessState(pReady, ProcessState.Ready);
			OperatingSystem.readyQueue.add(pReady);
			pReady.resume();
		}
	}
	
	public static void semPrintWait (Process p) {
		
		if (printSem == 0)
			printSem = 1;
		else {
			printQueue.add(p);
			Process.setProcessState(p, ProcessState.Waiting);
			p.suspend();
		}
	}
	
	public static void semPrintPost () {
		
		printSem = 0;
		
		if (printQueue.size() != 0) {
			Process pReady = printQueue.remove();
			Process.setProcessState(pReady, ProcessState.Ready);
			OperatingSystem.readyQueue.add(pReady);
			pReady.resume();
		}
	}
	
	public static void semInputWait (Process p) {
		
		if (inputSem == 0)
			inputSem = 1;
		else {
			inputQueue.add(p);
			Process.setProcessState(p, ProcessState.Waiting);
			p.suspend();
		}
	}
	
	public static void semInputPost () {
		
		inputSem = 0;
		
		if (inputQueue.size() != 0) {
			Process pReady = inputQueue.remove();
			Process.setProcessState(pReady, ProcessState.Ready);
			OperatingSystem.readyQueue.add(pReady);
			pReady.resume();
		}
	}

}
