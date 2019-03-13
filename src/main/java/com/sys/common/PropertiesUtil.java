package com.sys.common;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Desc:properties配置文件的管理工具类
 * @Author:wangli
 * @CreateTime:20:05 2018/10/15
 */
public class PropertiesUtil {
    private static Properties flowProperties = null;	//审批流关键字管理properties
    private static Properties imgProperties = null;//图片管理properties
    private static Properties applyTypeProperties = null;//图片管理properties
    private static Properties areaProperties = null;//区街道 字典关键字
    private static Properties overTimeProperties = null;//区街道 字典关键字
    private static Properties contractProperties = null;//合同 字典关键字
    private static Properties ftpProperties = null;//合同 字典关键字
    private static void initProperties() {
        try {
            /*初始化流程配置文件properties*/
            flowProperties = new Properties();
            PropertiesUtil flowPropUtil = new PropertiesUtil();
            InputStream inputStream = flowPropUtil.getClass().getResourceAsStream("/data/flow.properties");//审批流关键字管理properties
            flowProperties.load(new InputStreamReader(inputStream, "UTF-8"));

            /*初始化流程配置文件properties*/
            imgProperties = new Properties();
            InputStream inputStream1 = flowPropUtil.getClass().getResourceAsStream("/data/img.properties");//流程文件管理properties
            imgProperties.load(new InputStreamReader(inputStream1, "UTF-8"));

            /*初始化申请类别配置文件properties*/
            applyTypeProperties = new Properties();
            InputStream inputStream2 = flowPropUtil.getClass().getResourceAsStream("/data/applytype.properties");//注册打印管理properties
            applyTypeProperties.load(new InputStreamReader(inputStream2, "UTF-8"));

            /*初始化申请区域关键字配置文件properties*/
            areaProperties = new Properties();
            InputStream inputStream3 = flowPropUtil.getClass().getResourceAsStream("/data/area.properties");//注册打印管理properties
            areaProperties.load(new InputStreamReader(inputStream3, "UTF-8"));

            /*初始化申请区域关键字配置文件properties*/
            overTimeProperties = new Properties();
            InputStream inputStream4 = flowPropUtil.getClass().getResourceAsStream("/data/overtime.properties");//注册打印管理properties
            overTimeProperties.load(new InputStreamReader(inputStream4, "UTF-8"));

            /*初始化申请合同关键字配置文件properties*/
            contractProperties = new Properties();
            InputStream inputStream5 = flowPropUtil.getClass().getResourceAsStream("/data/contract.properties");//注册打印管理properties
            contractProperties.load(new InputStreamReader(inputStream5, "UTF-8"));

            /*初始化FTP关键字配置文件properties*/
            ftpProperties = new Properties();
            InputStream inputStream6 = flowPropUtil.getClass().getResourceAsStream("/data/ftpconnect.properties");//注册打印管理properties
            ftpProperties.load(new InputStreamReader(inputStream6, "UTF-8"));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 通过key获取对应的value值
     * @param key
     * @return
     */
    public static String getFlowProperties(String key){
        if(flowProperties==null)
            initProperties();
        return flowProperties.getProperty(key);
    }

    /**
     * 通过key获取对应的value值
     * @param key
     * @return
     */
    public static String getImgProperties(String key){
        if(imgProperties==null)
            initProperties();
        return imgProperties.getProperty(key);
    }

    /**
            * 通过key获取对应的value值
     * @param key
     * @return
             */
    public static String getContractProperties(String key){
        if(contractProperties==null)
            initProperties();
        return contractProperties.getProperty(key);
    }

    /**
     * 通过key获取对应的value值
     * @param key
     * @return
     */
    public static String getApplyTypeProperties(String key){
        if(applyTypeProperties==null)
            initProperties();
        return applyTypeProperties.getProperty(key);
    }

    /**
     * 通过key获取对应的value值
     * @param key
     * @return
     */
    public static String getAreaProperties(String key){
        if(areaProperties==null)
            initProperties();
        return areaProperties.getProperty(key);
    }

    /**
     * 通过key获取对应的value值
     * @param key
     * @return
     */
    public static String getOverTimeProperties(String key){
        if(overTimeProperties==null)
            initProperties();
        return overTimeProperties.getProperty(key);
    }

    /**
     * 通过key获取对应的value值
     * @param key
     * @return
     */
    public static String getFTPProperties(String key){
        if(ftpProperties==null)
            initProperties();
        return ftpProperties.getProperty(key);
    }

}