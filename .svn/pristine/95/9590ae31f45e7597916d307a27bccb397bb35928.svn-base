package com.sys.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.BatchUtil;
import com.sys.mapper.MessageMapper;
import com.sys.mapper.apply.ApplyFamilyMemberMapper;
import com.sys.mapper.contract.ContractDetailMapper;
import com.sys.pojo.Message;
import com.sys.pojo.apply.ApplyFamilyMember;
import com.sys.pojo.contract.ContractDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageService extends BaseService<Message> {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private BatchUtil batchUtil;

    @Autowired
    private ContractDetailMapper contractDetailMapper;

    @Autowired
    private ApplyFamilyMemberMapper applyFamilyMemberMapper;
    /**
     * 查询条件下消息数目
     * @param map
     * @return
     */
    public int selectCountByMap(Map map){
        return messageMapper.selectCountByMap(map);
    }

    /*插入信息的接口*/
    public void updateAndAddMessageBatch(List<Message> messAddList,List<Message> messUpdateList){
        if(!messAddList.isEmpty()){
            batchUtil.save(messAddList,"MessageMapper");
        }
        if(!messUpdateList.isEmpty()){
            messageMapper.updateMessBaBatch(messUpdateList);
        }
    }

    /*插入信息的接口与合同的接口（合同到期时间的维护）*/
    public void updateAndAddMessageByOne(List<Message> messAddList, List<Message> messUpdateList, ContractDetail contractDetail){

        String linktel="";
        if(!messAddList.isEmpty()){
            linktel=messAddList.get(0).getLinktel();
            batchUtil.save(messAddList,"MessageMapper");
        }
        if(!messUpdateList.isEmpty()){
            linktel=messUpdateList.get(0).getLinktel();
            messageMapper.updateMessBaBatch(messUpdateList);
        }
        /*插入合同表*/
        contractDetailMapper.insert(contractDetail);

        /*更新手机号码*/
        List<ApplyFamilyMember> toUpdateList = Lists.newArrayList();
        ApplyFamilyMember applyFamilyMember = new ApplyFamilyMember();
        applyFamilyMember.setAfmSqid(contractDetail.getcSqid());
        applyFamilyMember.setAfmLxdh(linktel);
        toUpdateList.add(applyFamilyMember);
        //this.ap
    }

    /*插入信息的接口与合同的接口（合同到期时间的维护），同时插入合同信息与更新家庭成员手机信息*/
    public void updateAllMessage(List<Message> messAddList, List<Message> messUpdateList,List<ContractDetail> contractDetailList,List<ApplyFamilyMember> memberList){

        String linktel="";
        if(!messAddList.isEmpty()){
            linktel=messAddList.get(0).getLinktel();
            batchUtil.save(messAddList,"MessageMapper");
        }
        if(!messUpdateList.isEmpty()){
            linktel=messUpdateList.get(0).getLinktel();
            messageMapper.updateMessBaBatch(messUpdateList);
        }
        /*插入合同表*/
        batchUtil.save(contractDetailList,"contract.ContractDetailMapper");

        /*批量更新家庭成员手机号*/
        if(memberList.size()>0){
            this.applyFamilyMemberMapper.updateMemberBatch(memberList);
        }
    }

    /**
     * 条件查询消息列表
     * @param map
     * @return
     */
    public List<Message> findMessageList(Map map){
        return  this.messageMapper.findMessageList(map);
    }
}
