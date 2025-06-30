package com.ragheb.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.ragheb.validator.SalaryValidator")
public class SalaryValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        double salary = 0;
        try {
            salary = Double.valueOf(o.toString());
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary validation failed:", "Salary must be number!"));
        }
        if(salary <= 5000000 && salary % 100000 != 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary validation failed:", "Invalid Salary!"));
        }
    }
}
