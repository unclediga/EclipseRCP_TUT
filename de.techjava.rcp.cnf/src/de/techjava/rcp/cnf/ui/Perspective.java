package de.techjava.rcp.cnf.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory
{

    public static final String PERSPECTIVE_ID = "de.techjava.rcp.cnf.perspective";

    public void createInitialLayout(IPageLayout layout)
    {
        layout.setEditorAreaVisible(false);
        layout.setFixed(true);
    }

}
