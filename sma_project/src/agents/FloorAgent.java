package agents;

import beans.Floor;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import platform.Log;

public class FloorAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7735947993656293008L;

	private Floor data;

	@Override
	public void setup() {
		Log.log(this, "Hello");

		// Register the ambient-agent service in the yellow pages
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType(ServiceType.FLOOR_AGENT);
		sd.setName("floor-setup");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// TODO add behaviors
	}

	public void setEnvironment(Floor env) {
		this.data = env;
	}

	public Floor getEnvironment() {
		return this.data;
	}

	@Override
	protected void takeDown() {
		// Unregister from the yellow pages
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Printout a dismissal message
		Log.log(this, "terminating.");
	}

}
