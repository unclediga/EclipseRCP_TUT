package my;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import my.model.TestPerson;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.internal.databinding.swt.SWTVetoableValueDecorator;
import org.eclipse.jface.internal.databinding.viewers.ObservableCollectionContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.part.ViewPart;


public class MyDataBindView extends ViewPart {

	public static final String ID = "my.views.view3";
	private Label label1;
	private Text text1;
	private Label label2;
	private Text text2;
	
	private TestPOJO model1;
	private Text text3;
	private Label label3;
	private TestBean modelBean1;
	private Binding bind1;
	private TestBean modelBean2;
	private Label label4;
	private Text text4;
	private WritableValue wv;
	private Text text5;
	private TestBean modelDecor1;
	private DataBindingContext bc;
	private Label statusLabel;
	private TableViewer tbl1;
	private TableViewer tbl2;
	private List dataList;

	public MyDataBindView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		
		bc = new DataBindingContext();
		Composite panel = new Composite(parent, SWT.NULL);
		
		panel.setLayout(new GridLayout(1, false));
		
		
		// POJO
		Group grp1 = new Group(panel, SWT.SHADOW_ETCHED_IN);
		grp1.setText("POJO");
		grp1.setLayout(new GridLayout(4, true));
		createPOJOWidgets(grp1);
		bindPOJO();
		
		
			
		// Beans
		Group grp2 = new Group(panel, SWT.SHADOW_ETCHED_IN);
		grp2.setText("Beans");
		grp2.setLayout(new GridLayout(4, true));
		createBeansWidgets(grp2);
		bindBean();
		
		//Decorator Validator Status messages
		Group grp3 = new Group(panel, SWT.SHADOW_ETCHED_IN);
		grp3.setText("Decorator Validator");
		grp3.setLayout(new GridLayout(4, true));
		createDecoratorWidgets(grp3);
		bindDecorator();
		
