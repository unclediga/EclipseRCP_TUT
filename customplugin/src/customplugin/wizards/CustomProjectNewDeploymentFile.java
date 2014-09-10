/**
 * Coder beware: this code is not warranted to do anything.
 * Copyright Oct 31, 2009 Carlos Valcarcel
 */
package customplugin.wizards;


/**
 * @author carlos
 */
public class CustomProjectNewDeploymentFile extends CustomProjectNewFile {
    private static final String WIZARD_NAME = "New Deployment File"; //$NON-NLS-1$

    public CustomProjectNewDeploymentFile() {
        super(WIZARD_NAME);
    }

    @Override
    public void addPages() {
        super.addPages();

        _pageOne = new WizardDeploymentNewFileCreationPage(_selection);

        addPage(_pageOne);
    }

}
