package com.jendrix.controlfy.model;

import java.math.BigDecimal;

public class ResumenModel {

	private String tipoOperacion;
	private BigDecimal importeTotal;

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public ResumenModel() {
		this.importeTotal = BigDecimal.ZERO;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
}
