package customnavigator.popup.actionprovider;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "customnavigator.popup.actionprovider.messages"; //$NON-NLS-1$
    public static String        CustomNewActionProvider_popupNewLabel;
    public static String CustomRefreshActionProvider_invocationTargetExceptionMessage;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
