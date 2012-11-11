package com.agsupport.core.service.communication;

import java.io.Serializable;
import java.util.List;

import com.agsupport.core.jpa.model.DerivativeValue;

public class ListOfDerivativeValue implements Serializable {

	private List<DerivativeValue> derivativeValues;

	public ListOfDerivativeValue() {
	}

	public ListOfDerivativeValue(List<DerivativeValue> derivativeValues) {
		this.derivativeValues = derivativeValues;
	}

	public List<DerivativeValue> getDerivativeValues() {
		return derivativeValues;
	}

	public void setDerivativeValues(List<DerivativeValue> derivativeValues) {
		this.derivativeValues = derivativeValues;
	}

}
