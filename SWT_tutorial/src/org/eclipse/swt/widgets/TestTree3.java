package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

import java.io.File;

public class TestTree3 {
	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		shell.setText("Lazy Tree");

		Tree tree = new Tree(shell, SWT.BORDER);

		/* Initialize the roots of the tree */

		File[] roots = File.listRoots();

		for (int i = 0; i < roots.length; i++) {

			TreeItem root = new TreeItem(tree, SWT.NULL);

			root.setText(roots[i].toString());

			root.setData(roots[i]);

			/* Use a dummy item to force the '+' */

			new TreeItem(root, SWT.NULL);

		}

		/* Use SWT.Expand to lazily fill the tree */

		tree.addListener(SWT.Expand, new Listener() {

			public void handleEvent(Event event) {

				/*
				 * 
				 * If the item does not contain a
				 * 
				 * dummy node, return. A dummy item
				 * 
				 * is a single child of the root that
				 * 
				 * does not have any application data.
				 */

				TreeItem root = (TreeItem) event.item;

				TreeItem[] items = root.getItems();

				if (items.length != 1)
					return;

				if (items[0].getData() != null)
					return;

				items[0].dispose();

				/* Create the item children */

				File file = (File) root.getData();

				File[] files = file.listFiles();

				if (files == null)
					return;

				for (int i = 0; i < files.length; i++) {

					TreeItem item = new TreeItem(root, SWT.NULL);

					item.setText(files[i].getName());

					item.setData(files[i]);

					/* Use a dummy item to force the '+' */

					if (files[i].isDirectory()) {

						new TreeItem(item, SWT.NULL);

					}

				}

			}

		});

		/* Set size of the tree and open the shell */

		tree.setSize(300, 300);

		shell.pack();

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}
}
