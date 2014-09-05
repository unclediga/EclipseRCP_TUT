/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 18, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Image;

import customnavigator.Activator;

/**
 * @author carlos
 *
 */
public class CustomProjectStoredProcedures implements ICustomProjectElement {

    private ICustomProjectElement _parent;
    private Image _image;
    private ICustomProjectElement[] _children;

    public CustomProjectStoredProcedures(ICustomProjectElement parent) {
        _parent = parent;
    }

    @Override
    public Image getImage() {
        if (_image == null) {
            _image = Activator.getImage("icons/project-stored-procedures.png"); //$NON-NLS-1$
        }

        return _image;
    }

    @Override
    public String getText() {
        return "Stored Procedures";
    }

    @Override
    public IProject getProject() {
        return _parent.getProject();
    }

    @Override
    public Object getParent() {
        return _parent;
    }

    /* (non-Javadoc)
     * @see customnavigator.navigator.ICustomProjectElement#getChildren()
     */
    @Override
    public ICustomProjectElement[] getChildren() {
        if (_children == null) {
            _children = initializeChildren(getProject());
        }
        // else the children are just fine
        
        return _children;
    }

    @Override
    public boolean hasChildren() {
        if (_children == null) {
            _children = initializeChildren(getProject());
        }
        // else we have already initialized them
    
        return _children.length > 0;
    }

    private ICustomProjectElement[] initializeChildren(IProject iProject) {
        ICustomProjectElement[] children = new ICustomProjectElement [0];
        
        return children;
    }
}
