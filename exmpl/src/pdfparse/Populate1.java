package pdfparse;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Populate1 {

	private ArrayList<String> files = new ArrayList<String>();
	static ArrayList<Book> books = new ArrayList<Book>();

	public Populate1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		String path = "C:/Users/smalpani/Desktop/parse.txt";

		Populate1 p = new Populate1();
		p.bulkGenerate(path);
		
	}

	public void bulkGenerate(String path) throws IOException {

		String filename="C:/hello.xls" ;
		HSSFWorkbook hwb=new HSSFWorkbook();
		HSSFSheet sheet =  hwb.createSheet("new sheet");

		HSSFRow rowhead=   sheet.createRow((int)0);
		rowhead.createCell((int) 0).setCellValue("SNo");
		rowhead.createCell((int) 1).setCellValue("Rule");
		rowhead.createCell((int) 2).setCellValue("When");
		rowhead.createCell((int) 3).setCellValue("Then");

		files.add(path);
		for (String f: files) {

			//System.out.println();
			FileInputStream fsStream = new FileInputStream(f);
			DataInputStream diStream = new DataInputStream(fsStream);
			BufferedReader biStream = new BufferedReader(new InputStreamReader(diStream));
			String strLine;
			HSSFRow row = null;;
			int num = 1;
			while((strLine = biStream.readLine()) != null)
			{
				

				if (strLine.trim().startsWith("rule"))
				{
					row =   sheet.createRow((int)num);
					row.createCell((int) 0).setCellValue(num);
					num++;
					String[] master = strLine.trim().split("\"");
					System.out.println();
					System.out.println("RULE: " + master[1].trim());
					row.createCell((int) 1).setCellValue(master[1].trim());
				}

				else if (strLine.trim().startsWith("when")) {
					StringBuffer strBuf = new StringBuffer();
					StringBuffer strBuf1 = new StringBuffer();
					String q = biStream.readLine();
					strBuf.append(q+"; ");
					String b;
					boolean b12 = true;
					while((b = biStream.readLine()) != null && b12 == true) {
						if(b.trim().startsWith("then")) {
							q = biStream.readLine();
							strBuf1.append(q+"; ");
							String bt;
							while((bt = biStream.readLine()) != null && !bt.trim().startsWith(";;")) {
								strBuf1.append(bt+"; ");
							}
							b12 = false;
							break;
						} else {
							strBuf.append(b+"; ");
						}
					}
					System.out.println("WHEN:\n" + strBuf.toString());
					System.out.println();
					System.out.println("THEN:\n" + strBuf1.toString());
					System.out.println();
					row.createCell((int) 2).setCellValue(strBuf.toString().trim());
					row.createCell((int) 3).setCellValue(strBuf1.toString().trim());
				}
				else if (strLine.trim().isEmpty()) { ; }
			}
			biStream.close();
		}

		FileOutputStream fileOut =  new FileOutputStream(filename);
		hwb.write(fileOut);
		fileOut.close();
		System.out.println("Your excel file has been generated!");


	}
}
