package name.samolisov.plugins.app;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.equinox.http.jetty.JettyConfigurator;

/**
 * This class controls all aspects of the application's execution
 */
public class WebApplication implements IApplication {

	private static final String HTTP_PORT_KEY = "http.port";



    private static final int HTTP_PORT = 8080;



    private static final String SERVER_NAME = "demojetty";



    @Override

    public Object start(IApplicationContext context) throws Exception

    {

        Dictionary<String, Object> properties = new  Hashtable<String, Object>();

        properties.put(HTTP_PORT_KEY, HTTP_PORT);

        JettyConfigurator.startServer(SERVER_NAME, properties);

        System.out.println("Server " + SERVER_NAME + " has been started");



        return EXIT_OK;

    }



    @Override

    public void stop()

    {

        try

        {

            JettyConfigurator.stopServer(SERVER_NAME);

            System.out.println("Server " + SERVER_NAME + " has been stoped");

        }

        catch (Exception e)

        {

            e.printStackTrace();

        }

    }}
