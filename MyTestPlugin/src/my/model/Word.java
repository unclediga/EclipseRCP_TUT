package my.model;

import my.WordActionFilter;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IActionFilter;

public class Word implements IAdaptable{
	public Word(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Color:" + color;
	}

	private String color;

	public String getName() {
		return color;
	}

	public void setName(String name) {
		this.color = name;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if(adapter == IActionFilter.class)
			return WordActionFilter.getSingleton();
		return null;
	}

}
