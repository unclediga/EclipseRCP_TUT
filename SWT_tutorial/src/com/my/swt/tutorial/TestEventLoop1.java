package com.my.swt.tutorial;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestEventLoop1 {
	public static void main(String[] args) {

	    final Display display = new Display();

	    Shell shell = new Shell(display);

	    shell.pack();

	    shell.open();

	    final boolean[] done = new boolean[1];

	    new Thread() {

	        public void run() {

	            for (int i = 0; i < 10; i++) {

	                try {

	                    Thread.sleep(500);

	                } catch (Throwable th) {

	                }

	                System.out.print(".");

	            }

	            done[0] = true;
	            
	            // without wake() method call, shell will exist, 
	            // till any event will appear (for example, MouseMove).
	            // The shell immediately will be closed with a "done" message
	            
	            display.wake();                 // wake the event loop

	                                            // thread from sleep

	        }

	    }

	    .start();

	    System.out.print("Running ");


	    while (!done[0]) {

	        if (!display.readAndDispatch()) display.sleep();

	    }

	    System.out.print(" done.");

	    display.dispose();

	}

}
