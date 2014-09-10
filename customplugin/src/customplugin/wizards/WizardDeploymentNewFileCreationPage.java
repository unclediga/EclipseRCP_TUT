/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 31, 2009 Carlos Valcarcel
 */
package customplugin.wizards;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import customplugin.Activator;

/**
 * @author carlos
 *
 */
public class WizardDeploymentNewFileCreationPage extends WizardNewFileCreationPage {

    private static final String PAGE_NAME = "Custom Plug-in Deployment File Wizard"; //$NON-NLS-1$

    public WizardDeploymentNewFileCreationPage(IStructuredSelection selection) {
        super(PAGE_NAME, selection);
        
        setTitle(NewWizardMessages.WizardDeploymentNewFileCreationPage_Deployment_File_Wizard);
        setDescription(NewWizardMessages.WizardDeploymentNewFileCreationPage_Create_a_Deployment_File);
        setFileExtension(NewWizardMessages.WizardDeploymentNewFileCreationPage_Deployment_File_Extension);
    }

    @Override
    protected InputStream getInitialContents() {
        String templateFilePath = NewWizardMessages.WizardDeploymentNewFileCreationPage_Deployment_Template_Location;
        InputStream inputStream = null;
        try {
            inputStream = Activator.getDefault().getBundle().getEntry(templateFilePath).openStream();
        } catch (IOException e) {
            // send back null
        }

        return inputStream;
    }

}
