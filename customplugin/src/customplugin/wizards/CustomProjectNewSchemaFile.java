package customplugin.wizards;


public class CustomProjectNewSchemaFile extends CustomProjectNewFile {

	private static final String WIZARD_NAME = "New Schema File";
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
