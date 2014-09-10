/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 17, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import org.eclipse.core.resources.IProject;


/**
 * @author carlos
 */
public class CustomProjectParent extends CustomProjectElement {

    private IProject _project;

    public CustomProjectParent(IProject iProject) {
        super(null, iProject.getName(), Messages.CustomProjectParent_Project_Folder);
        
        _project = iProject;
    }

    @Override
    public IProject getProject() {
        return _project;
    }

    @Override
    protected ICustomProjectElement[] initializeChildren(IProject project) {
        ICustomProjectElement[] children = {
                new CustomProjectSchema(this),
                new CustomProjectStoredProcedures(this)
        };

        return children;
    }

}
