package platform;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import agents.PersonalAgent;
import agents.SimulatorAgent;
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
 * Launches a main container and associated agents.
 */
public class MainContainerLauncher {

	/**
	 * The main container.
	 */
	AgentContainer mainContainer;
	
	Environment env;


	/**
	 * Configures and launches the main container.
	 */
	void setupPlatform() {
		Properties mainProps = new ExtendedProperties();
		mainProps.setProperty(Profile.GUI, "true"); // start the JADE GUI
		mainProps.setProperty(Profile.MAIN, "true"); // is main container
		mainProps.setProperty(Profile.CONTAINER_NAME, "AmI-Main"); // you can rename it
		// TODO: replace with actual IP of the current machine
		mainProps.setProperty(Profile.LOCAL_HOST, "localhost");
		mainProps.setProperty(Profile.LOCAL_PORT, "1099");
		mainProps.setProperty(Profile.PLATFORM_ID, "ami-agents");

		ProfileImpl mainProfile = new ProfileImpl(mainProps);
		mainContainer = Runtime.instance().createMainContainer(mainProfile);
	}

	/**
	 * Starts the agents assigned to the main container.
	 */
	void startAgents() {
		try {
			AgentController simulatorAgentCtrl = mainContainer.createNewAgent("simulator", SimulatorAgent.class.getName(), null);
			simulatorAgentCtrl.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launches the main container.
	 * 
	 * @param args
	 *            - not used.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MainContainerLauncher launcher = new MainContainerLauncher();
		
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader("/home/gsandu/Documents/sma_project/tests/7floor.json"));
		Environment data = gson.fromJson(reader, Environment.class); // contains the whole reviews list
		
		launcher.env = data;

		launcher.setupPlatform();
		launcher.startAgents();
	}

}
