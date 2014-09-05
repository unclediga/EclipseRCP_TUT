/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 18, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Image;

/**
 * @author carlos
 *
 */
public interface ICustomProjectElement {

    public Image getImage();

    public Object[] getChildren();

    public String getText();

    public boolean hasChildren();

    public IProject getProject();

    public Object getParent();
}