		//Change Listener
		bindListener();
		
		
		//Table Viewer
		Group grp4 = new Group(panel, SWT.SHADOW_ETCHED_IN);
		grp4.setText("Table Viewer");
		grp4.setLayout(new GridLayout(4, false));
		grp4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,4,1));
		
		createTableViewer(grp4);
		bindTableViewer();
		
		
		
	}

	private void createTableViewer(Composite parent) {
		
		//////////// TABLE 1 ////////////////////////////////////////
		
		tbl1 = new TableViewer(parent,SWT.BORDER|SWT.FULL_SELECTION);
		tbl1.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,4,1));
		tbl1.getTable().setHeaderVisible(true);
		TableViewerColumn col1 = new TableViewerColumn(tbl1, SWT.NONE);
		col1.getColumn().setText("ID");
		col1.getColumn().setWidth(50);
		TableViewerColumn col2 = new TableViewerColumn(tbl1, SWT.NONE);
		col2.getColumn().setText("fname");
		col2.getColumn().setWidth(100);
		TableViewerColumn col3 = new TableViewerColumn(tbl1, SWT.NONE);
		col3.getColumn().setText("lname");
		col3.getColumn().setWidth(100);
		
		
		
		
		// изменения в списке
		
		Button btnTV1 = new Button(parent, SWT.PUSH);
		btnTV1.setText("Add");
		btnTV1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// получить самому
				WritableList lst = (WritableList) tbl1.getInput();
				lst.add(new TestPerson(111, "Assa", "GoodBoy"));
			}
			
		});

		Button btnTV2 = new Button(parent, SWT.PUSH);
		btnTV2.setText("Del");
		btnTV2.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Использовать исходный объект бесполезно.
				// Изменения самого списка dataList отслеживаются только,
				// если их делать через обёртку WritableList
				// Изменения в самих объектах списка отслеживаются 
				// как обычно через BeanProperties
				
				// Не пройдёт
				//dataList.remove(0);
				
				// Только так
				WritableList lst = (WritableList) tbl1.getInput();
				lst.remove(0);
			}
			
		});

		// изменения в объекте
		Button btnTV3 = new Button(parent, SWT.PUSH);
		btnTV3.setText("Vasya");
		btnTV3.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection)tbl1.getSelection();
				if(!sel.isEmpty()){
					TestPerson p = (TestPerson) sel.getFirstElement();
					System.out.println("Set 'Vasya' for "+p);
					p.setFirstName("Vasya");
				}
			}
			
		});
		
		Button btnTV4 = new Button(parent, SWT.PUSH);
		btnTV4.setText("print");
		btnTV4.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				printList(dataList);
				printList((List) tbl1.getInput());
				
			}

			private void printList(List dataList) {
				System.out.println("List datas for class "+dataList.getClass().getName());
				for (Object e : dataList) {
					System.out.println(e);
				}			}
			
		});
		
		
		//////////// TABLE 2 ////////////////////////////////////////
		
		tbl2 = new TableViewer(parent,SWT.BORDER|SWT.FULL_SELECTION);
		tbl2.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,4,1));
		tbl2.getTable().setHeaderVisible(true);
		col1 = new TableViewerColumn(tbl2, SWT.NONE);
		col1.getColumn().setText("ID");
		col1.getColumn().setWidth(50);
		col2 = new TableViewerColumn(tbl2, SWT.NONE);
		col2.getColumn().setText("fname");
		col2.getColumn().setWidth(100);
		col3 = new TableViewerColumn(tbl2, SWT.NONE);
		col3.getColumn().setText("lname");
		col3.getColumn().setWidth(100);
		col1.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				return ""+((TestPerson)element).getEmpId();
			}
			
		});
		col2.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				return ((TestPerson)element).getFirstName().toString();

			}
			
		});
		col3.setLabelProvider(new ColumnLabelProvider(){
			
			@Override
			public String getText(Object element) {
				return ((TestPerson)element).getLastName().toString();
				
			}
			
		});
		
		

		
		
		Button btnTV21 = new Button(parent, SWT.PUSH);
		btnTV21.setText("Vasya");
		btnTV21.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection)tbl2.getSelection();
				if(!sel.isEmpty()){
					TestPerson p = (TestPerson) sel.getFirstElement();
					System.out.println("Set 'Vasya' for "+p);
					p.setFirstName("Vasya");
				}
			}
			
		});
		
		Button btnTV22 = new Button(parent, SWT.PUSH);
		btnTV22.setText("refresh");
		btnTV22.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tbl2.refresh();
			}
			
		});
		
		Button btnTV23 = new Button(parent, SWT.PUSH);
		btnTV23.setText("btn3");
		Button btnTV24 = new Button(parent, SWT.PUSH);
		btnTV24.setText("btn4");
		
		
		
		
	}
	
	private void bindTableViewer() {
		
		dataList = getTblData();

		WritableList input1 = new WritableList(dataList, TestPerson.class);
		
	    ViewerSupport.bind(tbl1,
	            input1,
	            BeanProperties.values(new String[] { "empId","firstName", "lastName"}));
//	    ViewerSupport.bind(tbl1,
//	    		input,
//	    		PojoProperties.values(new String[] { "empId","firstName", "lastName"}));

	    tbl1.setInput(input1);

	    
	    
	    WritableList input2 = new WritableList(dataList, TestPerson.class);
	    // Следит за списком. Обновляет данные сама.
	    // если поставили бы ArrayContentProvider(), то приходилось бы
	    // делать tbl2.refresh() при каждом изменении input2
	    
	    //!!!! Чудом работает. Вообще эта штука для ListViewer (см. Vogella JFace Data Binding - Tutorial)
	    ObservableListContentProvider contentProvider = new ObservableListContentProvider();
		tbl2.setContentProvider(contentProvider);
		for (Object e : contentProvider.getKnownElements()) {
			System.out.println(e);
			
		}
		System.out.println(contentProvider.getKnownElements());
		
		//ObservableMapLabelProvider
		
		tbl2.setInput(input2);
		
	    
	}

	private List getTblData() {
		
		ArrayList lst = new ArrayList(10);
		lst.add(new TestPerson(1, "Ivan", "Ivanov"));
		lst.add(new TestPerson(2, "Petr", "Petrov"));
		lst.add(new TestPerson(3, "Sidor", "Sidorov"));
		lst.add(new TestPerson(4, "Grigor", "Grigorov"));
		return lst;
	}

	private void bindListener() {
		
		IObservableList lst = bc.getBindings();
		for (Object e : lst) {
			((Binding) e).getTarget().addChangeListener(new IChangeListener() {

				@Override
				public void handleChange(ChangeEvent event) {
					Object object = event.getSource();
					if(object instanceof SWTVetoableValueDecorator){
						Widget w  = ((SWTVetoableValueDecorator) object).getWidget();
						Object v = ((SWTVetoableValueDecorator) object).getValue();
						 
						System.out.println("I am listener: widget "+w.getClass());
						System.out.println("I am listener: val "+v);
					}else{
						System.out.println("I am listener: change "+event);
					}

				}
			});
		}
		
	}
	
	

	private void createBeansWidgets(Composite panel) {

		label3 = new Label(panel, SWT.NULL);
		label3.setText("Bean 1:");
		label3.setLayoutData(new GridData(50, SWT.DEFAULT));

		text3 = new Text(panel, SWT.BORDER);
		text3.setText("TEXT1...");
		text3.setLayoutData(new GridData(100, SWT.DEFAULT));

		Button butBean1 = new Button(panel, SWT.PUSH);
		butBean1.setText("Print Bean Model");
		butBean1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Bean1:"+modelBean1);
				System.out.println("Bean2:"+modelBean2);
			}
		});
		
		Button butBean3 = new Button(panel, SWT.PUSH);
		butBean3.setText("SuperBean!!!");
		butBean3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelBean1.setName("SuperBean!!!");
			}
		});

		label4 = new Label(panel, SWT.NULL);
		label4.setText("Bean 2:");
		label4.setLayoutData(new GridData(50, SWT.DEFAULT));
		
		text4 = new Text(panel, SWT.BORDER);
		text4.setText("TEXT2...");
		text4.setLayoutData(new GridData(100, SWT.DEFAULT));
		

		Button butBean2 = new Button(panel, SWT.PUSH);
		butBean2.setText("Good Bean!!!");
		butBean2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelBean1.setName("Good Bean!");
			}
		});
		
		final Button butBean4 = new Button(panel, SWT.PUSH);
		butBean4.setText("set to model2");
		butBean4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(wv.getValue() == modelBean1){
					wv.setValue(modelBean2);
					butBean4.setText("set to model1");
				}else{
					wv.setValue(modelBean1);
					butBean4.setText("set to model2");
				}
			}
		});
	}
	private void createDecoratorWidgets(Composite panel) {
		
		Label label5 = new Label(panel, SWT.NULL);
		label5.setText("Telephone:");
		label5.setLayoutData(new GridData(50, SWT.DEFAULT));
		
		text5 = new Text(panel, SWT.BORDER);
		text5.setText("737-73-73");
		text5.setLayoutData(new GridData(100, SWT.DEFAULT));
		
		Button butDecor1 = new Button(panel, SWT.PUSH);
		butDecor1.setText("Print Bean Model");
		butDecor1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Decor1:"+modelDecor1);
			}
		});

		Button butDecor2 = new Button(panel, SWT.PUSH);
		butDecor2.setText("737-73-73");
		butDecor2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelDecor1.setName("7377373");
			}
		});
		
		//label status
		statusLabel = new Label(panel, SWT.BORDER);
		statusLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,4 , 1));
		statusLabel.setText("Status label");
		
	}

	private void bindDecorator() {

		modelDecor1 = new TestBean("Hello from Decor1!!");

		IObservableValue ov1 = WidgetProperties.text(SWT.Modify).observe(text5);
		IObservableValue dv1 = BeanProperties.value("name").observe(modelDecor1);
		
		UpdateValueStrategy us_targ_to_model = new UpdateValueStrategy(){

			@Override
			public Object convert(Object value) {
				System.out.println("T:convert1:"+value);
				StringBuilder builder = new StringBuilder();
				String str = (String) value;
				for(int i=0;i<str.length();i++){
					if(Character.isDigit(str.charAt(i)))
							builder.append(str.charAt(i));
				}
				
			    
				System.out.println("T:convert2:"+builder.toString());
				return builder.toString();
			}


			@Override
			public IStatus validateAfterConvert(Object value) {
			    System.out.println("T:validateAfterConvert");
				return super.validateAfterConvert(value);
			}


			@Override
			public IStatus validateBeforeSet(Object value) {
			    System.out.println("T:validateBeforeSet");
				return super.validateBeforeSet(value);
			}


			@Override
			public IStatus validateAfterGet(Object value) {
								
				System.out.println("validateAfterGet:"+value);
				String str = (String) value;
				
				for(int i=0;i<str.length();i++){
					if(! (Character.isDigit(str.charAt(i))||str.charAt(i) =='-')){
						System.out.println("char ("+i+")="+str.charAt(i)+":Not a Num!!!");
					    return ValidationStatus.error("char ("+i+")="+str.charAt(i)+":Is Not Number!!!");
					}   
				}
				return ValidationStatus.ok();
			}

			
		};
		
		
		UpdateValueStrategy us_model_to_targ = new UpdateValueStrategy(){
			
			@Override
			public Object convert(Object value) {
				
				System.out.println("M:convert1:"+value);
				StringBuilder builder = new StringBuilder();
				String str = (String) value;
				for(int i=0;i<str.length();i++){
					switch (i) {
					case 3:
					case 5:
						builder.append('-');
					default:
						builder.append(str.charAt(i));
					}
				}
				
			    
				System.out.println("M:convert2:"+builder.toString());
				return builder.toString();
			}
			
			@Override
			public IStatus validateAfterConvert(Object value) {
				System.out.println("M:convert2:validateAfterConvert");
				return super.validateAfterConvert(value);
			}
			
			@Override
			public IStatus validateAfterGet(Object value) {

				System.out.println("M:validateAfterGet:"+value);
				String str = (String) value;
				
				for(int i=0;i<str.length();i++){
					if(! (Character.isDigit(str.charAt(i)))){
						System.out.println("char ("+i+")="+str.charAt(i)+":Not!!!");
					    return Status.CANCEL_STATUS;
					}   
				}
				return Status.OK_STATUS;
			}
			
			@Override
			public IStatus validateBeforeSet(Object value) {
				System.out.println("M:convert2:validateBeforeSet");
				return super.validateBeforeSet(value);
			}
			
		};
		
		
		
		
		Binding bind = bc.bindValue(ov1, dv1,us_targ_to_model,us_model_to_targ);

		// DECORATOR
		ControlDecorationSupport.create(bind, SWT.TOP|SWT.LEFT);
		
		//status label for all status messages in binding context
		ISWTObservableValue errlab = WidgetProperties.text().observe(statusLabel);
		bc.bindValue(errlab, new AggregateValidationStatus(bc,AggregateValidationStatus.MAX_SEVERITY ));
		
				 
		
	}
	
	private void bindBean() {
		
		wv  = new WritableValue();
		
		modelBean1 = new TestBean("Hello from Bean1!!");
		modelBean2 = new TestBean("Hello from Bean2!!");
		
		
		IObservableValue ov1 = WidgetProperties.text(SWT.Modify).observe(text3);
		IObservableValue bv1 = BeanProperties.value("name").observe(modelBean1);
		bc.bindValue(ov1, bv1);
		
		IObservableValue ov2 = WidgetProperties.text(SWT.Modify).observe(text4);
		IObservableValue bv2 = BeanProperties.value("name").observeDetail(wv);
		bc.bindValue(ov2, bv2);
		
		wv.setValue(modelBean2);
		
	}

	private void createPOJOWidgets(Composite panel) {
		
		label1 = new Label(panel, SWT.NULL);
		label1.setText("Target 1:");
		label1.setLayoutData(new GridData(50, SWT.DEFAULT));

		text1 = new Text(panel, SWT.BORDER);
		text1.setText("TEXT1...");
		text1.setLayoutData(new GridData(100, SWT.DEFAULT));
		
		Button butPOJO1 = new Button(panel, SWT.PUSH);
		butPOJO1.setText("Print POJO");
		butPOJO1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("POJO: "+model1);
			}
		});
		
		Button butPOJO2 = new Button(panel, SWT.PUSH);
		butPOJO2.setText("POJO=GOOD!");
		butPOJO2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model1.setName("GOOD!");
			}
		});
		
		
		

		label2 = new Label(panel, SWT.NULL);
		label2.setText("Target 2:");
		label2.setLayoutData(new GridData(50, SWT.DEFAULT));
		
		text2 = new Text(panel, SWT.BORDER);
		text2.setText("TEXT2...");
		text2.setLayoutData(new GridData(100, SWT.DEFAULT));

		Button butPOJO3 = new Button(panel, SWT.PUSH);
		butPOJO3.setText("update bind");
		butPOJO3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				bind1.updateModelToTarget();
			}
		});

		Button butPOJO4 = new Button(panel, SWT.PUSH);
		butPOJO4.setText("update bind");
		butPOJO4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				bind1.updateModelToTarget();
			}
		});
		
		
		
	}

	private void bindPOJO() {
		
		
		model1 = new TestPOJO("Hello from POJO");

		bc = new DataBindingContext();
		IObservableValue ov1 = WidgetProperties.text(SWT.Modify).observe(text1);
		IObservableValue mv1 = PojoProperties.value("name").observe(model1);
		IObservableValue ov2 = WidgetProperties.text(SWT.Modify).observe(text2);
		
		bc.bindValue(ov2, mv1);
		bind1 = bc.bindValue(ov1, mv1);
	}

	@Override
	public void setFocus() {
		text1.setFocus();

	}

	class TestPOJO {
		private String name;

		public TestPOJO(String string) {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String toString() {
			return "POJO name: " + this.name;
		}
	}

	class TestBean {
		private String name;
		private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
				this);

		public void addPropertyChangeListener(String propName,
				PropertyChangeListener listener) {
			propertyChangeSupport.addPropertyChangeListener(propName, listener);
		}

		public void removePropertyChangeListener(String propName,
				PropertyChangeListener listener) {
			propertyChangeSupport.removePropertyChangeListener(propName,
					listener);
		}

		public TestBean() {
		}

		public TestBean(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			propertyChangeSupport.firePropertyChange("name", this.name,
					this.name = name);
		}

		public String toString() {
			return "Bean name: " + this.name;
		}
	}

}
