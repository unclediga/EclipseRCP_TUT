package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestResize {
	static final int INSET = 10;

	public static void main(String[] args) {

		Display display = new Display();

		final Shell shell = new Shell(display);

		final Button b1 = new Button(shell, SWT.PUSH);

		final Button b2 = new Button(shell, SWT.PUSH);

		shell.addListener(SWT.Resize, new Listener() {

			public void handleEvent(Event event) {

				Rectangle rect = shell.getClientArea();

				int width = (rect.width - INSET * 3) / 2;

				b1.setBounds(

				INSET,

				INSET,

				width,

				rect.height - INSET * 2);

				b2.setBounds(

				width + INSET * 2,

				INSET,

				width,

				rect.height - INSET * 2);

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
