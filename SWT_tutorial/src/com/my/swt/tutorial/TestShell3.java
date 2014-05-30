package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class TestShell3 {
	public static void main(String[] args) {

		Display display = new Display();

		final Shell shell = new Shell(display);

		shell.setText("Close Example");

		shell.addListener(SWT.Close, new Listener() {

			public void handleEvent(Event event) {

				int style = SWT.YES | SWT.NO | SWT.CANCEL;

				style |= SWT.APPLICATION_MODAL;

				MessageBox box = new MessageBox(shell, style);

				box.setText(shell.getText());

				box.setMessage("Save changes?");

				switch (box.open()) {

				case SWT.YES:
					break;

				case SWT.NO:
					break;

				case SWT.CANCEL:

					event.doit = false;

					break;

				}

			}

		});

		shell.setSize(200, 200);

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
