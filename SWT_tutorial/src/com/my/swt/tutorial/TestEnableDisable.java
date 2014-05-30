package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestEnableDisable {
	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		shell.setLayout(new FillLayout());

		Composite composite = new Composite(shell, SWT.NULL);

		composite.setEnabled(true);

		final Button button = new Button(composite, SWT.PUSH);

		button.setText("Drag Me");

		button.pack();

		Listener listener = new Listener() {

			Point offset = null;

			public void handleEvent(Event e) {

				switch (e.type) {

				case SWT.MouseDown:

					Rectangle rect = button.getBounds();

					if (!rect.contains(e.x, e.y))
						break;

					Point pt = button.getLocation();

					offset = new Point(e.x - pt.x, e.y - pt.y);

					break;

				case SWT.MouseMove:

					if (offset == null)
						break;

					button.setLocation(

					e.x - offset.x,

					e.y - offset.y);

					break;

				case SWT.MouseUp:

					offset = null;

					break;

				}

			}

		};

		shell.addListener(SWT.MouseDown, listener);

		shell.addListener(SWT.MouseUp, listener);

		shell.addListener(SWT.MouseMove, listener);

		shell.setSize(300, 300);

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
