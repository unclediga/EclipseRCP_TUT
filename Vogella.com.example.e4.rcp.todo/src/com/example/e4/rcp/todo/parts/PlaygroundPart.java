 
package com.example.e4.rcp.todo.parts;
import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PlaygroundPart {
  private Text text;
  private Label browser;

  @PostConstruct
  public void createControls(Composite parent) {
    parent.setLayout(new GridLayout(2, false));

    text = new Text(parent, SWT.BORDER);
    text.setMessage("Enter City");
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    Button button = new Button(parent, SWT.PUSH);
    button.setText("Search");

    browser = new Label(parent, SWT.NONE);
    browser.setText("Á Ð Î Â Ó Ç Å Ð");
    browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

  }

  @Focus
  public void onFocus() {
    text.setFocus();
//	  System.out.println(this.getClass().getSimpleName() 
//			  + " @Focus method called.");
    
  }
} 