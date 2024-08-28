package com.proyecto.piscina.web.app.util.reportes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Calendar;
import java.awt.Color;
import java.io.IOException;

import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.proyecto.piscina.web.app.entities.Matricula;

import jakarta.servlet.http.HttpServletResponse;

public class MatriculasPDF {

    private List<Matricula> ListaMatriculas;
    private int anio = 2024;

    public MatriculasPDF(List<Matricula> ListaMatriculas) {
        this.ListaMatriculas = ListaMatriculas;
    }

    private Map<Integer, List<Matricula>> agruparMatriculasPorMes() {
        return ListaMatriculas.stream()
                .filter(matricula -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(matricula.getFechaMatricula());
                    return cal.get(Calendar.YEAR) == anio;
                })
                .collect(Collectors.groupingBy(matricula -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(matricula.getFechaMatricula());
                    return cal.get(Calendar.MONTH) + 1;  // Enero es 0, por eso se suma 1
                }));
    }

    private void escribirCabeceraTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(new Color(33, 150, 243));
        celda.setPadding(8);
        celda.setBorderWidth(1.5f);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Alumno", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Clase", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Estado", fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla, List<Matricula> matriculasDelMes) {
        for (Matricula matricula : matriculasDelMes) {
            PdfPCell celda = new PdfPCell();
            celda.setPadding(8);

            celda.setPhrase(new Phrase(String.valueOf(matricula.getIdMatricula()), FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK)));
            tabla.addCell(celda);

            celda.setPhrase(new Phrase(matricula.getAlumno().getNombre() + ' ' + matricula.getAlumno().getApellido(), FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK)));
            tabla.addCell(celda);

            celda.setPhrase(new Phrase(matricula.getClase().getCurso().getNombre(), FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK)));
            tabla.addCell(celda);

            celda.setPhrase(new Phrase(matricula.getFechaMatricula().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK)));
            tabla.addCell(celda);

            celda.setPhrase(new Phrase(matricula.getEstado(), FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK)));
            tabla.addCell(celda);
        }
    }

    private void agregarEncabezadoYPiePagina(Document documento, HttpServletResponse response) throws DocumentException, IOException {
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.GRAY);
        Paragraph encabezado = new Paragraph("Piscina Poseidón - Reporte de Matrículas por Año " + anio, fuente);
        encabezado.setAlignment(Paragraph.ALIGN_CENTER);
        encabezado.setSpacingAfter(10);
        documento.add(encabezado);
    }

    private void exportar(HttpServletResponse response) {
        Document documento = new Document(PageSize.A4);
    
        try {
            PdfWriter writer = PdfWriter.getInstance(documento, response.getOutputStream());
            documento.open();

            agregarEncabezadoYPiePagina(documento, response);

            Map<Integer, List<Matricula>> matriculasPorMes = agruparMatriculasPorMes();
    
            Font fuenteTituloMes = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLACK);

            for (int mes = 1; mes <= 12; mes++) {
                List<Matricula> matriculasDelMes = matriculasPorMes.get(mes);
                if (matriculasDelMes != null && !matriculasDelMes.isEmpty()) {
                    // Agregar título del mes
                    Paragraph tituloMes = new Paragraph("Mes: " + obtenerNombreMes(mes), fuenteTituloMes);
                    tituloMes.setAlignment(Paragraph.ALIGN_LEFT);
                    tituloMes.setSpacingBefore(20);
                    tituloMes.setSpacingAfter(10);
                    documento.add(tituloMes);

                    PdfPTable tabla = new PdfPTable(5);
                    tabla.setWidthPercentage(100);
                    tabla.setSpacingBefore(15);
                    tabla.setWidths(new float[] { 1.2f, 3.5f, 3.5f, 3.0f, 2.0f });
    
                    escribirCabeceraTabla(tabla);
                    escribirDatosTabla(tabla, matriculasDelMes);
    
                    documento.add(tabla);
                }
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            documento.close();
        }
    }

    private String obtenerNombreMes(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes - 1];
    }
    
    public void generarPDF(HttpServletResponse response) {
        exportar(response);
    }
}