package br.com.triadworks.bugtracker.bean.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("senhaForte")
public class SenhaForteValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent c, Object o) throws ValidatorException{
		String senha = (String) o;
		if (!senha.contains("@")) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha Fraca!", "A senha deve conter o caracter '@'");
			throw new ValidatorException(msg);
		}
	}
	
}
