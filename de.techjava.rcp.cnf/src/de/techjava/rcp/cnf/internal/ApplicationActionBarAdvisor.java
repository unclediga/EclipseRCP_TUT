package de.techjava.rcp.cnf.internal;

import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * Action bar advisor
 * @author Simon Zambrovski
 * @version $Id$
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor 
{

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) 
	{
		super(configurer);
	}
}
