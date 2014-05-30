package my;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;


public class MyPerspective implements IPerspectiveFactory {


	@Override

	public void createInitialLayout(IPageLayout layout) {
		
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
//		layout.addStandaloneView(MyView1.ID, true, IPageLayout.LEFT, 0.5f, editorArea ); 
		layout.addView(MyView1.ID, IPageLayout.LEFT, 0.3f, editorArea );
//		layout.addView(MyView2.ID, IPageLayout.RIGHT, 0.0f, editorArea );
//		layout.getViewLayout(MyView2.ID).setCloseable(false);
		layout.getViewLayout(MyView1.ID).setCloseable(false);
		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.5f, editorArea);
		folder.addPlaceholder(MyView2.ID+":*");
		folder.addView(MyView2.ID);
		folder.addView(MyView2.ID);
		folder.addView(MyView2.ID);
		folder.addView(MyView2.ID);
		
		
		
//		layout.setFixed(true);
		
		// ��� �� ��������. ����� ��c��� Shell
		//layout.addStandaloneView(MyView1.ID, false,IPageLayout.LEFT, 1.0f,layout.getEditorArea());
	
		//layout.addView(viewId, relationship, ratio, refId);
	}
	
}
