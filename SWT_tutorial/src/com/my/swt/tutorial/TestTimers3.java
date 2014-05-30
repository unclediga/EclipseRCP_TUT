package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestTimers3 {
	public static void main(String[] args) {

		final Display display = new Display();

		Shell shell = new Shell(display);

		shell.pack();

		shell.open();

		final int TIME_OUT = 3000;

		final Runnable runnable = new Runnable() {

			public void run() {

				System.out.println(

				"Idle for "

				+ (TIME_OUT / 1000)

				+ " seconds");

				display.timerExec(TIME_OUT, this);

			}

		};

		display.addFilter(SWT.KeyDown, new Listener() {

			public void handleEvent(Event event) {

				display.timerExec(TIME_OUT, runnable);

			}

		});

		display.timerExec(TIME_OUT, runnable);

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
