package de.techjava.rcp.cnf.provider;

import org.eclipse.ui.navigator.CommonNavigator;

import de.techjava.rcp.cnf.data.Root;

/**
 * @author Simon Zambrovski
 * @version $Id$
 */
public class CNFNavigator extends CommonNavigator
{
    protected Object getInitialInput()
    {
        return new Root();
    }
}
