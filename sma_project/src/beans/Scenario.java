package beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {
	
	private Integer Tfrom;
	private Integer Tto;
	private Integer Ofrom;
	private Integer Oto;
	private Integer Dfrom;
	private Integer Dto;
	private Integer Period;
	
	
	
	public Scenario() {
		super();
	}

	public Scenario(Integer tfrom, Integer tto, Integer ofrom, Integer oto, Integer dfrom, Integer dto,
			Integer period) {
		super();
		Tfrom = tfrom;
		Tto = tto;
		Ofrom = ofrom;
		Oto = oto;
		Dfrom = dfrom;
		Dto = dto;
		Period = period;
	}

	public Integer getTfrom() {
		return Tfrom;
	}

	public void setTfrom(Integer tfrom) {
		Tfrom = tfrom;
	}

	public Integer getTto() {
		return Tto;
	}

	public void setTto(Integer tto) {
		Tto = tto;
	}

	public Integer getOfrom() {
		return Ofrom;
	}

	public void setOfrom(Integer ofrom) {
		Ofrom = ofrom;
	}

	public Integer getOto() {
		return Oto;
	}

	public void setOto(Integer oto) {
		Oto = oto;
	}

	public Integer getDfrom() {
		return Dfrom;
	}

	public void setDfrom(Integer dfrom) {
		Dfrom = dfrom;
	}

	public Integer getDto() {
		return Dto;
	}

	public void setDto(Integer dto) {
		Dto = dto;
	}

	public Integer getPeriod() {
		return Period;
	}

	public void setPeriod(Integer period) {
		Period = period;
	}
	
	
	
}
