package com.sys.service.home;

import com.sys.mapper.ColumnInfoMapper;
import com.sys.pojo.ColumnInfo;
import com.sys.pojo.UserInfo;
import com.sys.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class ColumnInfoService extends BaseService<ColumnInfo> {

    @Autowired
    private ColumnInfoMapper columnInfoMapper;

    /**
     * 新增
     * @param columnCode 栏目编码
     * @param columnName 栏目名称
     * @param sequence 顺序
     * @return
     */
    @Transactional
    public Object saveColumnInfo(String columnCode,String columnName,String url,
                                 String sequence,String downFlag,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        Short columnLevel = 1;//默认 顶级
        Date date =  new Date();
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setColumnCode(columnCode);
        columnInfo.setDeleteFlag("F");
        List<ColumnInfo> columnInfos = columnInfoMapper.selectByCondition(columnInfo);
        if(columnInfos.size()>0){
            return "栏目编码已经存在";
        }
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        columnInfo.setId(uuid);

        columnInfo.setColumnLevel(columnLevel);
        columnInfo.setColumnName(columnName);
        columnInfo.setUrl(url);
        columnInfo.setCreatePerson(userinfo.getUsercode());
        columnInfo.setCreateTime(date);
        columnInfo.setDeleteFlag("F");
        columnInfo.setUpdatePerson(userinfo.getUsercode());
        columnInfo.setUpdateTime(date);
        columnInfo.setParentId(uuid);
        columnInfo.setDownFlag(downFlag);
        Short s = Short.parseShort(StringUtils.isNotEmpty(sequence)?sequence:"0");
        columnInfo.setSequence(s);
        int num = columnInfoMapper.insert(columnInfo);
        boolean flag ;
        String msg ;
        if(num>0){
            msg = "操作成功!";
        }else{
            msg = "新增数据异常!";
        }
        return msg;
    }

    @Transactional
    public Object deleteById(String id,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        if(StringUtils.isEmpty(id)){
            return "删除提供信息缺失,无法修改!";
        }
        ColumnInfo columnInfo  = columnInfoMapper.selectById(id);
        columnInfo.setDeleteFlag("T");// 逻辑删除
        columnInfo.setUpdatePerson(userinfo.getUsercode());
        columnInfo.setUpdateTime(new Date());
        int num = columnInfoMapper.update(columnInfo);
        boolean flag ;
        String msg ;
        if(num>0){
            msg = "操作成功!";
        }else{
            msg = "删除数据异常!";
        }
        return msg;
    }

    /**
     * 修改
     * @param id 主键
     * @param columnCode 栏目编码
     * @param columnName 栏目名称
     * @param sequence 顺序
     * @return
     */
    @Transactional
    public Object updateColumInfo(String id,String columnCode,String columnName, String url,String sequence,
                                  String downFlag,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        if(StringUtils.isEmpty(id)){
            return "修改提供信息缺失,无法修改!";
        }
        ColumnInfo columnInfo  = columnInfoMapper.selectById(id);
        if(columnInfo==null){
            return "修改栏目信息丢失!";
        }
        columnInfo.setColumnCode(columnCode);
        columnInfo.setColumnName(columnName);
        columnInfo.setUrl(url);
        columnInfo.setSequence(Short.parseShort(sequence));
        Date date = new Date();
        columnInfo.setUpdateTime(date);
        columnInfo.setUpdatePerson(userinfo.getUsercode());
        columnInfo.setDownFlag(downFlag);
        int num = columnInfoMapper.update(columnInfo);
        boolean flag ;
        String msg ;
        if(num>0){
            msg = "操作成功!";
        }else{
             msg = "修改数据异常!";
        }
        return msg;
    }

    /**
     * 查询
     * @param columnName 栏目名称
     * @return
     */
    @Transactional
    public Object selectByConditions(String columnName){
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setColumnName(columnName);
        columnInfo.setDeleteFlag("F");
        List<ColumnInfo> columnInfos = columnInfoMapper.selectByCondition(columnInfo);
        return columnInfos;
    }

    public Object getAllTree(){
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setDeleteFlag("F");
        return columnInfoMapper.selectByConditionForAll(columnInfo);
    }

}
