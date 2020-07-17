package com.rate.web.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rate.system.rate_system.utils.IdGen;
import com.rate.web.history.dao.MiluoOriginalTreatmentPlantDao;
import com.rate.web.history.dao.MiluoRealtimeTreatmentPlantDao;
import com.rate.web.history.entity.MiluoOriginalTreatmentPlant;
import com.rate.web.history.entity.MiluoRealtimeTreatmentPlant;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *  
 *  @ClassName: ServerHandle
 *  @Description
 *  @Author  liuYong
 *  @Date  2019/3/12 14:47
 *  @version 1.00 
 */
@Component
@ChannelHandler.Sharable
public class ServerHandle extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(ServerHandle.class);

    @Autowired
    private MiluoRealtimeTreatmentPlantDao miluoRealtimeTreatmentPlantDao;
    @Autowired
    private MiluoOriginalTreatmentPlantDao miluoOriginalTreatmentPlantDao;
    /**
     * 实时数据的抓取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	      SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      /*	String aa="##0229ST=32;CN=2011;PW=123456;MN=0152316D179166;Flag=0;CP=&&DataTime=20190527110630;001-Rtd=6.899,"
    			+"001-Flag=N;027-Rtd=0.431,027-Flag=N;022-Rtd=0.004,022-Flag=N;026-Rtd=0.021,026-Flag=N;030-Rtd=0.864,030-Flag=N;029-Rtd=1.784,029-Flag=N&&95C1";*/
	      	String aa = (String) msg;
    	 	logger.info(aa);
     		if(aa.contains("ST=")){
    	        	String ST=aa.substring(aa.indexOf("ST=")+3,aa.indexOf(";CN"));
    	    	    if(ST.equalsIgnoreCase("32") && aa.contains("-Rtd")) {
    	    	    	MiluoOriginalTreatmentPlant miluoOriginalTreatmentPlant=new MiluoOriginalTreatmentPlant();
    	    	    	miluoOriginalTreatmentPlant.setId(IdGen.uuid());
    	    	    	miluoOriginalTreatmentPlant.setSiteType(ST);
    	    	    	MiluoRealtimeTreatmentPlant miluoRealtimeTreatmentPlant=new MiluoRealtimeTreatmentPlant();
    	    			miluoRealtimeTreatmentPlant.setId(IdGen.uuid());
    	    			  if(aa.contains("DataTime=")) {
    	    		        	String recordTime=aa.substring(aa.indexOf("DataTime=")+9,aa.indexOf(";001-"));
    	    		    		Date date = simpleDateFormat.parse(recordTime);
    	    		    	    miluoRealtimeTreatmentPlant.setRecordingTime(date);
    	    		    	    miluoOriginalTreatmentPlant.setRecordingTime(date);
    	    		    	    String siteDate=aa.substring(aa.indexOf("DataTime=")+24,aa.length());
    	    		    	    miluoOriginalTreatmentPlant.setSiteData(siteDate);
    	    				}
    	    			  if(aa.contains("CN=")) {
    	    		        	String cn=aa.substring(aa.indexOf("CN=")+3,aa.indexOf(";PW"));
    	    		    	    miluoOriginalTreatmentPlant.setCmdId(cn);
    	    				}
    	    			  	if(aa.contains("MN=0152316D179166")) {
    	    				  String MN=aa.substring(aa.indexOf("MN=")+3,aa.indexOf(";Flag"));
    	    				  miluoRealtimeTreatmentPlant.setEqumentId(MN);
    	    				  miluoOriginalTreatmentPlant.setSiteCode(MN);
    	    				}else{
    	    					  String MN=aa.substring(aa.indexOf("MN=")+3,aa.indexOf(";CP="));
    	        				  miluoRealtimeTreatmentPlant.setEqumentId(MN);
    	        				  miluoOriginalTreatmentPlant.setSiteCode(MN);
    	    				}
    	    			  if(aa.contains("001-Rtd=")) {
    	    				  String ph=aa.substring(aa.indexOf("001-Rtd")+8,aa.indexOf(",001-Flag"));
    	    				  miluoRealtimeTreatmentPlant.setPh(ph);
    	    				}
    	    			  if(aa.contains("B01-Rtd=")) {
    	    				  String B01=aa.substring(aa.indexOf("B01-Rtd=")+8,aa.indexOf(",B01-Flag"));
    	    				  miluoRealtimeTreatmentPlant.setBo1(B01);
    	    				}
    	    			  if(aa.contains("027-Rtd=")) {
    	    				  String pb=aa.substring(aa.indexOf("027-Rtd=")+8,aa.indexOf(",027-Fla"));
    	    				  miluoRealtimeTreatmentPlant.setPb(pb);
    	    				}
    	    			  if(aa.contains("022-Rtd=")) {
    	    				  String ge=aa.substring(aa.indexOf("022-Rtd=")+8,aa.indexOf(",022-Flag"));
    	    				  miluoRealtimeTreatmentPlant.setCd(ge);
    	    				}
    	    			  if(aa.contains("026-Rtd=")) {
    	    				  String shen=aa.substring(aa.indexOf("026-Rtd=")+8,aa.indexOf(",026-Flag="));
    	    				  miluoRealtimeTreatmentPlant.setShen(shen);
    	    				}
    	    			  if(aa.contains("030-Rtd=")) {
    	    				  String zn=aa.substring(aa.indexOf("030-Rtd=")+8,aa.indexOf(",030-Flag="));
    	    				  miluoRealtimeTreatmentPlant.setZn(zn);
    	    				}
    	    			  if(aa.contains("029-Rtd=")) {
    	    				  String cu=aa.substring(aa.indexOf("029-Rtd=")+8,aa.indexOf(",029-Flag="));
    	    				  miluoRealtimeTreatmentPlant.setCu(cu);
    	    				}
    	    			  try {
    	    				  miluoRealtimeTreatmentPlantDao.insert(miluoRealtimeTreatmentPlant);
        	    			  miluoOriginalTreatmentPlantDao.insert(miluoOriginalTreatmentPlant);
    	    			  }catch(Exception e) {
    	    				  logger.info("异常信息:"+e);
    	    			  }
    	    	    }
    			}
    			
    			  
    		}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("有新的客户端建立连接 -> " + ctx.channel().remoteAddress());
        ServerChannelManager.getInstance().addChannel(ctx.channel().id(),"1");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("有客户端断开连接 -> " + ctx.channel().remoteAddress());
        String code = ServerChannelManager.getInstance().getChannel(ctx.channel().id());
        ServerChannelManager.getInstance().removeChannel(ctx.channel().id());
        Map<ChannelId, String> map = ServerChannelManager.getInstance().getMap();
        System.out.println(map.size());
        super.channelInactive(ctx);
    }
    
}
