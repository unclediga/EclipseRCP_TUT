package customplugin.wizards;


public class CustomProjectNewDeploymentFile extends CustomProjectNewFile {

	private static final String WIZARD_NAME = "New Deployment File";
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
