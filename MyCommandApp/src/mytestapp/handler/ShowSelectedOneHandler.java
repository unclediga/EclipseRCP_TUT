package mytestapp.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;


public class ShowSelectedOneHandler extends ShowSelected {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		super.execute(event);

		Shell shell = HandlerUtil.getActiveShell(event);
		MessageDialog.openInformation(shell, "—читаем один...", getResult());

		return null;
	}

}
