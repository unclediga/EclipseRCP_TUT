package com.my.swt.tutorial;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class TestMonitor2 {
	public static void main(String[] args) {

	    Display display = new Display();

	    Shell shell = new Shell(display);

	    Monitor primary = display.getPrimaryMonitor();

	    Rectangle bounds = primary.getBounds();

	    Rectangle rect = shell.getBounds();

	    int x = bounds.x

	        + Math.max(0, (bounds.width - rect.width) / 2);

	    int y = bounds.y

	        + Math.max(0, (bounds.height - rect.height) / 2);

	    shell.setBounds(x, y, rect.width, rect.height);

	    shell.open();

	    while (!shell.isDisposed()) {

	        if (!display.readAndDispatch()) display.sleep();

	    }

	    display.dispose();

	}


}
