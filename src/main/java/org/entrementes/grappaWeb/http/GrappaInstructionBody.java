package org.entrementes.grappaWeb.http;

public class GrappaInstructionBody {

	private Integer value;
	
	public GrappaInstructionBody(int value) {
		this.value = value;
	}

	public GrappaInstructionBody() {
	}
	
	public Integer getValue() {
		return value;
	}

	public static GrappaInstructionBody valueOf(String parameter){
		if(parameter != null && !parameter.isEmpty()){
			try{
				return new GrappaInstructionBody(Integer.parseInt(parameter));
			}catch(NumberFormatException ex){
				return new GrappaInstructionBody();
			}
		}else{
			return new GrappaInstructionBody();
		}
	}
}
