package com.jendrix.controlfy.mapper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import com.jendrix.controlfy.model.MovimientoModel;

public class MovimientoMapper {

	private static final int COLUMNA_FECHA_PAGO = 0;
	private static final int COLUMNA_TIPO_OPERACION = 1;
	private static final int COLUMNA_NUMERO_MOVIMIENTO = 2;
	private static final int COLUMNA_OPERACION_RELACIONADA = 3;
	private static final int COLUMNA_IMPORTE = 4;
	private static final int COLUMNA_SALDO = 5;

	private static MovimientoMapper instance;

	public static MovimientoMapper getInstance() {
		if (instance == null) {
			instance = new MovimientoMapper();
		}
		return instance;
	}

	public static MovimientoModel map(Row row) throws Exception {
		MovimientoModel mov = new MovimientoModel();
		if (row != null) {
			Integer numeroFila = row.getRowNum();
			try {
				// leer columna FECHA DE PAGO
				Cell cellFechaPago = row.getCell(COLUMNA_FECHA_PAGO);
				if (!isEmptyCell(cellFechaPago)) {
					try {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("es_AR"));
						cellFechaPago.setCellType(CellType.STRING);
						mov.setFechaPago(formatter.parse(cellFechaPago.getStringCellValue()));
					} catch (Exception e) {
						throw new Exception(
								"[" + numeroFila + "] fecha de pago incorrecto: " + cellFechaPago.getStringCellValue());
					}
				}

				// leer columna TIPO OPERACION
				Cell cellTipoOperacion = row.getCell(COLUMNA_TIPO_OPERACION);
				if (!isEmptyCell(cellTipoOperacion)) {
					try {
						cellTipoOperacion.setCellType(CellType.STRING);
						mov.setTipoOperacion(cellTipoOperacion.getStringCellValue());
					} catch (Exception e) {
						throw new Exception("[" + numeroFila + "] Tipo de operacion incorrecto: "
								+ cellTipoOperacion.getStringCellValue());
					}
				}

				// leer columna NUMERO MOVIMIENTO
				Cell cellNumeroMovimiento = row.getCell(COLUMNA_NUMERO_MOVIMIENTO);
				if (!isEmptyCell(cellNumeroMovimiento)) {
					try {
						cellNumeroMovimiento.setCellType(CellType.NUMERIC);
						mov.setNumeroMovimiento(Long.valueOf((long) cellNumeroMovimiento.getNumericCellValue()));
					} catch (Exception e) {
						cellNumeroMovimiento.setCellType(CellType.STRING);
						throw new Exception("[" + numeroFila + "] Numero de movimiento incorrecto: "
								+ cellNumeroMovimiento.getStringCellValue());
					}
				}

				// leer columna OPERACION RELACIONADA
				Cell cellOperacionRelacionada = row.getCell(COLUMNA_OPERACION_RELACIONADA);
				if (!isEmptyCell(cellOperacionRelacionada)) {
					try {
						cellOperacionRelacionada.setCellType(CellType.NUMERIC);
						mov.setOperacionRelacionada(
								Long.valueOf((long) cellOperacionRelacionada.getNumericCellValue()));
					} catch (Exception e) {
						cellOperacionRelacionada.setCellType(CellType.STRING);
						throw new Exception("[" + numeroFila + "] Operacion relacionada incorrecto: "
								+ cellOperacionRelacionada.getStringCellValue());
					}
				}

				Cell cellImporte = row.getCell(COLUMNA_IMPORTE);
				if (!isEmptyCell(cellImporte)) {
					try {
						cellImporte.setCellType(CellType.NUMERIC);
						mov.setImporte(BigDecimal.valueOf(cellImporte.getNumericCellValue()));
					} catch (Exception e) {
						cellImporte.setCellType(CellType.STRING);
						throw new Exception(
								"[" + numeroFila + "] Importe incorrecto: " + cellImporte.getStringCellValue());
					}
				}

				Cell cellSaldo = row.getCell(COLUMNA_SALDO);
				if (!isEmptyCell(cellSaldo)) {
					try {
						cellSaldo.setCellType(CellType.NUMERIC);
						mov.setSaldo(BigDecimal.valueOf(cellSaldo.getNumericCellValue()));
					} catch (Exception e) {
						cellSaldo.setCellType(CellType.STRING);
						throw new Exception(
								"[" + numeroFila + "] Importe incorrecto: " + cellSaldo.getStringCellValue());
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return mov;
	}

	private static boolean isEmptyCell(Cell cell) {
		return (cell == null || cell.getCellType() == CellType.BLANK);
	}	
}
