package mytestapp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		// ��� ����� ������ standalone
//		String editorArea = layout.getEditorArea();
//		layout.addView(View.ID, IPageLayout.LEFT, 1.0f, editorArea);		
	}

}
