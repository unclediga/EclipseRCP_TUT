/**
 * Coder beware: this code is not warranted to do anything.
 *
 * Copyright Oct 31, 2009 Carlos Valcarcel
 */
package customplugin.projects;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.junit.Assert;
import org.junit.Test;

import customplugin.natures.ProjectNature;

public class CustomProjectSupportTest {

    @SuppressWarnings("nls")
    @Test
    public void testCreateProjectWithDifferentLocationArg() throws URISyntaxException, DocumentException, CoreException {
        String workspaceFilePath = "/home/carlos/Projects/junit-workspace2";
        File workspace = createTempWorkspace(workspaceFilePath);

        String projectName = "delete-me"; //$NON-NLS-1$
        String projectPath = workspaceFilePath + "/" + projectName;
        URI location = new URI("file:/" + projectPath);

        assertProjectDotFileAndStructureAndNatureExist(projectPath, projectName, location);

        deleteTempWorkspace(workspace);
    }

    @Test
    public void testCreateProjectWithEmptyNameArg() {
        String projectName = " "; //$NON-NLS-1$
        assertCreateProjectWithBadNameArg(projectName);
    }

    @Test
    public void testCreateProjectWithNullNameArg() {
        String projectName = null;
        assertCreateProjectWithBadNameArg(projectName);
    }

    @SuppressWarnings("nls")
    @Test
    public void testCreateProjectWithGoodArgs() throws DocumentException, CoreException {
        // This is the default workspace for this plug-in
        String workspaceFilePath = "/home/carlos/Projects/junit-workspace";
        String projectName = "delete-me";
        String projectPath = workspaceFilePath + "/" + projectName;

        URI location = null;
        assertProjectDotFileAndStructureAndNatureExist(projectPath, projectName, location);
    }

    @SuppressWarnings("nls")
    private void assertProjectDotFileAndStructureAndNatureExist(String projectPath, String name, URI location) throws DocumentException,
            CoreException {
        IProject project = CustomProjectSupport.createProject(name, location);

        String projectFilePath = projectPath + "/" + ".project";
        String[] emptyNodes = { "/projectDescription/comment", "/projectDescription/projects", "/projectDescription/buildSpec" };
        String[] nonEmptyNodes = { "/projectDescription/name", "/projectDescription/natures/nature" };

        try {
            Assert.assertNotNull(project);
            assertFileExists(projectFilePath);
            assertAllElementsEmptyExcept(projectFilePath, emptyNodes, nonEmptyNodes);
            assertNatureIn(project);
            assertFolderStructureIn(projectPath);
        } finally {
            // always do this before returning
            project.delete(true, null);
        }
    }

    @SuppressWarnings("nls")
    private void assertFolderStructureIn(String projectPath) {
        String[] goodPaths = {
                "schema", //$NON-NLS-1$
                "stored-procedures" }; //$NON-NLS-1$
        for (String path : goodPaths) {
            File file = new File(projectPath + "/" + path);
            if (!file.exists()) {
                Assert.fail("Folder structure " + path + " does not exist.");
            }
        }

        // This can probably be removed as any paranoia we might have of
        // additional folders would best be served by logic that would check for
        // just the existence of the folders we care about and complain about
        // any other folders that were found.
        String[] badPaths = {
                "deployment-files",
                "clause"}; //$NON-NLS-1$
        for (String path : badPaths) {
            File file = new File(projectPath + "/" + path);
            if (file.exists()) {
                Assert.fail("Folder structure " + path + " does exist.");
            }
        }
    }

    private void assertNatureIn(IProject project) throws CoreException {
        IProjectDescription descriptions = project.getDescription();
        String[] natureIds = descriptions.getNatureIds();
        if (natureIds.length != 1) {
            Assert.fail("No natures found in project."); //$NON-NLS-1$
        }

        if (!natureIds[0].equals(ProjectNature.NATURE_ID)) {
            Assert.fail("CustomProject natures not found in project."); //$NON-NLS-1$
        }
    }

    private void assertAllElementsEmptyExcept(String projectFilePath, String[] emptyNodes, String[] nonEmptyNodes) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(projectFilePath);
        int strLength;
        for (String emptyNode : emptyNodes) {
            strLength = document.selectSingleNode(emptyNode).getText().trim().length();
            if (strLength != 0) {
                Assert.fail("Node " + emptyNode + " was non-empty!"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        for (String nonEmptyNode : nonEmptyNodes) {
            strLength = document.selectSingleNode(nonEmptyNode).getText().trim().length();
            if (strLength == 0) {
                Assert.fail("Node " + nonEmptyNode + " was empty!"); //$NON-NLS-1$//$NON-NLS-2$
            }
        }
    }

    private void assertFileExists(String projectFilePath) {
        File file = new File(projectFilePath);

        if (!file.exists()) {
            Assert.fail("File " + projectFilePath + " does not exist."); //$NON-NLS-1$//$NON-NLS-2$
        }
    }

    private void assertCreateProjectWithBadNameArg(String name) {
        URI location = null;
        try {
            CustomProjectSupport.createProject(name, location);
            Assert.fail("The call to CustomProjectSupport.createProject() did not fail!"); //$NON-NLS-1$
        } catch (AssertionFailedException e) {
            // An exception was thrown as expected; the test passed.
        }
    }

    private void deleteTempWorkspace(File workspace) {
        boolean deleted = workspace.delete();
        if (!deleted) {
            Assert.fail("Unable to delete the new workspace dir at " + workspace); //$NON-NLS-1$
        }
    }

    private File createTempWorkspace(String pathToWorkspace) {
        File workspace = new File(pathToWorkspace);
        if (!workspace.exists()) {
            boolean dirCreated = workspace.mkdir();
            if (!dirCreated) {
                Assert.fail("Unable to create the new workspace dir at " + workspace); //$NON-NLS-1$
            }
        }

        return workspace;
    }

}
