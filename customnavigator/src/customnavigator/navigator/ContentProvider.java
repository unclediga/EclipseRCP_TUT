/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 17, 2009 Carlos Valcarcel
 */
package customnavigator.navigator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import customplugin.natures.ProjectNature;

/**
 * @author carlos
 */
public class ContentProvider implements ITreeContentProvider, IResourceChangeListener {

    private static final Object[] NO_CHILDREN = {};
    private Map<String, Object> _wrapperCache = new HashMap<String, Object>();
    private Viewer _viewer;

    public ContentProvider() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        System.out.println("ContentProvider.getChildren: " + parentElement.getClass().getName()); //$NON-NLS-1$
        Object[] children = null;
        if (IWorkspaceRoot.class.isInstance(parentElement)) {
            IProject[] projects = ((IWorkspaceRoot)parentElement).getProjects(); 
            children = createCustomProjectParents(projects);
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
     * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @SuppressWarnings("null")
    @Override
    public Object getParent(Object element) {
        System.out.println("ContentProvider.getParent: " + (element == null ? "null" : element.getClass().getName())); //$NON-NLS-1$ //$NON-NLS-2$
        Object parent = null;
            
        if (IProject.class.isInstance(element)) {
            parent = ((IProject)element).getWorkspace().getRoot();
        } else if (ICustomProjectElement.class.isInstance(element)) {
            parent = ((ICustomProjectElement)element).getParent();
        } // else parent = null if IWorkspaceRoot or anything else
        
        return parent;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object element) {
        System.out.println("ContentProvider.hasChildren: " + element.getClass().getName()); //$NON-NLS-1$
        boolean hasChildren = false;

        if (IWorkspaceRoot.class.isInstance(element)) {
            hasChildren = ((IWorkspaceRoot)element).getProjects().length > 0;
        } else if (ICustomProjectElement.class.isInstance(element)) {
            hasChildren = ((ICustomProjectElement)element).hasChildren();
        }
        // else it is not one of these so return false
        
        return hasChildren;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        // This is the same as getChildren() so we will call that instead
        System.out.println("ContentProvider.getElements: " + inputElement.getClass().getName()); //$NON-NLS-1$
        return getChildren(inputElement);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        System.out.println("ContentProvider.dispose"); //$NON-NLS-1$

        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        System.out.println("ContentProvider.inputChanged: viewer: " + viewer + " old: " + (oldInput != null ? oldInput.getClass().getName() : "null") + " new: " + newInput.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        System.out.println("ContentProvider.inputChanged: viewer.getInput: " + viewer.getInput()); //$NON-NLS-1$

        _viewer = viewer;
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        System.out.println("ContentProvider.resourceChanged: Hey! Something happened: " + event); //$NON-NLS-1$
        TreeViewer viewer = (TreeViewer) _viewer;
        TreePath[] treePaths = viewer.getExpandedTreePaths();
        viewer.refresh();
        viewer.setExpandedTreePaths(treePaths); 
        System.out.println("ContentProvider.resourceChanged: completed refresh() and setExpandedXXX()"); //$NON-NLS-1$
    }

    private Object createCustomProjectParent(IProject parentElement) {

        Object result = null;
        try {
            if (parentElement.getNature(ProjectNature.NATURE_ID) != null) {
                result = new CustomProjectParent(parentElement);
            }
        } catch (CoreException e) {
            // Go to the next IProject
        }

        return result;
    }

    private Object[] createCustomProjectParents(IProject[] projects) {
        Object[] result = null;
        
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < projects.length; i++) {
            Object customProjectParent = _wrapperCache.get(projects[i].getName()); 
            if (customProjectParent == null) {
                customProjectParent = createCustomProjectParent(projects[i]);
                _wrapperCache.put(projects[i].getName(), customProjectParent);
            }

            if (customProjectParent != null) {
                list.add(customProjectParent);
            } // else ignore the project
        }
        
        result = new Object[list.size()];
        list.toArray(result);
        
        return result;
    }

}
