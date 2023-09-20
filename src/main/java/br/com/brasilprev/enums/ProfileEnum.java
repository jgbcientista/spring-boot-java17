package br.com.brasilprev.enums;


import java.util.Objects;

public enum ProfileEnum {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;
    
    

    private ProfileEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}


	public static ProfileEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (ProfileEnum x : ProfileEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
    

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
    

}