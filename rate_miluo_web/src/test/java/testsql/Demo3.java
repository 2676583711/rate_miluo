package testsql;

import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.Application;
import com.rate.web.statement.dao.PolluteDao;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.service.PolluteService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @Author chenshixue
 * @Date 2019/11/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo3 {

    @Autowired
    PolluteDao polluteDao;
    @Autowired
    PolluteService polluteService;

//    @Test
    public void test() {
        List<String> stringList = polluteDao.findPolluteData();

        List<PolluteRealtime> list = new ArrayList<>();

        for (String aa : stringList) {

            String mn = aa.substring(aa.indexOf("MN=")).split(";")[0].trim().split("=")[1].trim();
            String validData = aa.split("&&")[1];

            Date recordingTime = null;
            String tableName = null;
            // 获取时间
            if(validData.contains("DataTime=")){
                String dataTime = validData.substring(validData.indexOf("DataTime=")).split(";")[0].trim().split("=")[1].trim();
                // 判断时间是否为空，不为空则格式化
                if(StringUtils.isNotBlank(dataTime)){
                    if(dataTime.contains("-")){
                        recordingTime = DateUtils.format(dataTime, "yyyy-MM-dd HH:mm:ss");
//                        tableName += TableSelUtil.getTableSignOfMonth(dataTime, "yyyy-MM-dd HH:mm:ss");
                    }else{
                        recordingTime = DateUtils.format(dataTime, "yyyyMMddHHmmss");
//                        tableName += TableSelUtil.getTableSignOfMonth(dataTime, "yyyyMMddHHmmss");
                    }
                }else{
                    recordingTime = new Date();
                }
            }
            String[] split = validData.split(";|,"); // 拿到所有数据，分割成单节点
            Map<String, String> mapReal = new HashMap<>(); // 存放实时数据
            Map<String, String> mapZs = new HashMap<>();   // 存放折算后的数据

            for (int i = 0; i < split.length; i++) {
                // 取出所有包含-Rtd的节点
                if (split[i].contains("-Rtd=")) {
                    String sign = split[i].split("-Rtd=")[0];
                    String value = split[i].split("-Rtd=")[1];
                    if (value != null) {
                        mapReal.put(sign, value);
                    }
                    continue;
                }
                // 取出所有包含-ZsRtd的节点
                if (split[i].contains("-ZsRtd=")) {
                    String sign = split[i].split("-ZsRtd=")[0];
                    String value = split[i].split("-ZsRtd=")[1];
                    if (value != null) {
                        mapZs.put(sign, value);
                    }
                    continue;
                }
            }
            if (mapReal.size() > 0) {
                PolluteRealtime real = new PolluteRealtime();
                real.setId(IdGen.uuid());
                real.setEqumentId(mn);
                real.setDataType(1); // 实时数据
//                real.setRecordingTime(recordingTime);
                real = getPolluteData(mapReal, real);
                list.add(real);
                // 插入分钟表
//                polluteDao.insertPollutantEntity(real, tableName);
//                int row = miluoPollutantRealtimeService.countPolluteFromDesktop(mn);
//                if (row > 0) {
//                    // 更新最新数据表
//                    miluoPollutantRealtimeService.updatePolluteDesktop(mn, real);
//                } else {
//                    // 插入最新数据表
//                    miluoPollutantRealtimeService.insertDesktopEntity(real);
//                }
            }
            if (mapZs.size() > 0) {
                PolluteRealtime zs = new PolluteRealtime();
                zs.setId(IdGen.uuid());
                zs.setEqumentId(mn);
                zs.setDataType(0); // 折算数据
//                zs.setRecordingTime(recordingTime);
                zs = getPolluteData(mapZs, zs);
                list.add(zs);
//                miluoPollutantRealtimeService.insertPollutantEntity(zs, tableName);
            }
        }
//        for (PolluteRealtime real : list) {
//            polluteDao.insertPollutantEntity(real);
//        }
//        polluteService.insert(list);
    }
    private PolluteRealtime getPolluteData(Map<String, String> mapReal, PolluteRealtime entity) {
        for(String key : mapReal.keySet()){
            if (key.equalsIgnoreCase("01")) {
                entity.setDust(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("S01")) {
                entity.setO2(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("02")) {
                entity.setSo2(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("B02")) {
                entity.setExhaust(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("S03")) {
                entity.setTemp(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("03")) {
                entity.setNox(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("S02")) {
                entity.setFlowVelocity(Double.parseDouble(mapReal.get(key)));
            }
            if (key.equalsIgnoreCase("S08")) {
                entity.setPress(Double.parseDouble(mapReal.get(key)));
            }
        }
        return entity;
    }
}
