package agents;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import agents.behaviors.AmbientServiceDiscoveryBehavior;
import beans.Elevator;
import beans.ElevatorState;
import beans.Environment;
import beans.Floor;
import beans.Person;
import beans.PersonState;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import platform.Log;

public class SimulatorAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<AID> elevatorAgents = new LinkedList<>();
	List<AID> floorAgents = new LinkedList<>();
	
	List<Elevator> elevators = new ArrayList<Elevator>();
	List<Floor> floors = new ArrayList<Floor>();
	
	Environment env;

	@Override
	public void setup() {
		Log.log(this, "Hello");

		// Read json
		Gson gson = new Gson();
		JsonReader reader = null;

		try {
			reader = new JsonReader(new FileReader("/home/gsandu/Documents/sma_project/tests/7floor.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Environment data = gson.fromJson(reader, Environment.class); // contains the whole reviews list
		env = data;

		addBehaviour(new AmbientServiceDiscoveryBehavior(this, ParallelBehaviour.WHEN_ALL));
	}

	public void addServiceAgent(String serviceType, AID agent) {
		if (serviceType.equals(ServiceType.ELEVATOR_AGENT))
			elevatorAgents.add(agent);
		if (serviceType.equals(ServiceType.FLOOR_AGENT))
			floorAgents.add(agent);

		if (floorAgents != null && floorAgents.size() == env.getF() && elevatorAgents != null
				&& elevatorAgents.size() == env.getE())
			onDiscoveryCompleted();
	}

	public void onDiscoveryCompleted() {

		this.elevatorAgents.forEach(agent -> {
			this.elevators.add(new Elevator(agent));
		});

		this.floorAgents.forEach(agent -> {
			this.floors.add(new Floor(agent));
		});

		this.generateSetup();
		
		

		// TODO Send inform message to all the agents with the setup details
		
		// TODO call toString() to print the environment 
		System.out.println(this.toString());
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		
		Collections.sort(this.floors, (o1, o2) -> o2.getLevel() - o1.getLevel());
		Collections.sort(this.elevators, (o1, o2) -> o1.getID().getName().compareTo(o2.getID().getName()));
		 
		Integer tableSize = 4 + this.elevators.size() * 8 * 2 +this.env.getC() * this.env.getE();
		
		builder.append(this.printLineCharacter(2, " "));
		
		for (Integer i = 0; i < this.elevators.size(); i++) {
			builder.append(this.printLineCharacter(5, " ")).append(i).append(this.printLineCharacter(5, " "));
		}
		
		builder.append(this.printLineCharacter(this.env.getC() * this.env.getE(), " ")).append("\n");
		
		builder.append(this.printLineCharacter(tableSize, "=")).append("\n");
		

		this.floors.forEach(floor -> {
			builder.append(" |");
			
			// line 1
			this.elevators.forEach(elevator -> {
				if (elevator.getPosition().intValue() == floor.getLevel()) {
					String line = elevator.toString().split("[\\r\\n]+")[0];
					builder.append("  ").append(line);
				} else {
					String line = this.printEmptyElevator().split("[\\r\\n]+")[0];
					builder.append(line);
				}
				builder.append(" ");
			});
			
			builder.append("  ").append(floor.toString().split("[\\r\\n]+")[0]).append("\n");
			
			// line 2
			builder.append(floor.getLevel()).append("|  ");
			
			for (Integer i = 0; i < this.elevators.size() - 1; i ++) {
				if (elevators.get(i).getPosition().intValue() == floor.getLevel()) {
					String line = elevators.get(i).toString().split("[\\r\\n]+")[1];
					builder.append(line);
					
				} else {
					String line = this.printEmptyElevator().split("[\\r\\n]+")[1];
					builder.append(line);
				}
				builder.append("  ");
			}
			
			if (elevators.get(this.elevators.size() - 1).getPosition().intValue() == floor.getLevel()) {
				String line = elevators.get(this.elevators.size() - 1).toString().split("[\\r\\n]+")[1];
				builder.append(line);
				builder.append("  ").append(floor.toString().split("[\\r\\n]+")[1]).append("\n");
				
			} else {
				String line = this.printEmptyElevator().split("[\\r\\n]+")[1];
				builder.append(line);
				builder.append(floor.toString().split("[\\r\\n]+")[1]).append("\n");
			}
		
			
			
			
			// line 3
			builder.append(" |");
			
			this.elevators.forEach(elevator -> {
				if (elevator.getPosition().intValue() == floor.getLevel()) {
					String line = elevator.toString().split("[\\r\\n]+")[2];
					builder.append("  ").append(line);
				} else {
					String line = this.printEmptyElevator().split("[\\r\\n]+")[2];
					builder.append(line);
				}
				builder.append(" ");
			});
			
			builder.append("  ").append(floor.toString().split("[\\r\\n]+")[2]).append("\n");
		});
		
		

		return builder.toString();
	}
	
	public String printEmptyElevator() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.printLineCharacter(this.env.getC() + 6, " ")).append("\n")
				.append(this.printLineCharacter(this.env.getC() + 6, " ")).append("\n")
				.append(this.printLineCharacter(this.env.getC() + 6, " "));
	
		return builder.toString();
	}
	
	public String printLineCharacter(Integer count, String ch) {
		String str = "";
		
		for (Integer i = 0; i < count; i++)
			str += ch;
		
		return str;
	}
	

	// TODO: add scenario as function parameter
	public void generateSetup() {

		this.elevators.forEach(elevator -> {
			elevator.setDirection(1);
			elevator.setPersonList(new ArrayList<Person>());
			elevator.setCapacity(this.env.getC());
			elevator.setPosition(1.0f);
			elevator.setState(ElevatorState.DOORS_OPEN);
		});

		Integer remainedPerson = this.env.getC() * this.env.getE();
		Integer personLetterCounter = 65;

		for (int i = 0; i < floors.size(); i++) {
			this.floors.get(i).setLevel(i + 1);

			List<Person> personList = new ArrayList<Person>();

			if (remainedPerson != 0) {
				Integer numberOfPersons = ThreadLocalRandom.current().nextInt(0, remainedPerson + 1);
				remainedPerson -= numberOfPersons;
				
				for (Integer j = 0; j < numberOfPersons; j++) {
					personList.add(this.generatePerson(personLetterCounter));
					personLetterCounter++;
				}				
			}
			
			this.floors.get(i).setPersonList(personList);
			this.floors.get(i).setCapacity(this.env.getC() * this.env.getE());
		}
	}

	public Person generatePerson(Integer count) {
		Integer oFromRandom = ThreadLocalRandom.current().nextInt(this.env.getScenarios().getNormal().get(0).getOfrom(),
				this.env.getScenarios().getNormal().get(0).getOto() + 1);
		Integer dToRandom = ThreadLocalRandom.current().nextInt(this.env.getScenarios().getNormal().get(0).getDfrom(),
				this.env.getScenarios().getNormal().get(0).getDto() + 1);

		Person person = new Person();

		person.setFromLevel(oFromRandom);
		person.setToLevel(dToRandom);
		person.setState(PersonState.REGISTERING);
		person.setID(Character.toString((char) count.intValue()));

		return person;
	}

	@Override
	public void takeDown() {
		// De-register from the yellow pages
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Printout a dismissal message
		Log.log(this, "terminating.");
	}

}
