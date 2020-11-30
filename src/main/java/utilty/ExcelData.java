package utilty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData extends Utility {
	public  XSSFWorkbook workbook;
	public  XSSFSheet sheet;
	public  XSSFCell cell;
	
	Properties prop=new Properties();
	
	/*
	 * Method to initialize Excel sheet and get Cell data
	 * Created by : Benarji Enamandala
	 * 
	 */
	 public String getExcelData() throws IOException {
		 try {
			 prop = propFile(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\config.properties");
			 String excelFilePath=System.getProperty("user.dir")+prop.getProperty("excelpath");
			 String sheetName=prop.getProperty("sheetname");
			 
		       File file =    new File(excelFilePath);
		       FileInputStream inputStream = new FileInputStream(file);
		      workbook=new XSSFWorkbook(inputStream);
		       //creating a Sheet object
		        sheet=workbook.getSheet(sheetName);
		 
			 
		       //getting the cell value from rowNumber and cell Number
		        cell =sheet.getRow(1).getCell(0);
		        System.out.println(cell.getStringCellValue());
		        
		        //returning the cell value as string
		    	}catch (Exception e) {
		    		e.printStackTrace();
					// TODO: handle exception
				}
		        return cell.getStringCellValue();
		    }
	
	 

}
