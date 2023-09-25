package br.com.brasilprev.api.entity.enums;

import java.util.Objects;

public enum ProfileEnum {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String descricao;
    
    

    private ProfileEnum(Integer code, String descricao) {
		this.code = code;
		this.descricao = descricao;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    

}
