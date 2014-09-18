/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 31, 2009 Carlos Valcarcel
 */
package customplugin.wizards;


/**
 * @author carlos
 */
public class CustomProjectNewStoredProcedureFile extends CustomProjectNewFile {
    private static final String WIZARD_NAME = "New Stored Procedure File"; //$NON-NLS-1$

    public CustomProjectNewStoredProcedureFile() {
        super(WIZARD_NAME);
    }

    @Override
    public void addPages() {
        super.addPages();

        _pageOne = new WizardStoredProcedureNewFileCreationPage(_selection);

        addPage(_pageOne);
    }

}
