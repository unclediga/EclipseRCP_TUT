package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

public class TestTree2 {
	static void checkPath(

	TreeItem item,

	boolean checked,

	boolean grayed) {

		if (item == null)
			return;

		if (grayed) {

			checked = true;

		} else {

			int index = 0;

			TreeItem[] items = item.getItems();

			while (index < items.length) {

				TreeItem child = items[index];

				if (child.getGrayed()

				|| checked != child.getChecked()) {

					checked = grayed = true;

					break;

				}

				index++;

			}

		}

		item.setChecked(checked);

		item.setGrayed(grayed);

		checkPath(item.getParentItem(), checked, grayed);

	}

	static void checkItems(TreeItem item, boolean checked) {

		item.setGrayed(false);

		item.setChecked(checked);

		TreeItem[] items = item.getItems();

		for (int i = 0; i < items.length; i++) {

			checkItems(items[i], checked);

		}

	}

	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		Tree tree = new Tree(shell, SWT.BORDER | SWT.CHECK);

		tree.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {

				if (event.detail == SWT.CHECK) {

					TreeItem item = (TreeItem) event.item;

					boolean checked = item.getChecked();

					checkItems(item, checked);

					checkPath(

					item.getParentItem(),

					checked,

					false);

				}

			}

		});

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

		tree.setSize(200, 200);

		shell.pack();

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}
