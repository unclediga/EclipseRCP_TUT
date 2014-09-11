/**
 * Coder beware: this code is not warranted to do anything. 
 * Some or all of this code is taken from the Eclipse code base.
 *
 * Copyright Mar 28, 2010 Carlos Valcarcel
 */
package customnavigator.popup.actionprovider;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.WizardActionGroup;

/**
 * Provides the new (artifact creation) menu options for a context menu.
 * 
 * <p>
 * The added submenu has the following structure
 * </p>
 * 
 * <ul>
 * <li>a set of context sensitive wizard shortcuts (as defined by
 * <b>org.eclipse.ui.navigator.commonWizard</b>), </li>
 * <li>another separator, </li>
 * <li>a generic "Other" new wizard shortcut action</li>
 * </ul>
 * 
 * @since 3.2
 * 
 */
public class CustomNewActionProvider extends CommonActionProvider {

    private static final String NEW_MENU_NAME = "common.new.menu";//$NON-NLS-1$

    private ActionFactory.IWorkbenchAction showDlgAction;

    private WizardActionGroup newWizardActionGroup;

    private boolean contribute = false;

    @Override
    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            IWorkbenchWindow window = ((ICommonViewerWorkbenchSite) anExtensionSite.getViewSite()).getWorkbenchWindow();
            showDlgAction = ActionFactory.NEW.create(window);

            newWizardActionGroup = new WizardActionGroup(window, PlatformUI.getWorkbench().getNewWizardRegistry(), WizardActionGroup.TYPE_NEW, anExtensionSite.getContentService());

            contribute = true;
        }
    }

    /**
     * Adds a submenu to the given menu with the name "group.new" see
     * {@link ICommonMenuConstants#GROUP_NEW}). The submenu contains the following structure:
     * 
     * <ul>
     * <li>a set of context sensitive wizard shortcuts (as defined by
     * <b>org.eclipse.ui.navigator.commonWizard</b>), </li>
     * <li>another separator, </li>
     * <li>a generic "Other" new wizard shortcut action</li>
     * </ul>
     */
    @Override
    public void fillContextMenu(IMenuManager menu) {
        IMenuManager submenu = new MenuManager(
                Messages.CustomNewActionProvider_popupNewLabel,
                NEW_MENU_NAME);
        if(!contribute) {
            return;
        }

        // fill the menu from the commonWizard contributions
        newWizardActionGroup.setContext(getContext());
        newWizardActionGroup.fillContextMenu(submenu);

        submenu.add(new Separator(ICommonMenuConstants.GROUP_ADDITIONS));

        // Add other ..
        submenu.add(new Separator());
        submenu.add(showDlgAction);

        // append the submenu after the GROUP_NEW group.
        menu.insertAfter(ICommonMenuConstants.GROUP_NEW, submenu);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.actions.ActionGroup#dispose()
     */
    @Override
    public void dispose() {
        if (showDlgAction!=null) {
            showDlgAction.dispose();
            showDlgAction = null;
        }
        super.dispose();
    }
}
