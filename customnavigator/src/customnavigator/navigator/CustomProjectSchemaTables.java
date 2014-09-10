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
public class CustomProjectSchemaTables extends CustomProjectElement {

    public static final String NAME = "Tables"; //$NON-NLS-1$

    public CustomProjectSchemaTables(ICustomProjectElement parent) {
        super(parent, NAME, Messages.CustomProjectSchemaTables_Project_Schema_Tables);
    }

    @Override
    protected ICustomProjectElement[] initializeChildren(IProject iProject) {
        ICustomProjectElement[] children = new ICustomProjectElement [0];
        
        return children;
    }
}
