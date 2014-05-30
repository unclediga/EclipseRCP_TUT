package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestShell1 {
	public static void main(String[] args) {

		final Display display = new Display();

		final Shell shell = new Shell(display);

		shell.setLayout(new FillLayout());

		Listener tipListener = new Listener() {

			Shell tip = null;
			Label label = null;
			void createToolTip() {

				tip = new Shell(shell, SWT.ON_TOP);
				tip.setLayout(new FillLayout());
				label = new Label(tip, SWT.BORDER);
				Listener listener = new Listener() {
					public void handleEvent(Event event) {
						tip.dispose();
					}
				};
				label.addListener(SWT.MouseExit, listener);
				Color foreground = display
						.getSystemColor(SWT.COLOR_INFO_FOREGROUND);
				label.setForeground(foreground);
				Color background = display.getSystemColor(SWT.COLOR_DARK_GREEN);
//				Color background = display
//						.getSystemColor(SWT.COLOR_INFO_BACKGROUND);
				label.setBackground(background);
				label.setText("ToolTip");
				tip.pack();
			}

			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.KeyDown:
				case SWT.MouseMove:
					if (tip != null)
						tip.dispose();
					tip = null;
					break;
				case SWT.MouseHover: {
					if (tip != null)
						break;
					createToolTip();
					Rectangle rect = tip.getBounds();
					rect.x = e.x;
					rect.y = e.y + 22;
					tip.setBounds(display.map(shell, null, rect));
					tip.setVisible(true);
				}
				}
			}
		};

		shell.addListener(SWT.KeyDown, tipListener);
		shell.addListener(SWT.MouseHover, tipListener);
		shell.addListener(SWT.MouseMove, tipListener);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
