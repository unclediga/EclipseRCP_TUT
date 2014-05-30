package my;

import my.model.Word;

import org.eclipse.ui.IActionFilter;

public class WordActionFilter implements IActionFilter {
	
	private static String COLOR = "color";	
	private static WordActionFilter singleton;
	
	public static WordActionFilter getSingleton() {
		if(singleton == null){
			singleton = new WordActionFilter();
		}
		return singleton;
	}



	@Override
	public boolean testAttribute(Object target, String name, String value) {
		if(name.equals(COLOR)){
			Word w = (Word) target;
			if(value.equals(w.getName()))
					return true;
			
		}
		return false;
		
	}

}

