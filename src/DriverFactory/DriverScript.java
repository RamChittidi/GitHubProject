package DriverFactory;

import org.testng.annotations.Test;

import CommonFunctions.FunctionsLibrary;
import Utilities.ExcelFileUtil;
import constant.PBconstant;

public class DriverScript extends PBconstant{
       String inputpath="G:\\Eclipse_1\\Automation_frameworks\\TestInput\\HybridTest.xlsx";
       String outputpath="G:\\Eclipse_1\\Automation_frameworks\\TestOutput\\HybridResults.xlsx";
       String TCSheet="TestCases";
       String TSSheet="TestSteps";
       @Test
       public void startTest() throws Throwable
       {
    	   boolean res=false;
    	   String tcres="";
    	   //create reference Object
    	   ExcelFileUtil xl=new ExcelFileUtil(inputpath);
    	   //count no. of rows in TSSheet and TCSheet
    	   int TCCount=xl.rowCount(TCSheet);
    	   int TSCount=xl.rowCount(TSSheet);
    	   //iterate all rows in Tc sheet
    	   for(int i=1;i<=TCCount;i++)
    	   {
    		   //store all module into TCModule
    		   String TCModule=xl.getCelldata(TCSheet, i, 1);
    		   //read all executionmode cell
    		   String Executionmode=xl.getCelldata(TCSheet, i, 2);
    		   if(Executionmode.equalsIgnoreCase("Y"))
    		   {
    			   //read tcid from tc sheet
    			   String tcid=xl.getCelldata(TCSheet, i, 0);
    			   //iterate all Tssheet
    			   for(int j=1;j<=TSCount;i++)
    			   {
    				   //read tsid from ts sheet
    				   String tsid=xl.getCelldata(TSSheet, j, 0);
    				   if(tcid.equalsIgnoreCase(tsid))
    				   {
    					   //read keyword from ts sheet
    					   String keyword=xl.getCelldata(TSSheet, j, 4);
    					   if(keyword.equalsIgnoreCase("AdminLogin"))
    					   {
    						   String username=xl.getCelldata(TSSheet, j, 5);
    						   String password=xl.getCelldata(TSSheet, j, 6);
    						   res=FunctionsLibrary.verifyLogin(username, password);
    					   }else if(keyword.equalsIgnoreCase("NewBranchCreation"))
    					   {
    						   String bname=xl.getCelldata(TSSheet, j, 5);
    						   String address1=xl.getCelldata(TSSheet, j, 6);
    						   String address2=xl.getCelldata(TSSheet, j, 7);
    						   String address3=xl.getCelldata(TSSheet, j, 8);
    						   String area=xl.getCelldata(TSSheet, j, 9);
    						   String zip=xl.getCelldata(TSSheet, j, 10);
    						   String country=xl.getCelldata(TSSheet, j, 12);
    						   String state=xl.getCelldata(TSSheet, j,13);
    						   String city=xl.getCelldata(TSSheet, j,14);
    						   FunctionsLibrary.clickBranches();
    						   res=FunctionsLibrary.verifyNewBranch(bname,address1,address2,address3,area,zip,country,state,city);

    					   }
    					   else if(keyword.equalsIgnoreCase("UpdateBranch"))
    					   {
    						   String branchname=xl.getCelldata(TSSheet, j, 5);
    						   String address=xl.getCelldata(TSSheet, j, 6);
    						   String zipcode=xl.getCelldata(TSSheet, j,10);
    						   FunctionsLibrary.clickBranches();
    						   res=FunctionsLibrary.verifyBranchUpdate(branchname, address, zipcode);

    					   }
    					   else if(keyword.equalsIgnoreCase("AdminLogout"))
    					   {
    						   res=FunctionsLibrary.verifyLogout();
    					   }
    					   //res id holding true or false
    					   String tsres="";
    					   if(res)
    					   {
    						   tsres="pass";
    						   xl.setCelldata(TSSheet, j, 3, tsres, outputpath);
    					   }
    					   else
    					   {
    						   tsres="fail";
    						   xl.setCelldata(TSSheet, j, 3, tsres, outputpath);
    					   }
    					   tcres=tsres;
    				   }
    			   }
    			   //write tcres into TcSheet
    			   xl.setCelldata(TCSheet, i, 3, tcres, outputpath);
    		   }
    			   else
    			   {
    				   xl.setCelldata(TCSheet, i, 3, "Blocked", outputpath);
    			   }
    		   }
    	   }
       }

