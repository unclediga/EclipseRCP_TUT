package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TestPainting2 {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		Button b1 = new Button(shell, SWT.PUSH);
		b1.setText("NO Update");
		Button b2 = new Button(shell, SWT.PUSH);
		b2.setText("DO Update");

		// WRONG – only draws last percentage
		
		final Text text = new Text(shell,SWT.BORDER);
		text.setText("000 %");
		
		text.setSize(100,30);

		shell.setSize(200, 200);
		shell.open();

		/*************************************************************
		**											        		**
		** !!!!  This is WRONG doing processing inside UI Thread !!!**
		**															**
		*************************************************************/
		
		b1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				//WRONG – only draws last percentage

				text.setText("000 %");

				for (int i=1; i<=100; i++) {
					try {
						Thread.sleep(30);
					} catch (Throwable th) {}
					text.setText(i + " %");
				}
			}
		});
		
		b2.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//CORRECT – draws every percentage

				text.setText("000 %");
				text.update();
				
				for (int i=1; i<=100; i++) {
					try {
						Thread.sleep(30);
					} catch (Throwable th) {}
					text.setText(i + " %");
					text.update();
				}
			}
		});

		

		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();

	}

}
