/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 24, 2009 Carlos Valcarcel Hidden Clause
 */
package customnavigator.sorter;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import customnavigator.navigator.CustomProjectSchemaFilters;
import customnavigator.navigator.CustomProjectSchemaTables;
import customnavigator.navigator.CustomProjectSchemaViews;
import customnavigator.navigator.ICustomProjectElement;

/**
 * @author carlos
 *
 */
public class SchemaCategorySorter extends ViewerSorter {

    /**
     * 
     */
    public SchemaCategorySorter() {
        // purposely empty
    }

    /**
     * @param collator
     */
    public SchemaCategorySorter(Collator collator) {
        super(collator);
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        String catName1 = ((ICustomProjectElement)e1).getText();
        String catName2 = ((ICustomProjectElement)e2).getText();
        
        int result = -1;
        if (catName1.equals(CustomProjectSchemaTables.NAME)) {
            result = -1;
        } else if (catName2.equals(CustomProjectSchemaTables.NAME)) {
            result = 1;
        } else if (catName1.equals(CustomProjectSchemaViews.NAME)) {
            result = -1;
        } else if (catName1.equals(CustomProjectSchemaFilters.NAME)) {
            result = 1;
        } // else result == -1
        
        return result;
    }

}
