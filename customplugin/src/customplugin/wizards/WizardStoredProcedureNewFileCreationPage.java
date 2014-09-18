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
public class WizardStoredProcedureNewFileCreationPage extends WizardNewFileCreationPage {

    private static final String PAGE_NAME = "Custom Plug-in Stored Procedure File Wizard"; //$NON-NLS-1$

    public WizardStoredProcedureNewFileCreationPage(IStructuredSelection selection) {
        super(PAGE_NAME, selection);
        
        setTitle(NewWizardMessages.WizardStoredProcedureNewFileCreationPage_StoredProcedure_File_Wizard);
        setDescription(NewWizardMessages.WizardStoredProcedureNewFileCreationPage_Create_a_StoredProcedure_File);
        setFileExtension(NewWizardMessages.WizardStoredProcedureNewFileCreationPage_StoredProcedure_File_Extension);
    }

    @Override
    protected InputStream getInitialContents() {
        String templateFilePath = NewWizardMessages.WizardStoredProcedureNewFileCreationPage_StoredProcedure_Template_Location;
        InputStream inputStream = null;
        try {
            inputStream = Activator.getDefault().getBundle().getEntry(templateFilePath).openStream();
        } catch (IOException e) {
            // send back null
        }

        return inputStream;
    }

}
