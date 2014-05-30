package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TestGridLauyout2 {
	public static void main(String[] args) {

	    Display display = new Display();

	    Shell shell = new Shell(display);

	    shell.setText("Find (GridLayout)");

	    Label label = new Label(shell, SWT.NONE);

	    label.setText("Find what:");

	    Text text = new Text(shell, SWT.BORDER);

	    Button findButton = new Button(shell, SWT.PUSH);

	    findButton.setText("Find Next");

	    Group group = new Group(shell, SWT.NONE);

	    group.setLayout(new RowLayout());

	    Button upButton = new Button(group, SWT.RADIO);

	    upButton.setText("Up");

	    Button downButton = new Button(group, SWT.RADIO);

	    downButton.setText("Down");

	    downButton.setSelection(true);

	    group.setText("Direction");

	    Button cancelButton = new Button(shell, SWT.PUSH);

	    cancelButton.setText("Cancel");



	    /* Use a GridLayout to position the controls */

	    Monitor monitor = shell.getMonitor();

	    int width = monitor.getClientArea().width / 7;

	    GridLayout layout = new GridLayout(4, false);

	    layout.marginWidth = layout.marginHeight = 9;

	    shell.setLayout(layout);

	    GridData labelData =

	        new GridData(SWT.FILL, SWT.CENTER, false, false);

	    label.setLayoutData(labelData);

	    GridData textData =

	        new GridData(SWT.FILL,SWT.CENTER,true,false,2,1);

	    textData.widthHint = width;

	    text.setLayoutData(textData);

	    GridData findData =

	        new GridData(SWT.FILL, SWT.CENTER, false, false);

	    findButton.setLayoutData(findData);

	    GridData groupData =

	        new GridData(SWT.RIGHT,SWT.TOP,false,false,3,1);

	    group.setLayoutData(groupData);

	    GridData cancelData =

	        new GridData(SWT.FILL, SWT.TOP, false, false);

	    cancelButton.setLayoutData(cancelData);



	    shell.pack();

	    shell.open();

	    while (!shell.isDisposed()) {

	        if (!display.readAndDispatch()) display.sleep();

	    }

	    display.dispose();

	}

}
