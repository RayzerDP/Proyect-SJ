// ReportService.java
package com.example.SSjApi.service;

import com.example.SSjApi.dto.RecursoPorCategoriaDTO;
import com.example.SSjApi.repository.RecursoRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private RecursoRepository recursoRepository;

    public ByteArrayInputStream generarReporteRecursosPorCategoria() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Agregar título
            Paragraph title = new Paragraph("Reporte de Recursos por Categoría");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Espacio
            document.add(new Paragraph(" "));

            // Crear tabla
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 1, 1 });

            // Encabezados de tabla
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Categoría"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cantidad"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            // Datos de la tabla
            List<RecursoPorCategoriaDTO> recursosPorCategoria = recursoRepository.countRecursosByCategoria();
            for (RecursoPorCategoriaDTO recurso : recursosPorCategoria) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(recurso.getCategoria()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(recurso.getCantidad())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
