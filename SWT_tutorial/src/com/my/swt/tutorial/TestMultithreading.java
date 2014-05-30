package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TestMultithreading {
	public static void main(String[] args) {

		final Display display = new Display();

		Shell shell = new Shell(display);

		shell.setLayout(new FillLayout(SWT.VERTICAL));

		final Text text =

		new Text(shell, SWT.BORDER | SWT.SINGLE);

		final Label label = new Label(shell, SWT.BORDER);

		text.addListener(SWT.KeyDown, new Listener() {

			public void handleEvent(Event event) {
				System.out.println("KeyDown...run Runnable");
				System.out.println("text="+text.getText());
				display.asyncExec(new Runnable() {
//				display.syncExec(new Runnable() {

					public void run() {
						System.out.println("run() : text="+text.getText());

						label.setText(

						"\"" + text.getText() + "\"");

					}

				});
				System.out.println("exit KeyDown listener text="+text.getText());

			}

		});

		shell.setSize(shell.computeSize(400, SWT.DEFAULT));

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()){
				display.sleep();
			}else{
			}

		}

		display.dispose();

	}

}
