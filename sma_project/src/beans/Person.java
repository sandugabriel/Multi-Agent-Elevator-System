package beans;

public class Person {
	private String ID;
	private PersonState state;
	
	private Integer fromLevel;
	private Integer toLevel;
	
	public Person() {
		super();
	}

	public Person(String iD, PersonState state, Integer fromLevel, Integer toLevel) {
		super();
		ID = iD;
		this.state = state;
		this.fromLevel = fromLevel;
		this.toLevel = toLevel;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public PersonState getState() {
		return state;
	}

	public void setState(PersonState state) {
		this.state = state;
	}

	public Integer getFromLevel() {
		return fromLevel;
	}

	public void setFromLevel(Integer fromLevel) {
		this.fromLevel = fromLevel;
	}

	public Integer getToLevel() {
		return toLevel;
	}

	public void setToLevel(Integer toLevel) {
		this.toLevel = toLevel;
	}
}
