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
public class CustomProjectStoredProcedures extends CustomProjectElement {

    public static final String NAME = "Stored Procedures"; //$NON-NLS-1$

    public CustomProjectStoredProcedures(ICustomProjectElement parent) {
        super(parent, NAME, Messages.CustomProjectStoredProcedures_Project_Stored_Procedures);
    }

    @Override
    protected ICustomProjectElement[] initializeChildren(IProject iProject) {
        ICustomProjectElement[] children = new ICustomProjectElement [0];
        
        return children;
    }
}
