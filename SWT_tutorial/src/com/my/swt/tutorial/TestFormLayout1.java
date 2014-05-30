package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestFormLayout1 {
	public static void main(String[] args) {

	    Display display = new Display();

	    Shell shell = new Shell(display);

	    FormLayout layout = new FormLayout();

	    shell.setLayout(layout);

	    Button button1 = new Button(shell, SWT.PUSH);

	    button1.setText("Button 1");

	    FormData data1 = new FormData();

	    data1.left = new FormAttachment(0, 14);

	    data1.top = new FormAttachment(0, 35);

	    button1.setLayoutData(data1);

	    Button button2 = new Button(shell, SWT.PUSH);

	    button2.setText("Button 2");

	    FormData data2 = new FormData();

	    data2.left = new FormAttachment(0, 73);

	    data2.top = new FormAttachment(0, 5);

	    button2.setLayoutData(data2);

	    shell.pack();

	    shell.open();

	    while (!shell.isDisposed()) {

	        if (!display.readAndDispatch()) display.sleep();

	    }

	    display.dispose();

	}

}
