package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestMoveResize {
	public static void main(String[] args) {

		// NOTE – contains a problem (see SWT.Resize)

		Display display = new Display();
		final Shell shell1 = new Shell(display);

		shell1.pack();
		shell1.open();

		final Shell shell2 = new Shell(shell1, SWT.NONE);

		shell2.pack();

		shell2.open();

		Rectangle rect = shell1.getBounds();

		shell2.setLocation(rect.x + rect.width + 2, rect.y);

		Listener listener = new Listener() {

			public void handleEvent(Event e) {

				Rectangle rect = shell1.getBounds();

				shell2.setLocation(

				rect.x + rect.width + 2, rect.y);

			}

		};

		shell1.addListener(SWT.Move, listener);

		shell1.addListener(SWT.Resize, listener);

		while (!shell1.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
