package com.jendrix.controlfy.model;

import java.math.BigDecimal;

public class ControlModel {
	Integer cantidadMovimientos;
	Integer cantidadVentas;
	BigDecimal totalVentas;
	BigDecimal totalComisiones;

	public Integer getCantidadMovimientos() {
		return cantidadMovimientos;
	}

	public void setCantidadMovimientos(Integer cantidadMovimientos) {
		this.cantidadMovimientos = cantidadMovimientos;
	}

	public Integer getCantidadVentas() {
		return cantidadVentas;
	}

	public void setCantidadVentas(Integer cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}

	public BigDecimal getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(BigDecimal totalVentas) {
		this.totalVentas = totalVentas;
	}

	public BigDecimal getTotalComisiones() {
		return totalComisiones;
	}

	public void setTotalComisiones(BigDecimal totalComisiones) {
		this.totalComisiones = totalComisiones;
	}
}
