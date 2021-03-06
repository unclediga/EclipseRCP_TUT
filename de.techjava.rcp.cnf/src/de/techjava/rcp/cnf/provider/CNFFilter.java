package de.techjava.rcp.cnf.provider;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.techjava.rcp.cnf.data.Parent;

/**
 * @author Simon Zambrovski
 * @version $Id$
 */
public class CNFFilter extends ViewerFilter
{

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
        if (element instanceof Parent) 
        {
            Parent parent = (Parent) element;
            return (parent.getName().equals("Parent 11"));
        } else {
            return true;
        }
    }

}
