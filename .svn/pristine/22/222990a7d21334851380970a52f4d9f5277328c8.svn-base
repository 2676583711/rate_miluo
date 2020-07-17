package com.rate.system.rate_system.utils;
/**
 * 
 * @author zhang
 *result = Result.success(dataMap);// 成功，并返回数据和retCode和message  
  
// result = Result.success();// 成功，不返回数据，只返回retCode和message  
  
// result = Result.error(CodeMsg.SERVER_EXCEPTION);// 失败返回错误信息  
  
// result = Result.error(CodeMsg.SERVER_EXCEPTION,e.toString());// 失败返回错误+扩展错误信息  
 */
public class CodeMsg {
   
	private int retCode;  
    private String message;  
    // 按照模块定义CodeMsg  
    // 通用异常  
    public static CodeMsg SUCCESS = new CodeMsg(200,"success");  //200为成功！
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500100,"操作失败");  
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500101,"输入参数为空");
    public static CodeMsg PARAMETER_ERROE = new CodeMsg(500107,"输入参数有误");
    public static CodeMsg LONIN_ERROR = new CodeMsg(500105,"登陆失败");
    public static CodeMsg VALIDATA_ERROR = new CodeMsg(150,"用户未登陆");
    public static CodeMsg SERVER_BUSY = new CodeMsg(500,"服务器正忙,请稍后再试");
    // 业务异常  
    public static CodeMsg USER_NOT_EXSIST = new CodeMsg(500102,"用户不存在");   
    public static CodeMsg ONLINE_USER_OVER = new CodeMsg(500103,"在线用户数超出允许登录的最大用户限制。");   
    public static CodeMsg SESSION_NOT_EXSIST =  new CodeMsg(500104,"不存在离线session数据");  
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(500105,"查找不到对应数据");
    public static CodeMsg NOT_AUTHORITY = new CodeMsg(500106,"没有权限");
    public static CodeMsg OUT_OF_DATE = new CodeMsg(500106,"只能查看最近7天的数据。");
    public static CodeMsg OUT_OF_INTERVAL = new CodeMsg(500106,"时间间隔不能超过一天。");
    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;  
        this.message = message;  
    }  
    public int getRetCode() {  
        return retCode;  
    }  
    public String getMessage() {  
        return message;  
    }  
    public void setMessage(String message) {  
        this.message = message;  
    }  
}
