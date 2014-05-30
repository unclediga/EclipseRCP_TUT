package com.example.addressbook.views;

import java.util.List;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

import com.example.addressbook.entities.Address;
import com.example.addressbook.services.AddressBookServices;
import com.example.addressbook.services.IAddressService;

public class AddressList extends ViewPart {

	private TableViewer addressTableViewer;
	private List<Address> allAddresses;

	public AddressList() {
		// TODO Auto-generated constructor stub
	}

	
	private void refresh(){
		IAddressService service = AddressBookServices.getAddressService();
		allAddresses = service.getAllAddresses();
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText("My LABEL");
		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.setText("Address");
		// TODO Auto-generated method stub
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		}
		);
		
		
		/////////////////////////////////////////////////////////////////////
		
		Composite tableComposite = new Composite(parent, SWT.V_SCROLL);
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);
		addressTableViewer = new TableViewer(tableComposite, SWT.BORDER);
		
		Table table = addressTableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn nameCol = new TableViewerColumn(addressTableViewer, SWT.NONE);
		nameCol.getColumn().setText("Name");
		nameCol.getColumn().setWidth(200);
		
//		tableColumnLayout.setColumnData(nameCol.getColumn(), new ColumnPixelData(50));
		tableColumnLayout.setColumnData(nameCol.getColumn(), new ColumnWeightData(20, 200, true));
		
		addressTableViewer.setContentProvider(ArrayContentProvider.getInstance());
		addressTableViewer.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				return ((Address)element).getName();
			}
			
		});
		refresh();
		addressTableViewer.setInput(allAddresses);
			
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
