package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.QxInfoMapper;
import com.sys.pojo.QxInfo;
import com.sys.pojo.extend.DataGridResult;
import javafx.scene.chart.PieChart;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("qxInfoService")
public class QxInfoService extends  BaseService<QxInfo>{

    @Autowired
    private QxInfoMapper qxInfoMapper;


    public DataGridResult findAllQxInfo(Map map){
        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<QxInfo> list=this.qxInfoMapper.findAllQxInfo(map);
        PageInfo<QxInfo> pageInfo=new PageInfo<QxInfo>(list);
        DataGridResult dataGridResult=new DataGridResult(pageInfo.getTotal(),list);
        return dataGridResult;
    }

    @Override
    public int insert(QxInfo pojo) {
        return qxInfoMapper.insert(pojo);
    }

    @Override
    public int update(QxInfo pojo) {
        return qxInfoMapper.update(pojo);
    }

    @Override
    public int delete(String id) {
        return qxInfoMapper.delete(id);
    }

    /**
     * 根据菜单id查询权限信息
     * @param map
     * @return
     */
    public int findQxInfoByMeId(Map map){
        return qxInfoMapper.findQxInfoByMeId(map);
    }

    /**
     * 查询是否存在不同模块下的角色
     * @param qxname
     * @param qxname
     * @return
     */
     public    int findQxinfoByNameOrMid(@Param("qxname") String qxname){
        return  this.qxInfoMapper.findQxinfoByNameOrMid(qxname);
    }
}
