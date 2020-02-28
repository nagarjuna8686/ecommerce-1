package ecommerce.dto;

public class PasswordsDto {

	private String oldP;
	private String newP;
	private String confP;

	public PasswordsDto() {}
	
	public PasswordsDto(String oldP, String newP, String confP){
		this.oldP = oldP;
		this.newP = newP;
		this.confP = confP;
	}

	public String getOldP() {
		return oldP;
	}

	public void setOldP(String oldP) {
		this.oldP = oldP;
	}

	public String getNewP() {
		return newP;
	}

	public void setNewP(String newP) {
		this.newP = newP;
	}

	public String getConfP() {
		return confP;
	}

	public void setConfP(String confP) {
		this.confP = confP;
	}
	
	
}
