package com.my.swt.tutorial;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestCursor1 {
	public static void main(String[] args) {

		final Display display = new Display();

		final Control[] last = new Control[1];

		display.timerExec(100, new Runnable() {

			public void run() {

				Control control = display.getCursorControl();

				if (control != last[0]) {

					if (last[0] != null

					&& !last[0].isDisposed()) {

						System.out.println("Exit:" + last[0]);

					}

					if (control != null

					&& !control.isDisposed()) {

						System.out.println("Enter:" + control);

					}

				}

				last[0] = control;

				display.timerExec(100, this);

			}

		});

		Shell shell = new Shell(display);

		shell.pack();

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
