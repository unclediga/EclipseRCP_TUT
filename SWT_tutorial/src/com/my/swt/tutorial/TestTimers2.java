package com.my.swt.tutorial;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestTimers2 {
	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		shell.pack();

		shell.open();

		final int TIME_OUT = 3000;

		Runnable runnable = new Runnable() {

			public void run() {

				System.out.println(

				"Idle for "

				+ (TIME_OUT / 1000)

				+ " seconds");

			}

		};

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()) {

				display.timerExec(TIME_OUT, runnable);

				display.sleep();

				System.out.println("display wake up...");
				
				display.readAndDispatch();

				display.timerExec(-1, runnable);

			}

		}

		display.dispose();

	}

}
