package beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dynamic {
	
	private Integer Tdoors;
	private Integer Tclose;
	private Integer Ttransit;
	private Integer Tslow;
	private Integer Taccel;
	private Integer Tfast;
	
	
	
	public Dynamic() {
		super();
	}
	
	public Dynamic(Integer tdoors, Integer tclose, Integer ttransit, Integer tslow, Integer taccel, Integer tfast) {
		super();
		Tdoors = tdoors;
		Tclose = tclose;
		Ttransit = ttransit;
		Tslow = tslow;
		Taccel = taccel;
		Tfast = tfast;
	}
	public Integer getTdoors() {
		return Tdoors;
	}
	public void setTdoors(Integer tdoors) {
		Tdoors = tdoors;
	}
	public Integer getTclose() {
		return Tclose;
	}
	public void setTclose(Integer tclose) {
		Tclose = tclose;
	}
	public Integer getTtransit() {
		return Ttransit;
	}
	public void setTtransit(Integer ttransit) {
		Ttransit = ttransit;
	}
	public Integer getTslow() {
		return Tslow;
	}
	public void setTslow(Integer tslow) {
		Tslow = tslow;
	}
	public Integer getTaccel() {
		return Taccel;
	}
	public void setTaccel(Integer taccel) {
		Taccel = taccel;
	}
	public Integer getTfast() {
		return Tfast;
	}
	public void setTfast(Integer tfast) {
		Tfast = tfast;
	}
	
	
	
}
