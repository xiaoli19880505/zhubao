package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.ParmItemMapper;
import com.sys.mapper.ParmMapper;
import com.sys.pojo.Parm;
import com.sys.pojo.ParmItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParmItemService extends  BaseService<ParmItem> {

    @Autowired
    private ParmItemMapper parmItemMapper;
    @Autowired
    ParmMapper parmMapper;


    /**
     * 查询区街道成树
     * @return
     */
    public   List<ParmItem>  findAllParmImteByshu(){
        return  this.parmItemMapper.findAllParmImteByshu();
    }
    public   List<ParmItem>  selectGxExceptPeiou(){
        return  this.parmItemMapper.selectGxExceptPeiou();
    }
    public   List<ParmItem>  selectSsqExceptCenter(){
        return  this.parmItemMapper.selectSsqExceptCenter();
    }

    public ParmItem findAllParmImteByChildren(ParmItem parmItem){
        List<ParmItem> list=this.parmItemMapper.findAllParmImteByshu();
        List<ParmItem> resultList = new ArrayList<ParmItem>();
        boolean flag=true;

            for (int i = 0; i <list.size() ; i++) {
                if(i==0){
                    list.get(i).setState("open");
                }else{
                    list.get(i).setState("closed");
                }


            for (ParmItem parmItem1: list){
                if (list.get(i).getPiItemcode().equals(parmItem1.getPiParentsetcode()) ){
                    //parmItem1.setState("closed");
                    list.get(i).getChildren().add(parmItem1);
                }
                if (parmItem1.getPiItemcode().equals(list.get(i).getPiParentsetcode())){
                    flag=false;
                }
            }
                for (ParmItem parmItem1: list){
                    if(parmItem1.getChildren().size()==0){
                        parmItem1.setState(null);
                    }
                }
            if (flag){
                resultList.add(list.get(i));
            }
        }
        parmItem.setChildren(resultList);
        return parmItem;
    }

    /**
     * 下拉区
     * @return
     */
     public  List<ParmItem> findAllQu(){
         return    this.parmItemMapper.findAllQu();
     }

    /**
     * 下拉街道
     * @param qid
     * @return
     */
    public  List<ParmItem> findAllJd(String qid){
        return  this.parmItemMapper.findAllJd(qid);
    }




    /**
     * 分页查询
     * @param map
     * @return
     */
    public PageInfo<ParmItem> pageParm(Map map){

        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        String prSetcode= (String) map.get("piSetcode");
        List<ParmItem> list = parmItemMapper.selectByPage(map);
        if (list!=null&&list.size()>0){
            for (ParmItem item:list) {
                if(item.getPiParentsetcode()==null
                        ||item.getPiParentsetcode().equals("")){
                    Map<String,Object> map2=new HashMap<String, Object>();
                    map2.put("prSetcode",prSetcode);
                    Parm parm=parmMapper.selectBySetCode(map2);
                    if(parm!=null){
                        item.setPiParentsetcode(parm.getPrSetcode());
                        item.setParentName(parm.getPrSetName());
                    }
                }else{

                    if(prSetcode.equals("06")){
                        Map<String,Object> map2=new HashMap<String, Object>();
                        map2.put("piSetcode","05");
                        map2.put("piItemcode",item.getPiParentsetcode());
                        List<ParmItem> list2=parmItemMapper.selectBySetCodeAndItemCode(map2);
                        if (list2!=null &&list2.size()>0){
                            item.setPiParentsetcode(list2.get(0).getPiItemcode());
                            item.setParentName(list2.get(0).getPiItemname());
                        }
                    }else if(prSetcode.equals("05")){
                        Map<String,Object> map2=new HashMap<String, Object>();
                        map2.put("piSetcode","04");
                        map2.put("piItemcode",item.getPiParentsetcode());
                        List<ParmItem> list2=parmItemMapper.selectBySetCodeAndItemCode(map2);
                        if (list2!=null &&list2.size()>0){
                            item.setPiParentsetcode(list2.get(0).getPiItemcode());
                            item.setParentName(list2.get(0).getPiItemname());
                        }
                    }else if(prSetcode.equals("04")){
                        Map<String,Object> map2=new HashMap<String, Object>();
                        map2.put("piSetcode","04");
                        map2.put("piItemcode","0");
                        List<ParmItem> list2=parmItemMapper.selectBySetCodeAndItemCode(map2);
                        if (list2!=null &&list2.size()>0){
                            item.setPiParentsetcode(list2.get(0).getPiItemcode());
                            item.setParentName(list2.get(0).getPiItemname());
                        }
                    }

                }


            }
        }
        return new PageInfo<ParmItem>(list);
    }

    /**
     * 获取PiItemCode最大值
     * @return
     */
    public String getMaxPiItemCode(Map map){
        return parmItemMapper.getMaxPiItemCode(map);
    }

    public ParmItem selectSwitch(){
        return parmItemMapper.selectSwitch();
    }

    /**
     * 根据条件查询   setCode 和 itemCode
     * @param map
     * @return
     */
    public  List<ParmItem> selectBySetCodeAndItemCode(Map map){
        return parmItemMapper.selectBySetCodeAndItemCode(map);
    }

    /**
     * 级联查询
     * @param map
     * @return
     */
    public  List<ParmItem> selectBySetCodeAndParentCode(Map map){
        return parmItemMapper.selectBySetCodeAndParentCode(map);
    }

    /**
     * 根据条件删除右侧字典
     * @param map
     * @return
     */
    public int deletParmItem(Map map){
        return parmItemMapper.deletParmItem(map);
    }

    /**
     * 根据条件查询右侧字典的数量
     * @param map
     * @return
     */
    public int countParm(Map map){
        return parmItemMapper.countParm(map);
    }
    /**
     * 行政区排序 市区-鼓楼区-云龙区-泉山区-经济结束开发区
     * @return
     */
    public  List<ParmItem> xzqOrder(){
        return parmItemMapper.xzqOrder();
    }
    /**
     * 只查询配偶子女
     * @return
     */
    public  List<ParmItem> findFamylyOnlypozn(){
        return parmItemMapper.findFamylyOnlypozn();
    }

}
