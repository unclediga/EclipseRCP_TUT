package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

public class TestTree1{
	
	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		Tree tree = new Tree(shell, SWT.BORDER);

		for (int i = 0; i < 4; i++) {

			TreeItem itemI = new TreeItem(tree, SWT.NULL);

			itemI.setText("Item " + i);

			for (int j = 0; j < 4; j++) {

				TreeItem itemJ = new TreeItem(itemI, SWT.NULL);

				itemJ.setText("Item " + i + " " + j);

				for (int k = 0; k < 4; k++) {

					TreeItem itemK =

					new TreeItem(itemJ, SWT.NULL);

					itemK.setText(

					"Item " + i + " " + j + " " + k);

				}

			}

		}

		tree.setSize(200, 400);

		shell.pack();

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
