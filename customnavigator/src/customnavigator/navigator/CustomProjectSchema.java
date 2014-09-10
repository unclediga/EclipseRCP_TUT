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
public class CustomProjectSchema implements ICustomProjectElement {

    private ICustomProjectElement _parent;
    private ICustomProjectElement[] _children;
    private Image _image;

    public CustomProjectSchema(ICustomProjectElement parent) {
        _parent = parent;
    }

    public Image getImage() {
        if (_image == null) {
            _image = Activator.getImage("icons/project-schema.png");
        }

        return _image;
    }

    public String getText() {
        return "Schema";
    }

    @Override
    public IProject getProject() {
        return _parent.getProject();
    }
    
    @Override
    public Object getParent() {
        return _parent;
    }

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
        ICustomProjectElement[] children = new ICustomProjectElement [] {
                new CustomProjectSchemaTables(this),
                new CustomProjectSchemaViews(this),
                new CustomProjectSchemaFilters(this)
        };
        
        return children;
    }

}
