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
public class WizardSchemaNewFileCreationPage extends WizardNewFileCreationPage {

	private static final String PAGE_NAME = "Custom Plug-in Schema File Wizard"; //$NON-NLS-1$

	public WizardSchemaNewFileCreationPage(IStructuredSelection selection) {

		super(PAGE_NAME, selection);

		setTitle(NewWizardMessages.WizardSchemaNewFileCreationPage_Schema_File_Wizard);
		setDescription(NewWizardMessages.WizardSchemaNewFileCreationPage_Create_a_Schema_File);
		setFileExtension(NewWizardMessages.WizardSchemaNewFileCreationPage_Schema_File_Extension); 
	}

	@Override
	protected InputStream getInitialContents() {
		
		String templateFilePath = NewWizardMessages.WizardSchemaNewFileCreationPage_Schema_Template_Location;
        InputStream inputStream = null;
		try {
			inputStream = Activator.getDefault().getBundle().getEntry(templateFilePath).openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}
}
