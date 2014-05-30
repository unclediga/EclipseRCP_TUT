 
package com.example.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;

public class TodoDetails {
//	@Inject
//	public TodoDetails() {
//		System.out.println("Created "+this.getClass().getName());
//	}
	
	@PostConstruct
	public void createControls(Composite parent) {
	  System.out.println(this.getClass().getSimpleName() 
	  + " @PostConstruct method called.");
	} 	
	
	@Focus
	public void onFocus() {
//		  System.out.println(this.getClass().getSimpleName() 
//				  + " @Focus method called.");
	}
}