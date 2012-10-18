package pdfparse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.security.PdfPKCS7;

public class ExtractPageContentArea {

    /** The original PDF that will be parsed. */
    public static final String PREFACE = "C:/Users/smalpani/Downloads/Project_Abstract_295A.pdf";
    /** The resulting text file. */
    public static final String RESULT = "C:/Users/smalpani/Downloads/Project_Abstract_295As.txt";
    
    /**
     * Parses a specific area of a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        Rectangle rect = new Rectangle(70, 80, 490, 580);
        RenderFilter filter = new RegionTextRenderFilter(rect);
        TextExtractionStrategy strategy;
        int temp = reader.getNumberOfPages();
        AcroFields a = reader.getAcroFields();
        ArrayList<String> b = a.getSignatureNames();
        PdfPKCS7 j = a.verifySignature("Magdalini");
        
        Iterator<String> c = b.iterator();
        while(c.hasNext())
        {
        	System.out.println(c.next() + "   " + j);
        	System.out.println("   " + j.getEncryptionAlgorithm() );
        	System.out.println("   " +j.getDigestAlgorithm() );
        	System.out.println("   " + j.getSignName());
        	System.out.println(" " + j.getLocation());
        }
        System.out.println(reader.getLastXref() + "   " + reader.getPermissions() + "   " +reader.getAcroFields());
       
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
            out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
        }
        out.flush();
        out.close();
    }

    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        new ExtractPageContentArea().parsePdf(PREFACE, RESULT);
    }
}