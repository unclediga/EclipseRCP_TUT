package com.my.swt.tutorial;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestShell2 {
	static final int POINTS = 11;

	public static void main(String[] args) {

		final Point center = new Point(0, 0);

		final int[] radial = new int[POINTS * 2];

		final Display display = new Display();

		final Color black =

		display.getSystemColor(SWT.COLOR_BLACK);

		final Shell shell = new Shell(display, SWT.NO_TRIM);

		shell.setBackground(black);

		shell.setSize(200, 200);

		Rectangle bounds = shell.getClientArea();

		center.x = bounds.x + bounds.width / 2;

		center.y = bounds.y + bounds.height / 2;

		int pos = 0;

		for (int i = 0; i < POINTS; ++i) {

			double r = Math.PI * 2 * pos / POINTS;

			radial[i * 2] = (int) ((1 + Math.cos(r)) * center.x);

			radial[i * 2 + 1] = (int) ((1 + Math.sin(r)) * center.y);

			pos = (pos + POINTS / 2) % POINTS;

		}

		Listener listener = new Listener() {

			int offsetX = 0, offsetY = 0;

			public void handleEvent(Event e) {

				switch (e.type) {

				case SWT.MouseDown:

					if (e.button == 1) {

						offsetX = e.x;

						offsetY = e.y;

					}

					break;

				case SWT.MouseMove:

					if ((e.stateMask & SWT.BUTTON1) != 0) {

						Point pt = shell.toDisplay(e.x, e.y);

						pt.x -= offsetX;

						pt.y -= offsetY;

						shell.setLocation(pt);

					}

					break;

				case SWT.KeyDown:

					shell.dispose();

					break;

				}

			}

		};

		shell.addListener(SWT.MouseDown, listener);

		shell.addListener(SWT.MouseMove, listener);

		shell.addListener(SWT.KeyDown, listener);

		Region region = new Region(display);

		region.add(radial);

		shell.setRegion(region);

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		region.dispose();

		display.dispose();

	}

}
