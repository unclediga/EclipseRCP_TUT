package com.my.swt.tutorial;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class TestMonitor3 {
	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		shell.setSize(200, 200);

		Point pt = display.getCursorLocation();

		Point size = shell.getSize();

		Monitor[] monitors = display.getMonitors();

		for (int i = 0; i < monitors.length; i++) {

			if (monitors[i].getBounds().contains(pt)) {

				Rectangle rect = monitors[i].getClientArea();

				pt.x =

				Math.max(

				rect.x,

				Math.min(

				Math.max(pt.x, rect.x),

				rect.x + rect.width - size.x));

				pt.y =

				Math.max(

				rect.y,

				Math.min(

				Math.max(pt.y, rect.y),

				rect.y + rect.height - size.y));

				break;

			}

		}

		shell.setLocation(pt);

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
