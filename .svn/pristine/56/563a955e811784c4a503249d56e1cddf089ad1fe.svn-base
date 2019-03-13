package com.sys.mapper;

import com.sys.pojo.ParmItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParmItemMapper extends  IMapper<ParmItem> {


    /**
     * 下拉区
     * @return
     */
    List<ParmItem> findAllQu();


    ParmItem findAllQuByUid(String itemCode);

    ParmItem findAllJdByUid(String itemCode);

    /**
     * 下拉街道
     * @param qid
     * @return
     */
    List<ParmItem> findAllJd(String qid);

    List<ParmItem> select(ParmItem pojo);

    ParmItem selectById(String id);

    /**
     * 查询区街道成树
     * @return
     */
    List<ParmItem>  findAllParmImteByshu();

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<ParmItem> selectByPage(Map<String, Integer> map);

    /**
     * 获取PiItemCode最大值
     * @return
     */
    String getMaxPiItemCode(Map map);

    /**
     * 根据条件查询   setCode 和 itemCode
     * @param map
     * @return
     */
    List<ParmItem> selectBySetCodeAndItemCode(Map map);

    /**
     * 级联查询
     * @param map
     * @return
     */
    List<ParmItem> selectBySetCodeAndParentCode(Map map);

    /**
     * 根据条件删除字典
     * @param map
     * @return
     */
    int deletParmItem(Map map);

    /**
     * 根据条件查询字典的数量
     * @param map
     * @return
     */
    int countParm(Map map);

    ParmItem selectByMap(Map map);

    /**
     * 不包含配偶的关系
     * @param
     * @return
     */
    List<ParmItem> selectGxExceptPeiou();

    /**
     * 区列表，不包含市区
     * @return
     */
    List<ParmItem> selectSsqExceptCenter();

    /**
     * 查询申请开关 是--可以申请  否-关闭申请
     * @return
     */
    ParmItem selectSwitch();

    /**
     * 行政区排序 市区-鼓楼区-云龙区-泉山区-经济结束开发区
     * @return
     */
    List<ParmItem> xzqOrder();

    /**
     * 只查询配偶子女
     * @return
     */
    List<ParmItem> findFamylyOnlypozn();

}
