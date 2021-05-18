package beans;

import java.util.List;

import jade.core.AID;

public class Elevator {

	private AID ID;
	private Float position;
	private ElevatorState state;
	private Integer direction;
	private Integer capacity;
	private List<Person> personList;

	public Elevator() {
		super();
	}

	public Elevator(AID ID) {
		this.ID = ID;
	}

	public Elevator(AID iD, Float position, ElevatorState state, Integer direction, Integer capacity,
			List<Person> personList) {
		super();
		ID = iD;
		this.position = position;
		this.state = state;
		this.direction = direction;
		this.capacity = capacity;
		this.personList = personList;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("--------").append("\n");
		builder
		.append("| ")
		.append(this.printPersonList())
		.append(" ").append(
					this.state == ElevatorState.DOORS_CLOSED ? "|" :
					this.state == ElevatorState.DOORS_OPEN ? " " :
					this.state == ElevatorState.DOORS_CLOSING ? "\\" : 
					this.state == ElevatorState.DOORS_OPENING ? "/" :
					this.state == ElevatorState.IN_TRANSIT ? "|" : "|"
				)
		.append(this.direction == 1 ? "^" : "v").append("\n");
		builder.append("--------");
		
		return builder.toString();
	}
	
	public String printPersonList() {
		StringBuilder builder = new StringBuilder();
		
		this.personList.forEach(person -> {
			builder.append(person.getID());
		});
		
		for (int i = 0; i < this.capacity - this.personList.size(); i++) {
			builder.append(" ");
		}
		
		return builder.toString();
	}

	public AID getID() {
		return ID;
	}

	public void setID(AID ID) {
		this.ID = ID;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Float getPosition() {
		return position;
	}

	public void setPosition(Float position) {
		this.position = position;
	}

	public ElevatorState getState() {
		return state;
	}

	public void setState(ElevatorState state) {
		this.state = state;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

}
