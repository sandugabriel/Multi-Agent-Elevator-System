package platform;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import agents.ElevatorAgent;
import agents.FloorAgent;
import beans.Environment;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * Launches a slave container and associated agents.
 */
public class SlaveContainerLauncher
{
	
	/**
	 * A reference to the launched container.
	 */
	AgentContainer secondaryContainer;
	
	Environment env;
	
	/**
	 * Configures and launches a slave container.
	 */
	void setupPlatform()
	{
		Properties secondaryProps = new ExtendedProperties();
		secondaryProps.setProperty(Profile.CONTAINER_NAME, "AmI-Slave"); // change if multiple slaves.
		
		// TODO: replace with actual IP of the current machine
		secondaryProps.setProperty(Profile.LOCAL_HOST, "localhost");
		secondaryProps.setProperty(Profile.LOCAL_PORT, "1100");
		secondaryProps.setProperty(Profile.PLATFORM_ID, "ami-agents");
		
		// TODO: replace with actual IP of the machine running the main container.
		secondaryProps.setProperty(Profile.MAIN_HOST, "localhost");
		secondaryProps.setProperty(Profile.MAIN_PORT, "1099");
		
		ProfileImpl secondaryProfile = new ProfileImpl(secondaryProps);
		secondaryContainer = Runtime.instance().createAgentContainer(secondaryProfile);
	}
	
	/**
	 * Starts the agents assigned to this container.
	 */
	void startAgents()
	{
		
		Gson gson = new Gson();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("/home/gsandu/Documents/sma_project/tests/7floor.json"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Environment data = gson.fromJson(reader, Environment.class); // contains the whole reviews list
		
		try
		{
			List<AgentController> agents = new ArrayList<AgentController>();
			
			for (Integer i = 0; i < data.getE(); i++) {
				AgentController elevatorAgentCtrl = secondaryContainer.createNewAgent("elevator" + i,
						ElevatorAgent.class.getName(), null);
				agents.add(elevatorAgentCtrl);
				
			} 

			for (Integer i = 0; i < data.getF(); i++) {
				AgentController floorAgentCtrl = secondaryContainer.createNewAgent("floor" + i,
						FloorAgent.class.getName(), null);
				agents.add(floorAgentCtrl);
				
			} 
			
			for (AgentController agent : agents) {
				agent.start();
			}
			
		} catch(StaleProxyException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches a slave container.
	 * 
	 * @param args
	 *            - not used.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		SlaveContainerLauncher launcher = new SlaveContainerLauncher();
		
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader("/home/gsandu/Documents/sma_project/tests/7floor.json"));
		Environment data = gson.fromJson(reader, Environment.class); // contains the whole reviews list
		
		launcher.env = data;
		
		launcher.setupPlatform();
		launcher.startAgents();
	}
	
}
