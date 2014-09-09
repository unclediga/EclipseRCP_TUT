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

	public WizardSchemaNewFileCreationPage(IStructuredSelection selection) {

		super("Custom Plug-in Schema File Wizard", selection);

		setTitle("Schema File Wizard");
		setDescription("Create a Schema File");
		setFileExtension("xml");
	}

	@Override
	protected InputStream getInitialContents() {
		
		String templateFilePath = "/templates/schema-template.xml";
        InputStream inputStream = null;
		try {
			inputStream = Activator.getDefault().getBundle().getEntry(templateFilePath).openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}
}
