package beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scenarios {
	private List<Scenario> normal;
	private List<Scenario> morning;
	private List<Scenario> intensive;
	
	public Scenarios() {
		super();
	}

	public Scenarios(List<Scenario> normal, List<Scenario> morning, List<Scenario> intensive) {
		super();
		this.normal = normal;
		this.morning = morning;
		this.intensive = intensive;
	}

	public List<Scenario> getNormal() {
		return normal;
	}

	public void setNormal(List<Scenario> normal) {
		this.normal = normal;
	}

	public List<Scenario> getMorning() {
		return morning;
	}

	public void setMorning(List<Scenario> morning) {
		this.morning = morning;
	}

	public List<Scenario> getIntensive() {
		return intensive;
	}

	public void setIntensive(List<Scenario> intensive) {
		this.intensive = intensive;
	}
	
	
	
}
