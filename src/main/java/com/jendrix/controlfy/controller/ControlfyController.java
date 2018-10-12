package com.jendrix.controlfy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jendrix.controlfy.model.ControlModel;
import com.jendrix.controlfy.model.MovimientoModel;

@Controller
public class ControlfyController {

	private MultipartFile fileupload;
	private Workbook workbook;
	private Sheet sheet;
	
	private ControlModel control;  

	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {

		try {			
			// valido el archivo excel
			this.fileupload = file;
			validarArchivo();

			procesarExcel();
			
			model.addAttribute("control", this.control);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//return "redirect:/";
		return "index";
	}

	private void validarArchivo() throws IllegalArgumentException {

		// valido que haya enviado un archivo
		if (this.fileupload == null) {
			throw new IllegalArgumentException("Debe seleccionar un archivo a procesar");
		}

		// creo el workbook en base al archivo seleccionado
		try {
			this.workbook = WorkbookFactory.create(this.fileupload.getInputStream());
		} catch (EncryptedDocumentException | IOException e) {
			throw new IllegalArgumentException(
					"No se puede procesar el archivo seleccionado. Por favor verifique que el formato del archivo sea correcto");
		}

		// valido que el workbook tenga la solapa 'promos'
		this.sheet = this.workbook.getSheetAt(0);
		if (this.sheet == null) {
			throw new IllegalArgumentException(
					"El archivo " + this.fileupload.getOriginalFilename() + " no tiene solapas");
		}
	}

	private void procesarExcel() {
		List<MovimientoModel> movimientoList = new ArrayList<MovimientoModel>();
		this.control = new ControlModel();
		this.control.setCantidadMovimientos(0);
		for (int i = 1; i < this.sheet.getPhysicalNumberOfRows(); i++) {
			MovimientoModel movimientoModel = mapearFila(this.sheet.getRow(i));
			movimientoList.add(movimientoModel);
			this.control.setCantidadMovimientos(this.control.getCantidadMovimientos() + 1);
		}
	}
	
	private MovimientoModel mapearFila(Row row) {
		MovimientoModel movimiento = new MovimientoModel();

		try {

			// leer columna codigo de articulo
			Cell cellCodigoArticulo = row.getCell(0);
			try {
				if (CellType.NUMERIC == cellCodigoArticulo.getCellType()) {
					//promoMasiva.setCodigoArticuloFVG(((long) cellCodigoArticulo.getNumericCellValue()));
					System.out.println();
				} else {
					cellCodigoArticulo.setCellType(CellType.STRING);
					//promoMasiva.setCodigoArticuloFVG(Long.valueOf(cellCodigoArticulo.getStringCellValue()));
				}
			} catch (Exception e) {
				cellCodigoArticulo.setCellType(CellType.STRING);
				//msg.addError("Codigo de articulo incorrecto: " + cellCodigoArticulo.getStringCellValue());
			}


		} catch (Exception e) {
			//msg.addError("Error leyendo los datos de la promo");
		}
		
		return movimiento;
	}
	

}
