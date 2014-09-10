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
public class CustomProjectSchema extends CustomProjectElement {

    public static final String NAME = "Schema"; //$NON-NLS-1$

    public CustomProjectSchema(ICustomProjectElement parent) {
        super(parent, NAME, Messages.CustomProjectSchema_Project_Schema);
    }

    @Override
    protected ICustomProjectElement[] initializeChildren(IProject iProject) {
        ICustomProjectElement[] children = new ICustomProjectElement [] {
                new CustomProjectSchemaTables(this),
                new CustomProjectSchemaViews(this),
                new CustomProjectSchemaFilters(this)
        };
        
        return children;
    }

}
