package com.example.SSjApi.service;

import com.example.SSjApi.dto.PedidosPorUsuarioDTO;
import com.example.SSjApi.repository.PedidoRepository;
import com.itextpdf.text.BaseColor;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class ReporteService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ByteArrayInputStream generarReportePedidosConDetalles() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Añadir título
            document.add(new Paragraph("Reporte de Pedidos por Usuario"));

            // Añadir un párrafo vacío para el espaciado
            document.add(new Paragraph(" "));

            // Crear la tabla
            PdfPTable table = new PdfPTable(7); // Número de columnas
            table.setWidthPercentage(100); // Ancho de la tabla
            table.setWidths(new int[] { 3, 3, 3, 2, 3, 3, 3 }); // Ancho relativo de las columnas

            // Añadir cabeceras de la tabla
            Stream.of("Nombre Producto", "Fecha de Ingreso", "Fecha de Creacion", "Cantidad", "Proveedor",
                    "Precio Unitario", "Nombre de Usuario")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle));
                        table.addCell(header);
                    });

            // Añadir datos de la tabla
            for (PedidosPorUsuarioDTO pedido : pedidoRepository.findAllPedidosConDetalles()) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(pedido.getNombreProducto()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getFechaIngreso().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getFechaCreacion().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getCantidad().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getProveedor()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getPrecioUnitario().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getNombreUsuario()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReporteService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
