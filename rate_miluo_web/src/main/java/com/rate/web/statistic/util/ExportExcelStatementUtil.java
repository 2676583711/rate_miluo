package com.rate.web.statistic.util;

import com.rate.web.statistic.entity.AverageCompare;
import com.rate.web.statistic.vo.ConcentrationVO;
import com.rate.web.statistic.vo.QualityCategory;
import com.rate.web.statistic.vo.RankVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportExcelStatementUtil {


    public HSSFWorkbook exportExcllentCategory1(List<QualityCategory> list, String s, String statr, String end) {
        // 声明String数组，并初始化元素（表头名称）
        // 第一行表头字段，合并单元格时字段跨几列就将该字段重复几次
        String[] excelHeader0 = {"站点名称", "时间", "优", "", "良", "", "轻度污染", "", "中度污染", "", "重度污染", "", "严重污染", "", "总天数",
                "有效天数", "优良天数", "优良比例"};
        // “0,2,0,0” ===> “起始行，截止行，起始列，截止列”
        String[] headnum0 = {
                "0,1,0,0", // 第0列跨2行
                "0,1,1,1",
                "0,0,2,3", // 第1列跨2行
                "0,0,4,5", // 第2-13列跨1行
                "0,0,6,7", // 第14列跨3行
                "0,0,8,9", // 第15列跨3行
                "0,0,10,11", // 第16列跨3行
                "0,0,12,13", "0,1,14,14", "0,1,15,15", "0,1,16,16", "0,1,17,17"};
        // 第二行表头字段，其中的空的双引号是为了补全表格边框
        String[] excelHeader1 = {"优天数", "比率", "良天数", "比率", "轻度污染天数", "比率", "中度污染天数", "比率", "重度污染天数", "比率", "严重污染天数",
                "比率"};
        // 合并单元格
        String[] headnum1 = {"1,1,2,2", "1,1,3,3", "1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7", "1,1,8,8",
                "1,1,9,9", "1,1,10,10", "1,1,11,11", "1,1,12,12", "1,1,13,13"};

        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet("全区空气质量类别统计");

        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成并设置另一个样式style2
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成另一种字体2
        HSSFFont font2 = wb.createFont();
        // 设置字体
        font2.setFontName("微软雅黑");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 12);
        // 字体加粗
        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式2中引用这种字体
        style2.setFont(font2);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader0.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader0[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        // 第二行表头
        row = sheet.createRow(1);
        for (int i = 0; i < excelHeader1.length; i++) {
            HSSFCell cell = row.createCell(i + 2);
            cell.setCellValue(excelHeader1[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i + 2, true);// 自动调整宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        for (int i = 0; i < excelHeader0.length; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        // 这个至此只是输出了表头，对于表格内容应当遍历list，循环填充每一个单元格
        for (int i = 0; i < list.size(); i++) {
            int k = 0;
            QualityCategory qualityCategory = list.get(i);
            row = sheet.createRow(i + 2);

            HSSFCell cell0 = row.createCell(k++);
            if (qualityCategory.getSiteName() != null) {
                cell0.setCellValue(qualityCategory.getSiteName());
            }
            HSSFCell cell1 = row.createCell(k++);
            if (qualityCategory.getTimes() != null) {
                String value = qualityCategory.getTimes();
                String times = "";
                if ("1".equals(s)) {
                    times = value.substring(0, 7);
                } else if ("2".equals(s)) {
                    int as = Integer.parseInt(value.substring(0, 4));
                    int as2 = Integer.parseInt(value.substring(5, 7));
                    if (1 <= as2 && as2 < 4) {
                        times = as + "年第一季度";
                    } else if (4 <= as2 && as2 < 7) {
                        times = as + "年第二季度";
                    } else if (7 <= as2 && as2 < 10) {
                        times = as + "年第三季度";
                    } else {
                        times = as + "年第四季度";
                    }

                } else if ("3".equals(s)) {

                    times = value.substring(0, 4) + "年";

                } else {
                    times = statr + "至" + end;
                }
                cell1.setCellValue(times);
            }
            HSSFCell cell2 = row.createCell(k++);
            if (qualityCategory.getExcellentDays() != null) {
                cell2.setCellValue(qualityCategory.getExcellentDays());
            }
            HSSFCell cell3 = row.createCell(k++);
            if (qualityCategory.getExcellentRatio() != null) {
                cell3.setCellValue(qualityCategory.getExcellentRatio() + "%");
            }
            HSSFCell cell4 = row.createCell(k++);
            if (qualityCategory.getGoodDays() != null) {
                cell4.setCellValue(qualityCategory.getGoodDays());
            }
            HSSFCell cell5 = row.createCell(k++);
            if (qualityCategory.getGoodRatio() != null) {
                cell5.setCellValue(qualityCategory.getGoodRatio() + "%");
            }
            HSSFCell cell6 = row.createCell(k++);
            if (qualityCategory.getMildDays() != null) {
                cell6.setCellValue(qualityCategory.getMildDays());
            }
            HSSFCell cell7 = row.createCell(k++);
            if (qualityCategory.getMildRatio() != null) {
                cell7.setCellValue(qualityCategory.getMildRatio() + "%");
            }
            HSSFCell cell8 = row.createCell(k++);
            if (qualityCategory.getModerateDays() != null) {
                cell8.setCellValue(qualityCategory.getModerateDays());
            }
            HSSFCell cell9 = row.createCell(k++);
            if (qualityCategory.getModerateRatio() != null) {
                cell9.setCellValue(qualityCategory.getModerateRatio() + "%");
            }
            HSSFCell cell10 = row.createCell(k++);
            if (qualityCategory.getSevereDays() != null) {
                cell10.setCellValue(qualityCategory.getSevereDays());
            }
            HSSFCell cell11 = row.createCell(k++);
            if (qualityCategory.getSevereRatio() != null) {
                cell11.setCellValue(qualityCategory.getSevereRatio() + "%");
            }
            HSSFCell cell12 = row.createCell(k++);
            if (qualityCategory.getSeriousnessDays() != null) {
                cell12.setCellValue(qualityCategory.getSeriousnessDays());
            }
            HSSFCell cell13 = row.createCell(k++);
            if (qualityCategory.getSeriousnessRatio() != null) {
                cell13.setCellValue(qualityCategory.getSeriousnessRatio() + "%");
            }
            HSSFCell cell14 = row.createCell(k++);
            if (qualityCategory.getSumDays() != null) {
                cell14.setCellValue(qualityCategory.getSumDays());
            }
            HSSFCell cell15 = row.createCell(k++);
            if (qualityCategory.getValidDays() != null) {
                cell15.setCellValue(qualityCategory.getValidDays());
            }
            HSSFCell cell16 = row.createCell(k++);
            if (qualityCategory.getExcellentAndGood() != null) {
                cell16.setCellValue(qualityCategory.getExcellentAndGood());
            }
            HSSFCell cell17 = row.createCell(k);
            if (qualityCategory.getExcellentAndGoodRatio() != null) {
                cell17.setCellValue(qualityCategory.getExcellentAndGoodRatio() + "%");
            }
        }
        return wb;
    }

    public HSSFWorkbook exportExcllentCategory2(Map<String, Object> dataSet) {

        List<String> time = (List<String>) dataSet.get("time");
        List<ConcentrationVO> data = (List<ConcentrationVO>) dataSet.get("data");
        String[] excelHeader0 = time.stream().toArray(String[]::new);
        String[] headnum0 = new String[excelHeader0.length + 1];
        for (int i = 0; i < headnum0.length; i++) {
            headnum0[i] = "0,0," + i + "," + i;
        }

        String[] excelHeader00 = {"空气质量(优良天数及变化)"};
        String[] headnum00 = {"0,0,0," + (headnum0.length - 1)};

        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet();
        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成另一种样式style，应用在数据行中
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader00.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader00[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i + 2, true);// 自动调整宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum00.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum00[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        row = sheet.createRow(1);
        HSSFCell siteName = row.createCell(0);
        siteName.setCellValue("站点名称");
        siteName.setCellStyle(style);
        // 根据字段长度自动调整列的宽度


        // 根据字段长度自动调整列的宽度
        sheet.autoSizeColumn(1, true);
        for (int i = 0; i < excelHeader0.length; i++) {
            HSSFCell cell = row.createCell(i + 1);
            cell.setCellValue(excelHeader0[i]);
            cell.setCellStyle(style);
            // 根据字段长度自动调整列的宽度
            sheet.autoSizeColumn(i + 1, true);
        }

        for (int i = 0; i < data.size() + 3; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }

        for (int i = 0; i < data.size(); i++) {
            ConcentrationVO concentrationVO = data.get(i);
            row = sheet.createRow(i + 2);
            HSSFCell cell0 = row.createCell(0);
            if (concentrationVO.getSiteName() != null) {
                cell0.setCellValue(concentrationVO.getSiteName());
                cell0.setCellStyle(style2);
            }

            Map<String, Integer> map = new HashMap<>(time.size());

            List lists1 = concentrationVO.getLists();
            List<QualityCategory> lists = concentrationVO.getLists();
            for (QualityCategory hourStatement : lists) {

                map.put(hourStatement.getTimes(), hourStatement.getExcellentAndGood() == null ? 0 : hourStatement.getExcellentAndGood());
            }

         /*    if (type == 1) {
                List<PzDailyStatement> lists = concentrationVO.getLists();
                for (PzDailyStatement dailyStatement : lists) {
                    String format = DateUtils.format(dailyStatement.getsDatetime(), "yyyy-MM-dd");
                    map.put(format, dailyStatement.getValue());
                }
            }*/

            for (int j = 0; j < time.size(); j++) {
                HSSFCell cell = row.createCell(j + 1);
                String time1 = time.get(j);
                if (map.containsKey(time1)) {
                    cell.setCellValue(map.get(time1));
                    cell.setCellStyle(style2);
                } else {
                    cell.setCellValue("");
                    cell.setCellStyle(style2);
                }
            }
        }

        return wb;
    }

    public HSSFWorkbook exportExcllentCategory5(List<RankVo> list, String s, String statr, String end) {
        // 声明String数组，并初始化元素（表头名称）
        // 第一行表头字段，合并单元格时字段跨几列就将该字段重复几次
        String[] excelHeader0 = {"站点名称", "时间", "排名", "综合指数", "去年综合指数", "同比增幅(%)", "最大单项 指数", "最大单项 污染物", "PM2.5 浓度", "PM2.5 单项指数", "PM10 浓度", "PM10 单项指数", "O3 90PER 浓度", "O3 90PER 单项指数", "\n" +
                "NO2 浓度", "NO2 单项指数", "SO2 浓度", "SO2 单项指数", "CO 95PER 浓度", "CO 95PER 单项指数"};
        // “0,2,0,0” ===> “起始行，截止行，起始列，截止列”
        String[] headnum0 = {"0,0,0,0", // 第0列跨2行
                "0,0,1,1",
                "0,0,2,2", // 第1列跨2行
                "0,0,3,3", // 第2-13列跨1行
                "0,0,4,4", // 第14列跨3行
                "0,0,5,5", // 第15列跨3行
                "0,0,6,6", // 第16列跨3行
                "0,0,7,7", "0,0,8,8"};
     /*   // 第二行表头字段，其中的空的双引号是为了补全表格边框
        String[] excelHeader1 = {"优天数", "良天数", "轻度污染天数", "中度污染天数", "重度污染天数", "PM2.5", "PM10", "O3", "NO2", "SO2", "CO"};
        // 合并单元格
        String[] headnum1 = { "1,1,2,2", "1,1,3,3", "1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7", "1,1,8,8",
                "1,1,9,9", "1,1,10,10", "1,1,11,11", "1,1,12,12"};
*/
        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet("空气综合指数统计");

        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成并设置另一个样式style2
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成另一种字体2
        HSSFFont font2 = wb.createFont();
        // 设置字体
        font2.setFontName("微软雅黑");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 12);
        // 字体加粗
        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式2中引用这种字体
        style2.setFont(font2);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader0.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader0[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
      /*
        // 动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }*/
        sheet.setColumnWidth(0, 25 * 256);
        sheet.setColumnWidth(1, 25 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        // 这个至此只是输出了表头，对于表格内容应当遍历list，循环填充每一个单元格
        for (int i = 0; i < list.size(); i++) {
            int k = 0;
            RankVo qualityCategory = list.get(i);
            row = sheet.createRow(i + 1);

            HSSFCell cell0 = row.createCell(k++);
            if (qualityCategory.getSiteName() != null) {
                cell0.setCellValue(qualityCategory.getSiteName());
            }
            HSSFCell cell1 = row.createCell(k++);
            if (qualityCategory.getTime() != null) {
                String value = qualityCategory.getTime();
                String times = "";
                if ("1".equals(s)) {
                    times = value.substring(0, 7);
                } else if ("2".equals(s)) {
                    int as = Integer.parseInt(value.substring(0, 4));
                    int as2 = Integer.parseInt(value.substring(5, 7));
                    if (1 <= as2 && as2 < 4) {
                        times = as + "年第一季度";
                    } else if (4 <= as2 && as2 < 7) {
                        times = as + "年第二季度";
                    } else if (7 <= as2 && as2 < 10) {
                        times = as + "年第三季度";
                    } else {
                        times = as + "年第四季度";
                    }

                } else if ("3".equals(s)) {

                    times = value.substring(0, 4) + "年";

                } else {
                    times = statr + "至" + end;
                }
                cell1.setCellValue(times);
            }
            HSSFCell cell2 = row.createCell(k++);
            if (qualityCategory.getRanking() != null) {
                cell2.setCellValue(qualityCategory.getRanking());
            }
            HSSFCell cell3 = row.createCell(k++);
            if (qualityCategory.getZhzs() != null) {
                cell3.setCellValue(qualityCategory.getZhzs());
            }
            HSSFCell cell4 = row.createCell(k++);
            if (qualityCategory.getOdlzhzs() != null) {
                cell4.setCellValue(qualityCategory.getOdlzhzs());
            }
            HSSFCell cell5 = row.createCell(k++);
            if (qualityCategory.getNewodlzhzs() != null) {
                cell5.setCellValue(qualityCategory.getNewodlzhzs());
            }
            HSSFCell cell6 = row.createCell(k++);
            if (qualityCategory.getBigOne() != null) {
                cell6.setCellValue(qualityCategory.getBigOne() + "%");
            }
            HSSFCell cell7 = row.createCell(k++);
            if (qualityCategory.getBigPre() != null) {
                cell7.setCellValue(qualityCategory.getBigPre());
            }
            HSSFCell cell8 = row.createCell(k++);
            if (qualityCategory.getPm25() != null) {
                cell8.setCellValue(qualityCategory.getPm25());
            }
            HSSFCell cell9 = row.createCell(k++);
            if (qualityCategory.getPm25Zh() != null) {
                cell9.setCellValue(qualityCategory.getPm25Zh());
            }
            HSSFCell cell10 = row.createCell(k++);
            if (qualityCategory.getPm10() != null) {
                cell10.setCellValue(qualityCategory.getPm10());
            }
            HSSFCell cell11 = row.createCell(k++);
            if (qualityCategory.getPm10Zh() != null) {
                cell11.setCellValue(qualityCategory.getPm10Zh());
            }
            HSSFCell cell12 = row.createCell(k++);
            if (qualityCategory.getO3() != null) {
                cell12.setCellValue(qualityCategory.getO3());
            }
            HSSFCell cell13 = row.createCell(k++);
            if (qualityCategory.getO3Zh() != null) {
                cell13.setCellValue(qualityCategory.getO3Zh() + "%");
            }
            HSSFCell cell14 = row.createCell(k++);
            if (qualityCategory.getNo2() != null) {
                cell14.setCellValue(qualityCategory.getNo2());
            }
            HSSFCell cell15 = row.createCell(k++);
            if (qualityCategory.getNo2Zh() != null) {
                cell15.setCellValue(qualityCategory.getNo2Zh());
            }
            HSSFCell cell16 = row.createCell(k++);
            if (qualityCategory.getSo2() != null) {
                cell16.setCellValue(qualityCategory.getSo2());
            }

            HSSFCell cell17 = row.createCell(k++);
            if (qualityCategory.getSo2Zh() != null) {
                cell17.setCellValue(qualityCategory.getSo2Zh());
            }

            HSSFCell cell18 = row.createCell(k++);
            if (qualityCategory.getCo() != null) {
                cell18.setCellValue(qualityCategory.getCo());
            }

            HSSFCell cell19 = row.createCell(k++);
            if (qualityCategory.getCoZh() != null) {
                cell19.setCellValue(qualityCategory.getCoZh() + "%");
            }
        }
        return wb;
    }

    public HSSFWorkbook exportExcllentCategory8(Map<String, Object> dataSet) {

        List<String> time = (List<String>) dataSet.get("time");
        List<ConcentrationVO> data = (List<ConcentrationVO>) dataSet.get("data");
        String[] excelHeader0 = time.stream().toArray(String[]::new);
        String[] headnum0 = new String[excelHeader0.length + 1];
        for (int i = 0; i < headnum0.length; i++) {
            headnum0[i] = "0,0," + i + "," + i;
        }

        String[] excelHeader00 = {"综合指数(变化趋势)"};
        String[] headnum00 = {"0,0,0," + (headnum0.length - 1)};

        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet();
        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成另一种样式style，应用在数据行中
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader00.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader00[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i + 1, true);// 自动调整宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum00.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum00[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        row = sheet.createRow(1);
        HSSFCell siteName = row.createCell(0);
        siteName.setCellValue("站点名称");
        siteName.setCellStyle(style);
        // 根据字段长度自动调整列的宽度

        sheet.setColumnWidth(0, 25 * 256);
//        sheet.autoSizeColumn(1, true);
        for (int i = 0; i < excelHeader0.length; i++) {
            HSSFCell cell = row.createCell(i + 1);
            cell.setCellValue(excelHeader0[i]);
            cell.setCellStyle(style);
            // 根据字段长度自动调整列的宽度
            sheet.autoSizeColumn(i + 1, true);
        }

        for (int i = 0; i < data.size(); i++) {
            ConcentrationVO concentrationVO = data.get(i);
            row = sheet.createRow(i + 2);
            HSSFCell cell0 = row.createCell(0);
            if (concentrationVO.getSiteName() != null) {
                cell0.setCellValue(concentrationVO.getSiteName());
                cell0.setCellStyle(style2);
            }

            Map<String, Double> map = new HashMap<>(time.size());

            List lists1 = concentrationVO.getLists();
            List<RankVo> lists = concentrationVO.getLists();
            for (RankVo hourStatement : lists) {

                map.put(hourStatement.getTime(), hourStatement.getValue() == null ? 0d : hourStatement.getValue());
            }

         /*    if (type == 1) {
                List<PzDailyStatement> lists = concentrationVO.getLists();
                for (PzDailyStatement dailyStatement : lists) {
                    String format = DateUtils.format(dailyStatement.getsDatetime(), "yyyy-MM-dd");
                    map.put(format, dailyStatement.getValue());
                }
            }*/

            for (int j = 0; j < time.size(); j++) {
                HSSFCell cell = row.createCell(j + 1);
                String time1 = time.get(j);
                if (map.containsKey(time1)) {
                    cell.setCellValue(map.get(time1));
                    cell.setCellStyle(style2);
                } else {
                    cell.setCellValue("");
                    cell.setCellStyle(style2);
                }
            }
        }

        return wb;
    }

    public HSSFWorkbook exportExcllentCategory4(List<QualityCategory> list, String s, String statr, String end) {
        // 声明String数组，并初始化元素（表头名称）
        // 第一行表头字段，合并单元格时字段跨几列就将该字段重复几次
        String[] excelHeader0 = {"站点名称", "时间", "优良天数(天)", "污染天数(天)", "有效天数(天)", "总天数(天)", "本区间优良天数比例(%)", "上年同期优良天数比例(%)", "本区间较上年同期(%)"};
        // “0,2,0,0” ===> “起始行，截止行，起始列，截止列”
        String[] headnum0 = {"0,0,0,0", // 第0列跨2行
                "0,0,1,1",
                "0,0,2,2", // 第1列跨2行
                "0,0,3,3", // 第2-13列跨1行
                "0,0,4,4", // 第14列跨3行
                "0,0,5,5", // 第15列跨3行
                "0,0,6,6", // 第16列跨3行
                "0,0,7,7", "0,0,8,8"};
     /*   // 第二行表头字段，其中的空的双引号是为了补全表格边框
        String[] excelHeader1 = {"优天数", "良天数", "轻度污染天数", "中度污染天数", "重度污染天数", "PM2.5", "PM10", "O3", "NO2", "SO2", "CO"};
        // 合并单元格
        String[] headnum1 = { "1,1,2,2", "1,1,3,3", "1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7", "1,1,8,8",
                "1,1,9,9", "1,1,10,10", "1,1,11,11", "1,1,12,12"};
*/
        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet("空气质量等级分布变化");

        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成并设置另一个样式style2
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成另一种字体2
        HSSFFont font2 = wb.createFont();
        // 设置字体
        font2.setFontName("微软雅黑");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 12);
        // 字体加粗
        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式2中引用这种字体
        style2.setFont(font2);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader0.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader0[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        sheet.setColumnWidth(0, 25 * 255);
        sheet.setColumnWidth(1, 20 * 255);
      /*
        // 动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }*/

        // 这个至此只是输出了表头，对于表格内容应当遍历list，循环填充每一个单元格
        for (int i = 0; i < list.size(); i++) {
            QualityCategory qualityCategory = list.get(i);
            row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            if (qualityCategory.getSiteName() != null) {
                cell0.setCellValue(qualityCategory.getSiteName());
            }
            HSSFCell cell1 = row.createCell(1);
            if (qualityCategory.getTimes() != null) {
                String value = qualityCategory.getTimes();
                String times = "";
                if ("1".equals(s)) {
                    times = value.substring(0, 7);
                } else if ("2".equals(s)) {
                    int as = Integer.parseInt(value.substring(0, 4));
                    int as2 = Integer.parseInt(value.substring(5, 7));
                    if (1 <= as2 && as2 < 4) {
                        times = as + "年第一季度";
                    } else if (4 <= as2 && as2 < 7) {
                        times = as + "年第二季度";
                    } else if (7 <= as2 && as2 < 10) {
                        times = as + "年第三季度";
                    } else {
                        times = as + "年第四季度";
                    }

                } else if ("3".equals(s)) {

                    times = value.substring(0, 4) + "年";

                } else {
                    times = statr + "至" + end;
                }
                cell1.setCellValue(times);
            }
            HSSFCell cell2 = row.createCell(2);
            if (qualityCategory.getExcellentAndGood() != null) {
                cell2.setCellValue(qualityCategory.getExcellentAndGood());
            }
            HSSFCell cell3 = row.createCell(3);
            if (qualityCategory.getExcellentAndGood() != null) {
                cell3.setCellValue(qualityCategory.getExcellentAndGood());
            }
            HSSFCell cell4 = row.createCell(4);
            if (qualityCategory.getValidDays() != null) {
                cell4.setCellValue(qualityCategory.getValidDays());
            }
            HSSFCell cell5 = row.createCell(5);
            if (qualityCategory.getSumDays() != null) {
                cell5.setCellValue(qualityCategory.getSumDays());
            }
            HSSFCell cell6 = row.createCell(6);
            if (qualityCategory.getExcellentAndGoodRatio() != null) {
                cell6.setCellValue(qualityCategory.getExcellentAndGoodRatio() + "%");
            }
            HSSFCell cell7 = row.createCell(7);
            if (qualityCategory.getOldRatio() != null) {
                cell7.setCellValue(qualityCategory.getOldRatio());
            }
            HSSFCell cell8 = row.createCell(8);
            if (qualityCategory.getNewoldRatio() != null) {
                cell8.setCellValue(qualityCategory.getNewoldRatio());
            }
        }
        return wb;
    }

    public HSSFWorkbook exportAverageCompare(List<AverageCompare> list, String dateRange) {

        String[] excelHeader00 = {dateRange + "全市六项污染物的平均浓度同比"};
        String[] excelHeader0;
        String[] excelHeader2 = {"区域", "站点名称(类型)", " PM₂.₅（μg/m³）", "", "", "PM₁₀（μg/m³）", "", "", "O₃-8h（μg/m³）", "", "", "NO₂（μg/m³）", "", "", "SO₂（μg/m³）", "", "",
                "CO（mg/m³）", "", ""};
        excelHeader0 = excelHeader2;
        String[] headnum00;
        String[] headnum0;
        String[] headnum1 = {"1,2,0,0", // 第0列跨2行
                "1,2,1,1",
                "1,1,2,4", // 第1列跨2行
                "1,1,5,7", // 第2-13列跨1行
                "1,1,8,10", // 第14列跨3行
                "1,1,11,13", // 第15列跨3行
                "1,1,14,16", // 第16列跨3行
                "1,1,17,19"};
        headnum0 = headnum1;
        String[] headnum001 = {"0,0,0,19"};
        headnum00 = headnum001;

        // 第二行表头字段，其中的空的双引号是为了补全表格边框
        String[] excelHeader1;
        String[] excelHeader3 = {"日均浓度", "上年同期浓度", "同比(%)", "日均值浓度", "上年同期浓度", "同比(%)", "日均值浓度", "上年同期浓度", "同比(%)",
                "日均值浓度", "上年同期浓度", "同比(%)", "日均值浓度", "上年同期浓度", "同比(%)", "日均值浓度", "上年同期浓度", "同比(%)"};
        excelHeader1 = excelHeader3;

        String[] headnum3 = {"2,2,2,2", "2,2,3,3", "2,2,4,4", "2,2,5,5", "2,2,6,6", "2,2,7,7", "2,2,8,8",
                "2,2,9,9", "2,2,10,10", "2,2,11,11", "2,2,12,12", "2,2,13,13", "2,2,14,14", "2,2,15,15", "2,2,16,16",
                "2,2,17,17", "2,2,18,18", "2,2,19,19"};
        headnum1 = headnum3;

        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet("全市六项污染物的平均浓度同比");

        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成并设置另一个样式style2
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一种字体2
        HSSFFont font2 = wb.createFont();
        // 设置字体
        font2.setFontName("微软雅黑");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 12);
        // 字体加粗
        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式2中引用这种字体
        style2.setFont(font2);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader00.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader00[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum00.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum00[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        row = sheet.createRow(1);
        for (int i = 0; i < excelHeader0.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader0[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        // 第二行表头
        row = sheet.createRow(2);
        for (int i = 0; i < excelHeader1.length; i++) {
            HSSFCell cell = row.createCell(i + 2);
            cell.setCellValue(excelHeader1[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i + 1, true);// 自动调整宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }

        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        for (int i = 2; i < 20; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        for (int i = 0; i < list.size(); i++) {
            AverageCompare averageCompare = list.get(i);
            row = sheet.createRow(i + 3);
            HSSFCell cell0 = row.createCell(0);
            if (averageCompare.getAreaName() != null) {
                cell0.setCellValue(averageCompare.getAreaName());
                cell0.setCellStyle(style2);
            }
            HSSFCell cell1 = row.createCell(1);
            if (averageCompare.getSiteName() != null) {
                cell1.setCellValue(averageCompare.getSiteName());
                cell1.setCellStyle(style2);
            }
            HSSFCell cell2 = row.createCell(2);
            if (averageCompare.getPm25Avg() != null) {
                cell2.setCellValue(averageCompare.getPm25Avg().doubleValue());
                cell2.setCellStyle(style2);
            }
            HSSFCell cell3 = row.createCell(3);
            if (averageCompare.getPm25PreYearAvg() != null) {
                cell3.setCellValue(averageCompare.getPm25PreYearAvg());
                cell3.setCellStyle(style2);
            }
            HSSFCell cell4 = row.createCell(4);
            if (averageCompare.getPm25Avg() != null && averageCompare.getPm25PreYearAvg() != null) {
                if (averageCompare.getPm25PreYearAvg().equals(0.0)) {
                    cell4.setCellValue("-");
                } else {
                    Double basis = Math.round((averageCompare.getPm25Avg() / averageCompare.getPm25PreYearAvg() - 1) * 1000) / 10d;
                    cell4.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                }
                cell4.setCellStyle(style2);
            }
            HSSFCell cell5 = row.createCell(5);
            if (averageCompare.getPm10Avg() != null) {
                cell5.setCellValue(averageCompare.getPm10Avg().doubleValue());
                cell5.setCellStyle(style2);
            }
            HSSFCell cell6 = row.createCell(6);
            if (averageCompare.getPm10PreYearAvg() != null) {
                cell6.setCellValue(averageCompare.getPm10PreYearAvg());
                cell6.setCellStyle(style2);
            }
            HSSFCell cell7 = row.createCell(7);
            if (averageCompare.getPm10Avg() != null && averageCompare.getPm10PreYearAvg() != null) {
                if (averageCompare.getPm10PreYearAvg().equals(0.0)) {
                    cell7.setCellValue("-");
                } else {
                    Double basis = Math.round((averageCompare.getPm10Avg() / averageCompare.getPm10PreYearAvg() - 1) * 1000) / 10d;
                    cell7.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                }
                cell7.setCellStyle(style2);
            }
            HSSFCell cell8 = row.createCell(8);
            if (averageCompare.getO3EightAvg() != null) {
                cell8.setCellValue(averageCompare.getO3EightAvg().doubleValue());
                cell8.setCellStyle(style2);
            }
            HSSFCell cell9 = row.createCell(9);
            if (averageCompare.getO3EightPreYearAvg() != null) {
                cell9.setCellValue(averageCompare.getO3EightPreYearAvg());
                cell9.setCellStyle(style2);
            }
            HSSFCell cell10 = row.createCell(10);
            if (averageCompare.getO3EightAvg() != null && averageCompare.getO3EightPreYearAvg() != null) {
                if (averageCompare.getO3EightPreYearAvg().equals(0.0)) {
                    cell10.setCellValue("-");
                } else {
                    Double basis = Math.round((averageCompare.getO3EightAvg() / averageCompare.getO3EightPreYearAvg() - 1) * 1000) / 10d;
                    cell10.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                }
                cell10.setCellStyle(style2);
            }
            HSSFCell cell11 = row.createCell(11);
            if (averageCompare.getNo2Avg() != null) {
                cell11.setCellValue(averageCompare.getNo2Avg().doubleValue());
                cell11.setCellStyle(style2);
            }
            HSSFCell cell12 = row.createCell(12);
            if (averageCompare.getNo2PreYearAvg() != null) {
                cell12.setCellValue(averageCompare.getNo2PreYearAvg());
                cell12.setCellStyle(style2);
            }
            HSSFCell cell13 = row.createCell(13);
            if (averageCompare.getNo2Avg() != null && averageCompare.getNo2PreYearAvg() != null) {
                if (averageCompare.getNo2PreYearAvg().equals(0.0)) {
                    cell13.setCellValue("-");
                } else {
                    Double basis = Math.round((averageCompare.getNo2Avg() / averageCompare.getNo2PreYearAvg() - 1) * 1000) / 10d;
                    cell13.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                }
                cell13.setCellStyle(style2);
            }

            HSSFCell cell14 = row.createCell(14);
            if (averageCompare.getSo2Avg() != null) {
                cell14.setCellValue(averageCompare.getSo2Avg().doubleValue());
                cell14.setCellStyle(style2);
            }
            HSSFCell cell15 = row.createCell(15);
            if (averageCompare.getSo2PreYearAvg() != null) {
                cell15.setCellValue(averageCompare.getSo2PreYearAvg());
                cell15.setCellStyle(style2);
            }
            HSSFCell cell16 = row.createCell(16);
            if (averageCompare.getSo2Avg() != null && averageCompare.getSo2PreYearAvg() != null) {
                if (averageCompare.getSo2PreYearAvg().equals(0.0)) {
                    cell16.setCellValue("-");
                } else {
                    Double basis = Math.round((averageCompare.getSo2Avg() / averageCompare.getSo2PreYearAvg() - 1) * 1000) / 10d;
                    cell16.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                }
                cell16.setCellStyle(style2);
            }

            HSSFCell cell17 = row.createCell(17);
            if (averageCompare.getCoAvg() != null) {
                cell17.setCellValue(averageCompare.getCoAvg().doubleValue());
                cell17.setCellStyle(style2);
            }
            HSSFCell cell18 = row.createCell(18);
            if (averageCompare.getCoPreYearAvg() != null) {
                cell18.setCellValue(averageCompare.getCoPreYearAvg());
                cell18.setCellStyle(style2);
            }
            HSSFCell cell19 = row.createCell(19);
            if (averageCompare.getCoAvg() != null && averageCompare.getCoPreYearAvg() != null) {
                if (averageCompare.getCoPreYearAvg().equals(0.0)) {
                    cell19.setCellValue("-");
                } else {
                    Double basis = Math.round((averageCompare.getCoAvg() / averageCompare.getCoPreYearAvg() - 1) * 1000) / 10d;
                    cell19.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                }
                cell19.setCellStyle(style2);
            }
        }
        return wb;
    }

    /**
     * 大气综合分析环比
     *
     * @param list
     * @param dateRange
     * @return
     */
    public HSSFWorkbook exportChainCompare(List<AverageCompare> list, String dateRange) {
        String[] excelHeader00 = {dateRange + "全市六项污染物的平均浓度环比"};
        String[] excelHeader2 = {"区域", "站点名称(类型)", "PM₂.₅（μg/m³）", "", "", "PM10（μg/m³）", "", "", "O3-8h（μg/m³）", "", "", "NO2（μg/m³）", "", "", "SO2（ug/m³）", "", "", "CO（mg/m³）", "", ""};
        // 声明String数组，并初始化元素（表头名称）
        // 第一行表头字段，合并单元格时字段跨几列就将该字段重复几次
//        String[] excelHeader0 = {"站点名称(站点类型)", "SO2（μg/m³）", "", "", "NO2（μg/m³）", "", "", "PM10（μg/m³）", "", "",
//                "PM2.5（μg/m³）", "", "", "CO（mg/m³）", "", "", "O3-8h（μg/m³）", "", ""};
        //String[] excelHeader0 = {"站点名称(站点类型)", "PM10（μg/m³）", "", "", "PM2.5（μg/m³）", "", "", "O3-8h（μg/m³）", "", ""};
        // “0,2,0,0” ===> “起始行，截止行，起始列，截止列”
        String[] headnum2 = {"1,2,0,0", // 第0列跨2行
                "1,2,1,1",
                "1,1,2,4", // 第1列跨2行
                "1,1,5,7", // 第2-13列跨1行
                "1,1,8,10", // 第14列跨3行
                "1,1,11,13", // 第15列跨3行
                "1,1,14,16", // 第16列跨3行
                "1,1,17,19"};
        String[] headnum00 = {"0,0,0,19"};
        String[] excelHeader3 = {"日均浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)",
                "日均值浓度", "上同期浓月度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)"};
        // 第二行表头字段，其中的空的双引号是为了补全表格边框
//        String[] excelHeader1 = {"日均浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)",
//                "日均值浓度", "上同期浓月度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)"};
        //       String[] excelHeader1 = {"日均浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)", "日均值浓度", "上月同期浓度", "同比(%)",};
        // 合并单元格
        String[] headnum3 = {"2,2,2,2", "2,2,3,3", "2,2,4,4", "2,2,5,5", "2,2,6,6", "2,2,7,7", "2,2,8,8",
                "2,2,9,9", "2,2,10,10", "2,2,11,11", "2,2,12,12", "2,2,13,13", "2,2,14,14", "2,2,15,15", "2,2,16,16",
                "2,2,17,17", "2,2,18,18", "2,2,19,19"};
//        String[] headnum1 = {"1,1,1,1", "1,1,2,2", "1,1,3,3", "1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7", "1,1,8,8",
//                "1,1,9,9", "1,1,10,10", "1,1,11,11", "1,1,12,12", "1,1,13,13", "1,1,14,14", "1,1,15,15", "1,1,16,16",
//                "1,1,17,17", "1,1,18,18"};

/*   String[] headnum1 = {"1,1,1,1", "1,1,2,2", "1,1,3,3", "1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7", "1,1,8,8",
                "1,1,9,9"};*/
        // 声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet("全市六项污染物的平均浓度同比");

        // 生成一种样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成一种字体
        HSSFFont font = wb.createFont();
        // 设置字体
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式中引用这种字体
        style.setFont(font);

        // 生成并设置另一个样式style2
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一种字体2
        HSSFFont font2 = wb.createFont();
        // 设置字体
        font2.setFontName("微软雅黑");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 12);
        // 字体加粗
        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 在样式2中引用这种字体
        style2.setFont(font2);

        // 生成表格的第0行表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader00.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader00[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum00.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum00[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        row = sheet.createRow(1);
        for (int i = 0; i < excelHeader2.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader2[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i, true);// 根据字段长度自动调整列的宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum2.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum2[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }
        // 第二行表头
        row = sheet.createRow(2);
        for (int i = 0; i < excelHeader3.length; i++) {
            HSSFCell cell = row.createCell(i + 2);
            cell.setCellValue(excelHeader3[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i + 1, true);// 自动调整宽度
        }
        // 动态合并单元格
        for (int i = 0; i < headnum3.length; i++) {
            sheet.autoSizeColumn(i, true);
            String[] temp = headnum3[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            // sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
            // startcol, overcol));
            if (!(startrow.equals(overrow) && startcol.equals(overcol))) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
            }
        }

        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(0, 20 * 256);
        for (int i = 2; i < 20; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        for (int i = 0; i < list.size(); i++) {
            AverageCompare averageCompare = list.get(i);
            row = sheet.createRow(i + 3);
            HSSFCell cell0 = row.createCell(0);
            if (averageCompare.getAreaName() != null) {
                cell0.setCellValue(averageCompare.getAreaName());
                cell0.setCellStyle(style2);
            }
            HSSFCell cell1 = row.createCell(1);
            if (averageCompare.getSiteName() != null) {
                cell1.setCellValue(averageCompare.getSiteName());
                cell1.setCellStyle(style2);
            }
            HSSFCell cell2 = row.createCell(2);
            if (averageCompare.getPm25Avg() != null) {
                cell2.setCellValue(averageCompare.getPm25Avg().doubleValue());
                cell2.setCellStyle(style2);
            }
            HSSFCell cell3 = row.createCell(3);
            if (averageCompare.getPm25PreYearAvg() != null) {
                cell3.setCellValue(averageCompare.getPm25PreYearAvg());
                cell3.setCellStyle(style2);
            }
            HSSFCell cell4 = row.createCell(4);
            if (averageCompare.getPm25Avg() != null && averageCompare.getPm25PreYearAvg() != null) {
                Double basis = Math.round((averageCompare.getPm25Avg() / averageCompare.getPm25PreYearAvg() - 1) * 1000)
                        / 10d;
                cell4.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                cell4.setCellStyle(style2);
            }
            HSSFCell cell5 = row.createCell(5);
            if (averageCompare.getPm10Avg() != null) {
                cell5.setCellValue(averageCompare.getPm10Avg().doubleValue());
                cell5.setCellStyle(style2);
            }
            HSSFCell cell6 = row.createCell(6);
            if (averageCompare.getPm10PreYearAvg() != null) {
                cell6.setCellValue(averageCompare.getPm10PreYearAvg());
                cell6.setCellStyle(style2);
            }
            HSSFCell cell7 = row.createCell(7);
            if (averageCompare.getPm10Avg() != null && averageCompare.getPm10PreYearAvg() != null) {
                Double basis = Math.round((averageCompare.getPm10Avg() / averageCompare.getPm10PreYearAvg() - 1) * 1000)
                        / 10d;
                cell7.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                cell7.setCellStyle(style2);
            }
            HSSFCell cell8 = row.createCell(8);
            if (averageCompare.getO3EightAvg() != null) {
                cell8.setCellValue(averageCompare.getO3EightAvg().doubleValue());
                cell8.setCellStyle(style2);
            }
            HSSFCell cell9 = row.createCell(9);
            if (averageCompare.getO3EightPreYearAvg() != null) {
                cell9.setCellValue(averageCompare.getO3EightPreYearAvg());
                cell9.setCellStyle(style2);
            }
            HSSFCell cell10 = row.createCell(10);
            if (averageCompare.getO3EightAvg() != null && averageCompare.getO3EightPreYearAvg() != null) {
                Double basis = Math.round(
                        (averageCompare.getO3EightAvg() / averageCompare.getO3EightPreYearAvg() - 1) * 1000) / 10d;
                cell10.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                cell10.setCellStyle(style2);
            }
            HSSFCell cell11 = row.createCell(11);
            if (averageCompare.getNo2Avg() != null) {
                cell11.setCellValue(averageCompare.getNo2Avg().doubleValue());
                cell11.setCellStyle(style2);
            }
            HSSFCell cell12 = row.createCell(12);
            if (averageCompare.getNo2PreYearAvg() != null) {
                cell12.setCellValue(averageCompare.getNo2PreYearAvg());
                cell12.setCellStyle(style2);
            }
            HSSFCell cell13 = row.createCell(13);
            if (averageCompare.getNo2Avg() != null && averageCompare.getNo2PreYearAvg() != null) {
                Double basis = Math.round((averageCompare.getNo2Avg() / averageCompare.getNo2PreYearAvg() - 1) * 1000)
                        / 10d;
                cell13.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                cell13.setCellStyle(style2);
            }

            HSSFCell cell14 = row.createCell(14);
            if (averageCompare.getSo2Avg() != null) {
                cell14.setCellValue(averageCompare.getSo2Avg().doubleValue());
                cell14.setCellStyle(style2);
            }
            HSSFCell cell15 = row.createCell(15);
            if (averageCompare.getSo2PreYearAvg() != null) {
                cell15.setCellValue(averageCompare.getSo2PreYearAvg());
                cell15.setCellStyle(style2);
            }
            HSSFCell cell16 = row.createCell(16);
            if (averageCompare.getSo2Avg() != null && averageCompare.getSo2PreYearAvg() != null) {
                Double basis = Math.round((averageCompare.getSo2Avg() / averageCompare.getSo2PreYearAvg() - 1) * 1000)
                        / 10d;
                cell16.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                cell16.setCellStyle(style2);
            }
            HSSFCell cell17 = row.createCell(17);
            if (averageCompare.getCoAvg() != null) {
                cell17.setCellValue(averageCompare.getCoAvg().doubleValue());
                cell17.setCellStyle(style2);
            }
            HSSFCell cell18 = row.createCell(18);
            if (averageCompare.getCoPreYearAvg() != null) {
                cell18.setCellValue(averageCompare.getCoPreYearAvg());
                cell18.setCellStyle(style2);
            }
            HSSFCell cell19 = row.createCell(19);
            if (averageCompare.getCoAvg() != null && averageCompare.getCoPreYearAvg() != null) {
                Double basis = Math.round((averageCompare.getCoAvg() / averageCompare.getCoPreYearAvg() - 1) * 1000)
                        / 10d;
                cell19.setCellValue(basis <= 0 ? basis + "%" : "+" + basis + "%");
                cell19.setCellStyle(style2);
            }

        }
        return wb;
    }

}