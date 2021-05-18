package agents;

import java.util.LinkedList;
import java.util.List;

import agents.behaviors.AmbientServiceDiscoveryBehavior;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import platform.Log;

/**
 * The PersonalAgent.
 */
public class PersonalAgent extends Agent
{
	/**
	 * The serial UID.
	 */
	private static final long	serialVersionUID	= 2081456560111009192L;
	
	/**
	 * Known ambient agents.
	 */
	List<AID>					ambientAgents		= new LinkedList<>();
	
	/**
	 * Known preference agent
	 */
	AID							preferenceAgent;
	
	@Override
	protected void setup()
	{
		Log.log(this, "Hello from PersonalAgent");
		Log.log(this, "Adding DF subscribe behaviors");
		
		// Create a parallel behavior to handle the two DF subscriptions: one for the two ambient-agent and one for the
		// preference-agent services
		addBehaviour(new AmbientServiceDiscoveryBehavior(this, ParallelBehaviour.WHEN_ALL));
	}
	
	/**
	 * This method will be called when all the needed agents have been discovered.
	 */
	protected void onDiscoveryCompleted()
	{
		// TODO: add the RequestInitiator behavior for asking the PreferenceAgent about preferred wake up mode
	}
	
	/**
	 * Retains an agent provided a service.
	 * 
	 * @param serviceType
	 *            - the service type.
	 * @param agent
	 *            - the agent providing a service.
	 */
	public void addServiceAgent(String serviceType, AID agent)
	{
		if(serviceType.equals(ServiceType.ELEVATOR_AGENT))
			ambientAgents.add(agent);
		if(serviceType.equals(ServiceType.SIMULATOR_AGENT))
		{
			if(preferenceAgent != null)
				Log.log(this, "Warning: a second preference agent found.");
			preferenceAgent = agent;
		}
		if(preferenceAgent != null && ambientAgents.size() >= 2)
			onDiscoveryCompleted();
	}
	
	@Override
	protected void takeDown()
	{
		// Printout a dismissal message
		Log.log(this, "terminating.");
	}
}
