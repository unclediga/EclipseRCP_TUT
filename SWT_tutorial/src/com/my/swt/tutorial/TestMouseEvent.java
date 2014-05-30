package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestMouseEvent {
	public static void main(String[] args) {

	    Display display = new Display();

	    Shell shell = new Shell(display);

	    Listener mouseListener = new Listener() {

	        public void handleEvent(Event e) {

	            String string = "UNKNOWN";

	            switch (e.type) {

	                case SWT.MouseDown: string = "DOWN"; break;

	                case SWT.MouseUp: string = "UP"; break;

	                case SWT.MouseMove: string = "MOVE"; break;

	                case SWT.MouseDoubleClick:

	                    string = "DOUBLE";

	                    break;

	                case SWT.MouseEnter: string="ENTER"; break;

	                case SWT.MouseExit: string = "EXIT"; break;

	                case SWT.MouseHover: string="HOVER"; break;

	            }

	            string += ": stateMask=0x"

	                + Integer.toHexString(e.stateMask);

	            if ((e.stateMask & SWT.CTRL) != 0)

	                string += " CTRL";

	            if ((e.stateMask & SWT.ALT) != 0)

	                string += " ALT";

	            if ((e.stateMask & SWT.SHIFT) != 0)

	                string += " SHIFT";

	            if ((e.stateMask & SWT.COMMAND) != 0)

	                string += " COMMAND";

	            if ((e.stateMask & SWT.BUTTON1) != 0)

	                string += " BUTTON1";

	            if ((e.stateMask & SWT.BUTTON2) != 0)

	                string += " BUTTON2";

	            if ((e.stateMask & SWT.BUTTON3) != 0)

	                string += " BUTTON3";

	            string += ", button=0x"

	                + Integer.toHexString(e.button);

	            string += ", x=" + e.x + ", y=" + e.y;

	            System.out.println(string);

	        }

	    };

	    shell.addListener(SWT.MouseDown, mouseListener);

	    shell.addListener(SWT.MouseUp, mouseListener);

	    shell.addListener(SWT.MouseMove, mouseListener);

	    shell.addListener(SWT.MouseDoubleClick, mouseListener);

	    shell.addListener(SWT.MouseEnter, mouseListener);

	    shell.addListener(SWT.MouseExit, mouseListener);

	    shell.addListener(SWT.MouseHover, mouseListener);

	    shell.setSize(200, 200);

	    shell.open();

	    while (!shell.isDisposed()) {

	        if (!display.readAndDispatch()) display.sleep();

	    }

	    display.dispose();

	}

}
