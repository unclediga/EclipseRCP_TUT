package customnavigator.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Image;

import customnavigator.Activator;

public abstract class CustomProjectElement implements ICustomProjectElement {

    private Image _image;
    private String _name;
    private String _imagePath;
    private ICustomProjectElement _parent;
    private ICustomProjectElement[] _children;

    public CustomProjectElement(ICustomProjectElement parent, String name, String imagePath) {
        _parent = parent;
        _name = name;
        _imagePath = imagePath;
    }

    @Override
    public String getText() {
        return _name;
    }

    @Override
    public Image getImage() {
        if (_image == null) {
            _image = Activator.getImage(_imagePath);
        }
    
        return _image;
    }

    @Override
    public ICustomProjectElement getParent() {
        return _parent;
    }

    @Override
    public IProject getProject() {
        return getParent().getProject();
    }

    @Override
    public ICustomProjectElement[] getChildren() {
        if (_children == null) {
            _children = initializeChildren(getProject());
        }
        // else we have already initialized them

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

    protected abstract ICustomProjectElement[] initializeChildren(IProject project); 
}
