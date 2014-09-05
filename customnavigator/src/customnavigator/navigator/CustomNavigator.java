/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 16, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import org.eclipse.ui.navigator.CommonNavigator;

/**
 * @author carlos
 */
public class CustomNavigator extends CommonNavigator {
    @Override
    protected Object getInitialInput() {
        return new CustomProjectWorkbenchRoot();
    }
}
