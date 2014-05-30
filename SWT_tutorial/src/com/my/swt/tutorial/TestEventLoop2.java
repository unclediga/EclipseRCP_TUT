package com.my.swt.tutorial;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestEventLoop2 {
	public static void main(String[] args) {

		final Display display = new Display();

		final Shell shell = new Shell(display);

		shell.setSize(500, 64);

		shell.open();

		final boolean[] done = new boolean[1];
		////////////////////////////////////////////////
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Thread() iteratio : i="+i);
					try {
						Thread.sleep(1);
					} catch (Throwable th) {
					}
					display.syncExec(new Runnable() {
						public void run() {
							System.out.println("display.syncExec()  from Thread()");
							if (shell.isDisposed())
								return;
							shell.setText(shell.getText() + ".");
						}
					});
				}
				System.out.println("setting done=true in Thread() and wake()");
				done[0] = true;

				// wake the user interface thread from sleep
				display.wake();
			}
		}
		.start();

		System.out.println("sleep(2000) UI Thread" );
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {	}
		
		//////////////////////////////////////////////////
		System.out.println("Setting \"Running\"");
		shell.setText("Running ");
		while (!done[0]) {
			if (!display.readAndDispatch()){
				
				display.sleep();
				
			}else{
				System.out.println("Have event in UI-queue");
			}
		}
		///////////////////////////////////////////////
		if (!shell.isDisposed()) {
			shell.setText(shell.getText() + " done.");
			try {
				Thread.sleep(2000);
			} catch (Throwable th) {
			}
		}
		display.dispose();
	}

}
