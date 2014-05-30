package com.my.swt.tutorial;

import org.eclipse.swt.widgets.Display;

public class TestTimers1 {
	
	public static void main(String[] args) {

		final Display display = new Display();
		
		display .timerExec(1000, new Runnable() {

		    public void run() {

		        System.out.println("Once, after 1 seconds.");
		        System.out.println("1 display disposed : "+display.isDisposed());
		        
		        display.timerExec(2000, this);

		    }

		});
		
		
		display.sleep();
		System.out.println("dispose...");
		display.dispose();
		System.out.println("2 display disposed : "+display.isDisposed());


	} 
	

}
