package Utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
    Workbook wb;
    //Constructer for reading excel path
    public ExcelFileUtil(String excelpath) throws Throwable
    {
    	FileInputStream fi=new FileInputStream(excelpath);
    	wb=WorkbookFactory.create(fi);
    }
    //counting no. of rows in a sheet
    public int rowCount(String Sheetname) 
    {
    	return wb.getSheet(Sheetname).getLastRowNum();
    }
    //method for reading cell data
    public String getCelldata(String Sheetname,int Row,int Column)
    {
    	String data="";
    	if(wb.getSheet(Sheetname).getRow(Row).getCell(Column).getCellType()==Cell.CELL_TYPE_NUMERIC)
    	{
    		int cellData=(int) wb.getSheet(Sheetname).getRow(Row).getCell(Column).getNumericCellValue();
    		data=String.valueOf(cellData);
    	}
    	else {
    		data=wb.getSheet(Sheetname).getRow(Row).getCell(Column).getStringCellValue();
    	}
    	return data;
    }
    //Method for Writing status
    public void setCelldata(String Sheetname,int Row,int Coloumn,String Status,String writeexcel)throws Throwable
    {
    	//get sheet from workbook
    	Sheet ws=wb.getSheet(Sheetname);
    	//get row from sheet
    	Row rowNum = ws.getRow(Row);
    	//Create cell in a Row
    	Cell cell=rowNum.createCell(Coloumn);
    	//Write status
    	cell.setCellValue(Status);
    	if(Status.equalsIgnoreCase("pass"))
    	{
    	   CellStyle style=wb.createCellStyle();
    	   Font font=wb.createFont();
    	   //set font colour green
    	   font.setColor(IndexedColors.DARK_GREEN.getIndex());
    	   font.setBold(true);
    	   font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    	   style.setFont(font);
    	   rowNum.getCell(Coloumn).setCellStyle(style);
      	}
    	else if(Status.equalsIgnoreCase("Fail"))
    	{
    		CellStyle style=wb.createCellStyle();
    		Font font=wb.createFont();
    		//set font colour red
    		font.setColor(IndexedColors.RED.getIndex());
    		font.setBold(true);
    		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    		style.setFont(font);
    		rowNum.getCell(Coloumn).setCellStyle(style);
      	}
    	else if(Status.equalsIgnoreCase("Blocked"))
    	{
    		CellStyle style=wb.createCellStyle();
    		Font font=wb.createFont();
    		//set font colour black
    		font.setColor(IndexedColors.BLACK.getIndex());
    		font.setBold(true);
    		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    		style.setFont(font);
    		rowNum.getCell(Coloumn).setCellStyle(style);
    	}
    	FileOutputStream fo=new FileOutputStream(writeexcel);
    	wb.write(fo);
    }
}
