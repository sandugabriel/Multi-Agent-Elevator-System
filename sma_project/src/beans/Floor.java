package beans;

import java.util.List;

import jade.core.AID;

public class Floor {
	
	private AID ID;
	private Integer level;
	private List<Person> personList;
	private Integer capacity;
	
	public Floor() {
		super();
	}
	
	public Floor(AID ID) {
		this.ID = ID;
	}

	public Floor(AID iD, Integer level, List<Person> personList) {
		super();
		ID = iD;
		this.level = level;
		this.personList = personList;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.printLineCharacter(this.capacity * 2 + 1, " ")).append("\n");
		builder
			.append(this.printPersonList(PersonState.REGISTERING))
			.append(" ")
			.append(this.printPersonList(PersonState.WAITING))
			.append("\n");
		
		builder.append(this.printLines()).append("^").append(this.printLines());
		
		return builder.toString();
	}
	
	public String printPersonList(PersonState personState) {
		StringBuilder builder = new StringBuilder("");
		this.personList.stream().filter(person -> person.getState() == personState).forEach(person -> {
			builder.append(person.getID());
		});
		
		Integer size = builder.toString().length();
		
		for (Integer i = 0; i < this.capacity - size; i++) {
			builder.append(" ");
		}
		
		return builder.toString();
	}
	
	public String printLineCharacter(Integer count, String ch) {
		String str = "";
		
		for (Integer i = 0; i < count; i++)
			str += ch;
		
		return str;
	}
	
	public String printLines() {
		StringBuilder builder = new StringBuilder();

		
		for (Integer i = 0; i < this.capacity; i++) {
			builder.append("-");
		}
		
		return builder.toString();
	}
	
	
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public AID getID() {
		return ID;
	}

	public void setID(AID iD) {
		ID = iD;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
}
