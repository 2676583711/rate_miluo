package com.rate.system.rate_system.utils.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

public class CatalogExcelUtil {


    public static XSSFWorkbook dropDownList42007(Workbook wb, Sheet realSheet, String[] datas, int startRow, int endRow,
                                                 int startCol, int endCol)
            throws Exception {
        XSSFWorkbook workbook = (XSSFWorkbook) wb;
        XSSFSheet sheet = (XSSFSheet) realSheet;
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(datas);
        CellRangeAddressList addressList = null;
        XSSFDataValidation validation = null;
//        workbook.setSheetHidden(hiddenSheetIndex, true);
     // 加载下拉列表内容
        addressList = new CellRangeAddressList(startRow, endRow, startCol, endCol);
        validation = (XSSFDataValidation) dvHelper.createValidation(
                dvConstraint, addressList);
        // 07默认setSuppressDropDownArrow(true);
        validation.setSuppressDropDownArrow(true);
        // validation.setShowErrorBox(true);
    	// 数据有效性对象
    	validation.setSuppressDropDownArrow(true);
    	validation.createErrorBox("tip","请从下拉列表选取");
    	//错误警告框
    	validation.setShowErrorBox(true);
        sheet.addValidationData(validation);
        return workbook;
    }
	/** 
	 * textFormatting:设置列的单元格格式为文本 <br/> 
	 * @author jiangya 
	 * @param wb
	 * @param sheet
	 * @param column 列
	 */  
    public static void textFormat(XSSFWorkbook wb, XSSFSheet sheet,int column) {
		CellStyle css = wb.createCellStyle();
		DataFormat  format = wb.createDataFormat();
		css.setDataFormat(format.getFormat("@"));
		sheet.setDefaultColumnStyle(column,css);
	}
	/** 
	 * dateFormatt: 设置某列单元格时间格式为yyyy-mm-dd<br/> 
	 * @author jiangya 
	 * @param wb
	 * @param sheet
	 * @param column 列
	 */  
    public static void dateFormatt (XSSFWorkbook wb, XSSFSheet sheet,int column) {
		CellStyle cellStyle = wb.createCellStyle();
		DataFormat format= wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));
        sheet.setDefaultColumnStyle(column,cellStyle);
	}

}

