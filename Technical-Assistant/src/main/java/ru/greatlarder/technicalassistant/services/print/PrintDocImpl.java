package ru.greatlarder.technicalassistant.services.print;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

public class PrintDocImpl implements PrintDoc{
    @Override
    public void print(Node node) {
       /* // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
         
            // Print the node
            boolean printed = job.printPage(node);
            
            if (printed) {
                // End the printer job
                job.endJob();
            }
        }*/
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);
        
        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                
            }
        }
        node.getTransforms().remove(scale);
    }
}
