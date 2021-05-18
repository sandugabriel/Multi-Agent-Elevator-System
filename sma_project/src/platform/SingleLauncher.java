package platform;

import java.io.FileNotFoundException;

/**
 * Launches both containers and associated agents.
 * 
 * @author Andrei Olaru
 */
public class SingleLauncher
{
	
	/**
	 * Creates and launches containers.
	 * 
	 * @param args
	 *            - not used.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		
		MainContainerLauncher main = new MainContainerLauncher();
		SlaveContainerLauncher slave = new SlaveContainerLauncher();
		
		main.setupPlatform();
		slave.setupPlatform();
		main.startAgents();
		slave.startAgents();
	}
	

}
