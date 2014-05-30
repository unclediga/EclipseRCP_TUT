package com.example.addressbook;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView("com.example.addressbook.AddressList", IPageLayout.LEFT,
				0.4f, layout.getEditorArea());
		layout.addView("com.example.addressbook.AddressEditor", IPageLayout.RIGHT, 0.0f, layout.getEditorArea());
	}
}
