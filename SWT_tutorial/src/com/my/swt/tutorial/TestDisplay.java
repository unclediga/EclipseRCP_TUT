package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class TestDisplay {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);

		
		//////////////////////////////////////////////////////
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				int style = SWT.OK | SWT.CANCEL | SWT.APPLICATION_MODAL;
				MessageBox box = new MessageBox(shell, style);
				box.setMessage("Exit the application?");
				event.doit = box.open() == SWT.OK;
			}
		};

		display.addListener(SWT.Close, listener);
		display.addListener(SWT.Dispose, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Saving global state ... (shell.isDisposed()="+shell.isDisposed()+")");
			}
		});
		shell.addListener(SWT.Close, listener);
		shell.addListener(SWT.Dispose, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Saving window state ... (shell.isDisposed()="+shell.isDisposed()+")");
			}
		});

		
		//////////////////////////////////////////////////////////
		display.addFilter(SWT.KeyDown, new Listener() {

		    public void handleEvent(Event event) {
		        event.type = SWT.None;
		        event.doit = false;
		    }

		});

		//////////////////////////////////////////////////////////
		display.disposeExec(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("disposeExec: Diplay isDisposed()="+display.isDisposed());
				System.out.println("disposeExec: Shell  isDisposed()="+shell.isDisposed());
			}
			
		});

		
		shell.setSize(200, 200);
		shell.open();


		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
