package my;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
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

	public MyDataBindView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		
		
		Composite panel = new Composite(parent, SWT.NULL);
		
		panel.setLayout(new GridLayout(4, true));

		// POJO
		createPOJOWidgets(panel);


		bindPOJO();
			
		// Beans
		createBeansWidgets(panel);
		
		bindBean();

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
				System.out.println("Bean:"+modelBean1);
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
		text4.setText("TEXT1...");
		text4.setLayoutData(new GridData(100, SWT.DEFAULT));
		

		Button butBean2 = new Button(panel, SWT.PUSH);
		butBean2.setText("Good Bean!!!");
		butBean2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelBean1.setName("Good Bean!");
			}
		});
		
		Button butBean4 = new Button(panel, SWT.PUSH);
		butBean4.setText("SuperBean!!!");
		butBean4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelBean1.setName("SuperBean!!!");
			}
		});
	}

	private void bindBean() {
		modelBean1 = new TestBean("Hello from Bean1!!");
		modelBean2 = new TestBean("Hello from Bean2!!");
		
		DataBindingContext bc = new DataBindingContext();
		
		IObservableValue ov1 = WidgetProperties.text(SWT.Modify).observe(text3);
		IObservableValue bv1 = BeanProperties.value("name").observe(modelBean1);
		
		bc.bindValue(ov1, bv1);
		
		
		
		
		
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

		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue ov1 = WidgetProperties.text(SWT.Modify).observe(text1);
		IObservableValue mv1 = PojoProperties.value("name").observe(model1);
		IObservableValue ov2 = WidgetProperties.text(SWT.Modify).observe(text2);
		
		Binding bind2 = bindingContext.bindValue(ov2, mv1);
		bind1 = bindingContext.bindValue(ov1, mv1);
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
