/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 18, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import org.eclipse.core.resources.IProject;

/**
 * @author carlos
 *
 */
public class CustomProjectSchemaFilters extends CustomProjectElement {

    public static final String NAME = "Filters"; //$NON-NLS-1$

    public CustomProjectSchemaFilters(ICustomProjectElement parent) {
        super(parent, NAME, Messages.CustomProjectSchemaFilters_Project_Schema_Filters);
    }

    /* (non-Javadoc)
     * @see customnavigator.navigator.ICustomProjectElement#getChildren()
     */
    @Override
    protected ICustomProjectElement[] initializeChildren(IProject iProject) {
        ICustomProjectElement[] children = new ICustomProjectElement [0];
        
        return children;
    }
}
