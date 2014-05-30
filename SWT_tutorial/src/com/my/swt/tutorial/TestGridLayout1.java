package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class TestGridLayout1 {
	public static void main(String[] args) {

	    Display display = new Display();

	    Shell shell = new Shell(display);

	    GridLayout layout = new GridLayout();

	    layout.numColumns = 2;

	    shell.setLayout(layout);

	    Label label = new Label(shell, SWT.NONE);

	    label.setText("This is a very simple MessageBox.");

	    GridData labelData = new GridData();

	    labelData.horizontalSpan = 2;

	    label.setLayoutData(labelData);

	    Button okButton = new Button(shell, SWT.PUSH);

	    okButton.setText("OK");

	    GridData okData =

	        new GridData(SWT.CENTER, SWT.CENTER, true, true);

	    okButton.setLayoutData(okData);

	    Button cancelButton = new Button(shell, SWT.PUSH);

	    cancelButton.setText("Cancel");

	    GridData cancelData =

	        new GridData(SWT.CENTER, SWT.CENTER, true, true);

	    cancelButton.setLayoutData(cancelData);

	    shell.pack();

	    shell.open();

	    while (!shell.isDisposed()) {

	        if (!display.readAndDispatch()) display.sleep();

	    }

	    display.dispose();

	}

}
