package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;


public class TestMenuDetectEvent {
	static int Count;

	public static void main(String[] args) {

		Display display = new Display();

		final Shell shell = new Shell(display);

		shell.addListener(SWT.MenuDetect, new Listener() {

			public void handleEvent(Event event) {

				Menu menu = shell.getMenu();

				if (menu != null)
					menu.dispose();

				menu = new Menu(shell, SWT.POP_UP);

				MenuItem item = new MenuItem(menu, SWT.PUSH);

				item.setText("Menu " + Count++);

				shell.setMenu(menu);

			}

		});

		shell.pack();

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
