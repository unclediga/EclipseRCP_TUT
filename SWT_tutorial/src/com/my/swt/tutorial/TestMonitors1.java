package com.my.swt.tutorial;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;

public class TestMonitors1 {
	
	public static void main(String[] args) {
		
		

		Display display = new Display();
		
		Monitor[] list = display.getMonitors();

		System.out.println(list.length + " monitors.");

		for (int i = 0; i < list.length; i++) {

		    String string = "\t" + i + " - " + list[i].getBounds();

		    System.out.println(string);

		}

		System.out.println("Total bounds: " + display.getBounds());


		display.dispose();

	}

}
