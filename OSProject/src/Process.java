public class Process extends Thread {
	
	public int processID;
    ProcessState status = ProcessState.New;
	
	public Process(int m) {
		processID = m;
	}
	
	public void run() {
		
		OperatingSystem.readyQueue.remove(this);
		setProcessState(this, ProcessState.Running);
		
		switch (processID) {
			case 1: process1(); break;
			case 2: process2(); break;
			case 3: process3(); break;
			case 4: process4(); break;
			case 5: process5(); break;
		}
	}
	
	private void process1() {
		
		Semaphores.semPrintWait(this);
		OperatingSystem.printText("Enter File Name: ");
		Semaphores.semPrintPost();
		
		Semaphores.semPrintWait(this);
		Semaphores.semReadWait(this);
		Semaphores.semInputWait(this);
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		Semaphores.semPrintPost();
		Semaphores.semReadPost();
		Semaphores.semInputPost();
	
		setProcessState(this, ProcessState.Terminated);
	}
	
	private void process2() {
		
		Semaphores.semPrintWait(this);
		OperatingSystem.printText("Enter File Name: ");
		Semaphores.semPrintPost();
		
		Semaphores.semInputWait(this);
		String filename = OperatingSystem.TakeInput();
		Semaphores.semInputPost();
		
		Semaphores.semPrintWait(this);
		OperatingSystem.printText("Enter Data: ");
		Semaphores.semPrintPost();
		
		Semaphores.semInputWait(this);
		String data = OperatingSystem.TakeInput();
		Semaphores.semInputPost();

		Semaphores.semWriteWait(this);
		OperatingSystem.writefile(filename, data);
		Semaphores.semWritePost();
		
		setProcessState(this, ProcessState.Terminated);
	}
	
	private void process3() {
		
		int x = 0;
		
		while (x < 301) { 
			Semaphores.semPrintWait(this);
			OperatingSystem.printText(x + "\n");
			Semaphores.semPrintPost();
			x++;
		}
		
		setProcessState(this,ProcessState.Terminated);
	}
	
	private void process4() {
	
		int x = 500;
					
		while (x < 1001) {
			Semaphores.semPrintWait(this);
			OperatingSystem.printText(x+"\n");
			Semaphores.semPrintPost();
			x++;
		}
		
		setProcessState(this, ProcessState.Terminated);
	}
	
	private void process5() {
		
		Semaphores.semPrintWait(this);
		OperatingSystem.printText("Enter LowerBound: ");
		Semaphores.semPrintPost();
		
		Semaphores.semInputWait(this);
		String lower = OperatingSystem.TakeInput();
		Semaphores.semInputPost();
		
		Semaphores.semPrintWait(this);
		OperatingSystem.printText("Enter UpperBound: ");
		Semaphores.semPrintPost();
		
		Semaphores.semInputWait(this);
		String upper = OperatingSystem.TakeInput();
		Semaphores.semInputPost();
	
		int lowernbr = Integer.parseInt(lower);
		int uppernbr = Integer.parseInt(upper);
		String data = "";
		
		while (lowernbr <= uppernbr) {
			data += lowernbr++ +"\n";
		}	
		
		Semaphores.semWriteWait(this);
		OperatingSystem.writefile("P5.txt", data);
		Semaphores.semWritePost();
		
		setProcessState(this, ProcessState.Terminated);
	}
	
	 public static void setProcessState (Process p, ProcessState s) {
		 
		 p.status = s;
		 if (s == ProcessState.Terminated) {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}
