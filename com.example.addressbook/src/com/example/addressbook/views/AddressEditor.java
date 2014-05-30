package com.example.addressbook.views;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.example.addressbook.services.AddressBookServices;

public class AddressEditor extends ViewPart {

	private Text txtName;

	public AddressEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));

		// Name
		createLabel(parent, AddressBookMessages.Name);
		txtName = new Text(parent, SWT.BORDER);

		// Gravatar
		Label lblGravatar = new Label(parent, SWT.NONE);

		// Steet
		createLabel(parent, AddressBookMessages.Street);
		Text txtStreet = new Text(parent, SWT.BORDER);

		// Zip, City
		createLabel(parent, AddressBookMessages.Zip + AddressBookMessages.Field_Separator + AddressBookMessages.City);
		Text txtZip = new Text(parent, SWT.BORDER);
		Text txtCity = new Text(parent, SWT.BORDER);

		// Country
		createLabel(parent, AddressBookMessages.Country);
		ComboViewer cvCountry = new ComboViewer(parent, SWT.READ_ONLY);
		Combo cbCountry = cvCountry.getCombo();
		cvCountry.setContentProvider(ArrayContentProvider.getInstance());
		cvCountry.setLabelProvider(new CountryLabelProvider());
		cvCountry.setInput(AddressBookServices.getAddressService().getAllCountries());

		// E-Mail
		createLabel(parent, AddressBookMessages.Email);
		Text txtEmail = new Text(parent, SWT.BORDER);
		// Layout
		GridLayoutFactory.fillDefaults().margins(10, 10).spacing(6, 3).numColumns(4).applyTo(parent);
		GridDataFactory field = GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).span(2, 1);
		field.applyTo(txtName);
		GridDataFactory.fillDefaults().span(1, 5).hint(AddressBookEditing.GRAVATAR_SIZE,
				AddressBookEditing.GRAVATAR_SIZE).align(SWT.LEFT, SWT.TOP).indent(20, 0).applyTo(lblGravatar);
		field.applyTo(txtStreet);
		GridDataFactory.fillDefaults().hint(50, SWT.DEFAULT).applyTo(txtZip);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).indent(5, 0).grab(true, false).applyTo(txtCity);
		field.applyTo(cbCountry);
		field.applyTo(txtEmail);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text + AddressBookMessages.Field_Mark);
		//label.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		return label;
	}

}
