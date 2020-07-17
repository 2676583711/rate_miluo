package testsql;

import com.rate.web.site.entity.SitePower;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.beetl.sql.core.*;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.boot.system.ApplicationHome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: TestQuery</p>
 * <p>Description: 根据表名生成实体和md文件基本内容</p>
 *
 * @author chenh
 * throws Exception
 * @date 2018年12月13日
 */
public class InsertJob {
    public static void main(String[] args) throws Exception {
        long size = ObjectSizeCalculator.getObjectSize(new SitePower());
//		ObjectSizeCalculator.getEffectiveMemoryLayoutSpecification();
//        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.jdbc.Driver", "jdbc:mysql://47.101.50.57/miluo?useSSL=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull", "jadmin", "jadmin@123");


        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://218.75.181.68/miluo?useSSL=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
        String username = "root";
        String password = "mlhbj@123";
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, username, password);

//        DBStyle mysql = new OracleStyle();
        DBStyle mySqlStyle = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaulNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();

        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mySqlStyle, loader, source, nc, new Interceptor[]{new DebugInterceptor()});


//        sqlManager.genPojoCodeToConsole("miluo_vidicon_file");
//        sqlManager.genSQLTemplateToConsole("miluo_vidicon_file");

        //(date_time,site_id,param_id,data_value)
//        String path = new ApplicationHome().getDir().getPath();
//        System.out.println("-path--" + path);


        List<Integer> integerByFile = getIntegerByFile(new ApplicationHome().getDir().getPath() + "/rate_miluo_web/src/test/resources/paramsId.txt");
        List<Double> doubleByFile = getDoubleByFile(new ApplicationHome().getDir().getPath() + "/rate_miluo_web/src/test/resources/data_value.txt");

        Iterator<Integer> iterator1 = integerByFile.iterator();
        Iterator<Double> iterator2 = doubleByFile.iterator();

        ///*****************************************************
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//
        Integer setParam_id = null;
        Double setData_value = null;


        Date date = simpleDateFormat.parse("2019-10-18");
//        Integer setSite_id = 398; //白水港断面
//        Integer setSite_id = 399; //新市断面
//        Integer setSite_id = 402; //罗滨桥断面
//        Integer setSite_id = 403; //罗江三江口断面

//        Integer setSite_id = 404; //车对河赵工桥断面
//        Integer setSite_id = 400; //窑州断面
//        Integer setSite_id = 401; //兰家洞水库
//        Integer setSite_id = 410;   //向家洞水库
//        Integer setSite_id = 406; //汨罗水库
//        Integer setSite_id = 407; //友谊河
//        Integer setSite_id = 408; //罗江

//        Integer setSite_id = 405; //磊石断面
//        Integer setSite_id = 409; //汨罗江窑州
//        Integer setSite_id = 411;   //桃林河
//        Integer setSite_id = 413;   //李家河
//        Integer setSite_id = 414;   //一道撇洪渠
//        Integer setSite_id = 416;   //芭蕉水
        Integer setSite_id = 417;   //白水江
//        Integer setSite_id = 418; // 南渡
//        Integer setSite_id = 419; // 湘江汨罗段
//        Integer setSite_id = 421;   //汨罗江
//        Integer setSite_id = 422;      //湄江
//        Integer setSite_id = 423;   //芭蕉河
        ///*****************************************************
//0.3×10-3ND   0.0003
// **-//-*-/--//*/*/*
        int i = 1;
        while (iterator1.hasNext() & iterator2.hasNext()) {
            setParam_id = iterator1.next();
            setData_value = iterator2.next();

            System.out.println((i++) + "--个:::---" + setSite_id + "-----" + setParam_id + "---" + setData_value + "---" + date);

            InsertPojo insertJob = new InsertPojo();
            insertJob.setSiteId(setSite_id);
            insertJob.setDateTime(date);
            insertJob.setParamId(setParam_id);
            insertJob.setDataValue(setData_value);
            sqlManager.insert(insertJob);
        }

    }

    public static List<Integer> getIntegerByFile(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Integer> integers = new ArrayList<>();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            //ph  2，溶解氧 5，氨氮 7，总磷 9，高猛酸盐 8
            integers.add(Integer.valueOf(s));
        }
        fileReader.close();
        bufferedReader.close();
        return integers;
    }

    public static List<Double> getDoubleByFile(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Double> doubles = new ArrayList<Double>();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            //处理 多余部分 比如
            String needReplace1 = "ND";
            String needReplace2 = "×";
            try {
                doubles.add(Double.valueOf(s));
            } catch (Exception e1) {
                s = s.replaceAll(needReplace1, "");
                try {
                    doubles.add(Double.valueOf(s));
                } catch (Exception e2) {
                    String[] split = s.split(needReplace2);
                    doubles.add(Double.valueOf(split[0]) * 0.001);
                }
            }
        }
        fileReader.close();
        bufferedReader.close();
        return doubles;
    }
}


@Table(name = "miluo_water_month_data")
class InsertPojo {

    Date dateTime;
    Integer siteId;
    Integer paramId;
    Double dataValue;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Double getDataValue() {
        return dataValue;
    }

    public void setDataValue(Double dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return "InsertPojo{" +
                "dateTime=" + dateTime +
                ", siteId=" + siteId +
                ", paramId=" + paramId +
                ", dataValue=" + dataValue +
                '}';
    }
}
