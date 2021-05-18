package beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Environment {

	private Integer F;
	private Integer E;
	private Integer C;

	private Dynamic dynamic;
	private Scenarios scenarios;
	
	
	public Environment() {
		super();
	}
	public Environment(Integer f, Integer e, Integer c, Dynamic dynamic, Scenarios scenarios) {
		super();
		F = f;
		E = e;
		C = c;
		this.dynamic = dynamic;
		this.scenarios = scenarios;
	}
	public Integer getF() {
		return F;
	}
	public void setF(Integer f) {
		F = f;
	}
	public Integer getE() {
		return E;
	}
	public void setE(Integer e) {
		E = e;
	}
	public Integer getC() {
		return C;
	}
	public void setC(Integer c) {
		C = c;
	}
	public Dynamic getDynamic() {
		return dynamic;
	}
	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}
	public Scenarios getScenarios() {
		return scenarios;
	}
	public void setScenarios(Scenarios scenarios) {
		this.scenarios = scenarios;
	}


}
