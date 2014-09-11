package customnavigator.navigator;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "customnavigator.navigator.messages"; //$NON-NLS-1$
    public static String        CustomProjectParent_Project_Folder;
    public static String CustomProjectSchema_Project_Schema;
    public static String CustomProjectSchemaFilters_Project_Schema_Filters;
    public static String CustomProjectSchemaTables_Project_Schema_Tables;
    public static String CustomProjectSchemaViews_Project_Schema_Views;
    public static String CustomProjectStoredProcedures_Project_Stored_Procedures;
	public static String LabelProvider_Custom_Plugin_Navigator;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
        // always empty
    }
}
