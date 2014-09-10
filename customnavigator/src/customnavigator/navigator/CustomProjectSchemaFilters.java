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
public class CustomProjectSchemaFilters implements ICustomProjectElement {

    public static final String NAME = "Filters"; //$NON-NLS-1$

    private ICustomProjectElement _parent;
    private Image _image;
    private ICustomProjectElement[] _children;

    public CustomProjectSchemaFilters(ICustomProjectElement parent) {
        _parent = parent;
    }

    /* (non-Javadoc)
     * @see customnavigator.navigator.ICustomProjectElement#getImage()
     */
    @Override
    public Image getImage() {
        if (_image == null) {
            _image = Activator.getImage("icons/project-schema-filters.png");
        }

        return _image;
    }

    public String getText() {
        return NAME;
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
