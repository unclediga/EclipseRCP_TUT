/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 17, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Image;

import customnavigator.Activator;

/**
 * @author carlos
 */
public class CustomProjectParent implements ICustomProjectElement {

    IProject                _project;
    Image                   _image;
    ICustomProjectElement[] _children;

    public CustomProjectParent(IProject iProject) {
        _project = iProject;
    }

    public String getText() {
        return _project.getName();
    }

    public Image getImage() {
        if (_image == null) {
            _image = Activator.getImage("icons/project-folder.png"); //$NON-NLS-1$
        }

        return _image;
    }

    @Override
    public IProject getProject() {
        return _project;
    }
    
    @Override
    public Object getParent() {
        return null;
    }

    @Override
    public ICustomProjectElement[] getChildren() {
        if (_children == null) {
            _children = initializeChildren(_project);
        }
        // else we have already initialized them
    
        return _children;
    }

    @Override
    public boolean hasChildren() {
        if (_children == null) {
            _children = initializeChildren(_project);
        }
        // else we have already initialized them
        return _children.length > 0;
    }

    private ICustomProjectElement[] initializeChildren(IProject project) {
        ICustomProjectElement[] children = {
                new CustomProjectSchema(this),
                new CustomProjectStoredProcedures(this)
        };

        return children;
    }

}
