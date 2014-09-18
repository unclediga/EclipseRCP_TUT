package customplugin.wizards;

import org.eclipse.osgi.util.NLS;

public class NewWizardMessages extends NLS {
    private static final String BUNDLE_NAME = "customplugin.wizards.messages"; //$NON-NLS-1$
    public static String CustomProjectNewWizard_Create_something_custom;
    public static String CustomProjectNewWizard_Custom_Plugin_Project;
    public static String WizardSchemaNewFileCreationPage_Create_a_Schema_File;
    public static String WizardSchemaNewFileCreationPage_Schema_File_Extension;
    public static String WizardSchemaNewFileCreationPage_Schema_File_Wizard;
    public static String WizardSchemaNewFileCreationPage_Schema_Template_Location;
    public static String WizardStoredProcedureNewFileCreationPage_Create_a_StoredProcedure_File;
	public static String WizardStoredProcedureNewFileCreationPage_StoredProcedure_File_Extension;
	public static String WizardStoredProcedureNewFileCreationPage_StoredProcedure_File_Wizard;
	public static String WizardStoredProcedureNewFileCreationPage_StoredProcedure_Template_Location;
	static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, NewWizardMessages.class);
    }

    private NewWizardMessages() {
        // Hide me!
    }
}
