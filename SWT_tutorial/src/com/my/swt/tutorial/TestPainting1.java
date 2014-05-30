package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestPainting1 {
	public static void main(String[] args) {

		Display display = new Display();

		final Shell shell = new Shell(display);

		shell.addListener(SWT.Paint, new Listener() {

			public void handleEvent(Event event) {

				GC gc = event.gc;

				Rectangle rect = shell.getClientArea();

				gc.drawArc(0, 0, rect.width, rect.height, 0, 360);

			}

		});

		shell.setSize(150, 150);

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
