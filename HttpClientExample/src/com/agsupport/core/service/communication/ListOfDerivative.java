package com.agsupport.core.service.communication;

import java.io.Serializable;
import java.util.List;

import com.agsupport.core.jpa.model.Derivative;

public class ListOfDerivative implements Serializable {

	private List<Derivative> derivatives;

	public ListOfDerivative() {
	}

	public ListOfDerivative(List<Derivative> derivatives) {
		this.derivatives = derivatives;
	}

	public List<Derivative> getDerivatives() {
		return derivatives;
	}

	public void setDerivatives(List<Derivative> derivatives) {
		this.derivatives = derivatives;
	}

}
