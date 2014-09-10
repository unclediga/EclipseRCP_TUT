/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 31, 2009 Carlos Valcarcel
 */
package customplugin.wizards;


/**
 * @author carlos
 *
 */
public class CustomProjectNewSchemaFile extends CustomProjectNewFile {

    private static final String WIZARD_NAME = "New Schema File"; //$NON-NLS-1$
    /**
     * 
     */
    public CustomProjectNewSchemaFile() {
        super(WIZARD_NAME);
    }

    @Override
    public void addPages() {
        super.addPages();

        _pageOne = new WizardSchemaNewFileCreationPage(_selection);

        addPage(_pageOne);
    }
}
