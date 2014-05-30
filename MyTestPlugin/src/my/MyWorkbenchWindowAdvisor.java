package my;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class MyWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public MyWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new MyActionBarAdvisor(configurer);
	}
	
	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(400, 300));
		configurer.setShowMenuBar(true);
		configurer.setShowFastViewBars(true);
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
//        configurer.setInitialSize(new Point(400, 300));
//        configurer.setShowCoolBar(false);
//        configurer.setShowStatusLine(false);
//        configurer.setShowPerspectiveBar(true);

		configurer.setTitle("My Test App");
	
	}

}
