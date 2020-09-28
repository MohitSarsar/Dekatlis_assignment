package Bookpillow;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.IllegalFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelSheet {

	XSSFWorkbook wb;
	XSSFSheet sheet ;
	int colcount;
	
	public ReadExcelSheet(String excelPath) throws IllegalFormatException, IOException{
		
		File srcFile = new File(excelPath);
		FileInputStream fis = new FileInputStream(srcFile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
		wb.close();
	}
	
	public int getCellType(int sheetNumber, int row, int column){
		int type;
		
		type=sheet.getRow(row).getCell(column).getCellType();
		return  type;
		
	}
	
	public int getCellIntData(int sheetNumber, int row, int column){
		int data0 = 0;
		try{
			data0 = (int)sheet.getRow(row).getCell(column).getNumericCellValue();
		}catch(Exception e){
			//data0 = "";
		}
		
		return data0;
	}
	
	
	public String getCellData(int sheetNumber, int row, int column){
		String data0;
		try{
			data0 = sheet.getRow(row).getCell(column).getStringCellValue();
		}catch(Exception e){
			data0 = "";
		}
		return data0;
	}
	
	public String getCellDate(int sheetNumber, int row, int column){
		String df;
		try{
			df=new DataFormatter().formatCellValue(sheet.getRow(row).getCell(column));
		}catch(Exception e){
			df = "";
		}
		return df;
	}
	
	public int getRowCount(int sheetNumber){
		
		int rowCNT = wb.getSheetAt(sheetNumber).getLastRowNum();
		return rowCNT;
		
	}
	

}
