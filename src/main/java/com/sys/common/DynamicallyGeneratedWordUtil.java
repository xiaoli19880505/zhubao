package com.sys.common;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.Service;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class DynamicallyGeneratedWordUtil {
    private static Configuration freemarkerConfig;

    static {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_22);
        freemarkerConfig.setEncoding(Locale.getDefault(), "UTF-8");
    }


    /**
     * 将xml模板转换为后缀为doc文件，本质仍是属于xml
     * @param tempLetDir	模板路径
     * @param templetName	模板文件名
     * @param targetDir	模板文件路径
     * @param targetName	模板文件路径
     * @throws IOException
     * @throws TemplateException
     */
    public void genWordFile(String tempLetDir,
             String templetName, String targetDir, String targetName,Map<String,Object> inputMap)
            throws IOException, TemplateException{
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        // 如果目标文件目录不存在，则需要创建
        File file = new File(targetDir);
        if(!file.exists()){
            file.mkdirs();
        }
        // 加载模板数据（从文件路径中获取文件，其他方式，可百度查找）
        configuration.setDirectoryForTemplateLoading(new File(tempLetDir.replace(templetName,"")));
        // 获取模板实例
        Template template = configuration.getTemplate(templetName);
        File outFile = new File(targetDir + File.separator + targetName);
        //将模板和数据模型合并生成文件
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        //生成文件
        template.process(inputMap, out);
        out.flush();
        out.close();
    }
}
