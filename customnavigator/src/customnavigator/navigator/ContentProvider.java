/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 17, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import customplugin.natures.ProjectNature;

/**
 * @author carlos
 */
public class ContentProvider implements ITreeContentProvider {

    private static final Object[]   NO_CHILDREN = {};
    private ICustomProjectElement[] _customProjectParents;

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.
     * Object)
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        Object[] children = null;
        if (CustomProjectWorkbenchRoot.class.isInstance(parentElement)) {
            if (_customProjectParents == null) {
                _customProjectParents = initializeParent(parentElement);
            }

            children = _customProjectParents;
        } else if (ICustomProjectElement.class.isInstance(parentElement)) {
            children = ((ICustomProjectElement) parentElement).getChildren();
        } else {
            children = NO_CHILDREN;
        }

        return children;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
     * )
     */
    @Override
    public Object getParent(Object element) {
        System.out.println("ContentProvider.getParent: " + element.getClass().getName()); //$NON-NLS-1$
        Object parent = null;
        if (ICustomProjectElement.class.isInstance(element)) {
            parent = ((ICustomProjectElement)element).getParent();
        }
        return parent;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.
     * Object)
     */
    @Override
    public boolean hasChildren(Object element) {
        boolean hasChildren = false;

        if (CustomProjectWorkbenchRoot.class.isInstance(element)) {
            hasChildren = _customProjectParents.length > 0;
        } else if (ICustomProjectElement.class.isInstance(element)) {
            hasChildren = ((ICustomProjectElement)element).hasChildren();
        }
        // else it is not one of these so return false
        
        return hasChildren;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java
     * .lang.Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        // This is the same as getChildren() so we will call that instead
        return getChildren(inputElement);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        System.out.println("ContentProvider.dispose"); //$NON-NLS-1$
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
     * .viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
/*        System.out
                .println("ContentProvider.inputChanged: old: " + oldInput.getClass().getName() + " new: " + newInput.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        // TODO Auto-generated method stub
*/
    }

    private ICustomProjectElement[] initializeParent(Object parentElement) {
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

        List<CustomProjectParent> list = new Vector<CustomProjectParent>();
        for (int i = 0; i < projects.length; i++) {
            try {
                if (projects[i].getNature(ProjectNature.NATURE_ID) != null) {
                    list.add(new CustomProjectParent(projects[i]));
                }
            } catch (CoreException e) {
                // Go to the next IProject
            }
        }

        CustomProjectParent[] result = new CustomProjectParent[list.size()];
        list.toArray(result);

        return result;
    }

}
