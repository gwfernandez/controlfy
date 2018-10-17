package com.jendrix.controlfy.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoMP {

	private Long numeroMovimiento; // col 3
	private String tipoOperacion; // col 2
	private Date fechaPago; // col 1
	private BigDecimal importe; // col 5
	private BigDecimal saldo; // col 6
	private Long operacionRelacionada; // 4

	public Long getNumeroMovimiento() {
		return numeroMovimiento;
	}

	public void setNumeroMovimiento(Long numeroMovimiento) {
		this.numeroMovimiento = numeroMovimiento;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Long getOperacionRelacionada() {
		return operacionRelacionada;
	}

	public void setOperacionRelacionada(Long operacionRelacionada) {
		this.operacionRelacionada = operacionRelacionada;
	}
}