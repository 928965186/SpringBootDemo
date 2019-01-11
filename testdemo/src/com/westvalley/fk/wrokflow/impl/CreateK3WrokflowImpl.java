package com.westvalley.fk.wrokflow.impl;

import java.util.List;

import com.westvalley.fk.service.ICreateK3Table;
import com.westvalley.fk.service.impl.CreateK3TableImpl;
import com.westvalley.fk.wrokflow.ICreateK3Wrokflow;

import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.RequestService;

/**
 * 根据查找的K3数据创建OA流程
 * @author xieh
 * @date 2018年9月13日18:46:35
 * @version 1.0
 *
 */
public class CreateK3WrokflowImpl implements ICreateK3Wrokflow{
	//e-c数据源
	RecordSet rs=new RecordSet();
	//自定义返回参数在方法中赋值
	String flag="";
	//流程名称
	String workflowName="";
	//是否自动流转下一个节点  0不自动  1自动
	String isNext="1";
	//使用自定义接口实现类中的方法获取K3的数据并返回List
	ICreateK3Table k3Table=new CreateK3TableImpl();
	
	
	@Override
	public String create() {
		rs.writeLog("进入创建销售单流程");
		//获取全部符合条件的K3数据
		List<Object> list = k3Table.QuesyK3();
		//获取数据源
		RecordSet rs=new RecordSet();
		//--------------------------------------------------------------------
		// 流程发起操作类
		RequestService reqService = new RequestService();
		// 创建请求信息对象
		RequestInfo reqInfo = new RequestInfo();
		// 流程发起人ID
		reqInfo.setCreatorid((String) list.get(1));
		// workflowid/留言流程ID=5
		reqInfo.setWorkflowid("120");
		// 紧急程度
		reqInfo.setRequestlevel("0");
		// 提醒类型
		reqInfo.setRemindtype("1");
		// 是否自动流转到下个节点（0：不自动提交；1:屏蔽着自动提交）
		reqInfo.setIsNextFlow(isNext);
		// 流程标题  流程标题=标题-流程发起人-时间（YYYY-mm-dd）
		//reqInfo.setDescription("付款申请流程"+"-"+);
		//-----------------------------------------------------------------------
		MainTableInfo mtInfo = new MainTableInfo();// 创建流程主表信息对象
		Property[] mProperty = new Property[1];// 创建流程主表属性集合
		
		try {
			flag = reqService.createRequest(reqInfo);
		} catch (Exception e) {
			rs.writeLog(TimeUtil.getCurrentTimeString()+"创建流程异常"+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
