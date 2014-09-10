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
public class CustomProjectSchemaViews extends CustomProjectElement {

    public static final String NAME = "Views"; //$NON-NLS-1$

    public CustomProjectSchemaViews(ICustomProjectElement parent) {
        super(parent, NAME, Messages.CustomProjectSchemaViews_Project_Schema_Views);
    }

    @Override
    protected ICustomProjectElement[] initializeChildren(IProject iProject) {
        ICustomProjectElement[] children = new ICustomProjectElement [0];
        
        return children;
    }
}
