package com.rate.web.watercalcute.service.impl;

import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.config.CodeCache;
import com.rate.web.watercalcute.dao.WaterCalauteSortDao;
import com.rate.web.watercalcute.entity.WaterCalauteSort;
import com.rate.web.watercalcute.service.WaterCalauteSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: rate_hbsg_water
 * @ClassName: DataReportServiceImpl
 * @description:
 * @author: chenh
 * @create: 2020-04-02 14:50
 **/
@Service
public class WaterCalauteSortServiceImpl implements WaterCalauteSortService {

    @Autowired
    private WaterCalauteSortDao waterCalauteSortDao;

    /** @author chenh
     * @Description 通过因子和因子值查询水质类别
     * @date 2020/4/13 14:18
     * @param paramName, dataValue
     * @return java.lang.Integer
     */
    @Override
    public Integer waterSort(String paramName, Double dataValue) {
        Map<String, WaterCalauteSort> waterCalauteSortMap = CodeCache.codeMap;
        if (waterCalauteSortMap.isEmpty()) {
            Integer wp = waterCalauteSortDao.waterSort(paramName, dataValue);
            return wp;
        } else {
            WaterCalauteSort waterCalauteSort = waterCalauteSortMap.get(paramName);
            if (waterCalauteSort == null || waterCalauteSort.getType1() == null || waterCalauteSort.getType2() == null
                    || waterCalauteSort.getType3() == null || waterCalauteSort.getType4() == null
                    || waterCalauteSort.getType5() == null) {
                return null;
            }
            if (waterCalauteSort.getSymbol().equals("<=")) {
                if (dataValue <= waterCalauteSort.getType1() && dataValue > 0) {
                    return 1;
                } else if (dataValue > waterCalauteSort.getType1() && dataValue <= waterCalauteSort.getType2()) {
                    return 2;
                } else if (dataValue > waterCalauteSort.getType2() && dataValue <= waterCalauteSort.getType3()) {
                    return 3;
                } else if (dataValue > waterCalauteSort.getType3() && dataValue <= waterCalauteSort.getType4()) {
                    return 4;
                } else if (dataValue > waterCalauteSort.getType4() && dataValue <= waterCalauteSort.getType5()) {
                    return 5;
                } else if (dataValue > waterCalauteSort.getType5()) {
                    return 6;
                } else {
                    return 0;
                }
            } else if (waterCalauteSort.getSymbol().equals(">=")) {
                if (dataValue >= waterCalauteSort.getType1()) {
                    return 1;
                } else if (dataValue >= waterCalauteSort.getType2() && dataValue < waterCalauteSort.getType1()) {
                    return 2;
                } else if (dataValue >= waterCalauteSort.getType3() && dataValue < waterCalauteSort.getType2()) {
                    return 3;
                } else if (dataValue >= waterCalauteSort.getType4() && dataValue < waterCalauteSort.getType3()) {
                    return 4;
                } else if (dataValue >= waterCalauteSort.getType5() && dataValue < waterCalauteSort.getType4()) {
                    return 5;
                } else if (dataValue > 0 && dataValue < waterCalauteSort.getType5()) {
                    return 6;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    /** @author chenh
     * @Description 计算超标倍数
     * @date 2020/4/13
     * @param paramName, 参数名,参数值,超标指定水质类别,当前水质类别
     * @return java.lang.String
     */
    @Override
    public String getCbbs(String paramName, Double dataValue, int standBz,String currentType) {
        if (StringUtils.isBlank(currentType)) {
            return "";
        }
        String cbwz="";
        Map<String, WaterCalauteSort> waterCalauteSortMap = CodeCache.codeMap;
        if (waterCalauteSortMap.isEmpty()) {
            List<WaterCalauteSort> waterCalauteSortList = waterCalauteSortDao.all();
            for (WaterCalauteSort waterCalauteSort : waterCalauteSortList) {
                waterCalauteSortMap.put(waterCalauteSort.getDescription(),waterCalauteSort);
            }
        }
        WaterCalauteSort waterCalauteSort = waterCalauteSortMap.get(paramName);
        if(standBz == 1){
            if (waterCalauteSort.getSymbol().equals("<=")) {
                if(dataValue > waterCalauteSort.getType1()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(dataValue-waterCalauteSort.getType1())/waterCalauteSort.getType1());
                }
            }else if(waterCalauteSort.getSymbol().equals(">=")){
                if(dataValue < waterCalauteSort.getType1()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(waterCalauteSort.getType1()-dataValue)/waterCalauteSort.getType1());
                }
            }
        }else if(standBz == 2){
            if (waterCalauteSort.getSymbol().equals("<=")) {
                if(dataValue > waterCalauteSort.getType2()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(dataValue-waterCalauteSort.getType2())/waterCalauteSort.getType2());
                }
            }else if(waterCalauteSort.getSymbol().equals(">=")){
                if(dataValue < waterCalauteSort.getType2()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(waterCalauteSort.getType2()-dataValue)/waterCalauteSort.getType2());
                }
            }
        }else if(standBz == 3){
            if (waterCalauteSort.getSymbol().equals("<=")) {
                if(dataValue > waterCalauteSort.getType3()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(dataValue-waterCalauteSort.getType3())/waterCalauteSort.getType3());
                }
            }else if(waterCalauteSort.getSymbol().equals(">=")){
                if(dataValue < waterCalauteSort.getType3()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(waterCalauteSort.getType3()-dataValue)/waterCalauteSort.getType3());
                }
            }
        }else if(standBz == 4){
            if (waterCalauteSort.getSymbol().equals("<=")) {
                if(dataValue > waterCalauteSort.getType4()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(dataValue-waterCalauteSort.getType4())/waterCalauteSort.getType4());
                }
            }else if(waterCalauteSort.getSymbol().equals(">=")){
                if(dataValue < waterCalauteSort.getType4()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(waterCalauteSort.getType4()-dataValue)/waterCalauteSort.getType4());
                }
            }
        }else if(standBz == 5){
            if (waterCalauteSort.getSymbol().equals("<=")) {
                if(dataValue > waterCalauteSort.getType5()){
                    return cbwz=paramName+"("+currentType+")"+String.format("%.2f",(dataValue-waterCalauteSort.getType5())/waterCalauteSort.getType5());
                }
            }else if(waterCalauteSort.getSymbol().equals(">=")){
                if(dataValue < waterCalauteSort.getType5()){
                    return  cbwz=paramName+"("+currentType+")"+String.format("%.2f",(waterCalauteSort.getType5()-dataValue)/waterCalauteSort.getType5());
                }
            }
        }
        return null;
    }
}
