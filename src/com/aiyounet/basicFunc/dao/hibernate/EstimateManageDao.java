package com.aiyounet.basicFunc.dao.hibernate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.EstimateManage;
import com.aiyounet.basicFunc.util.DateUtil;
import com.aiyounet.basicFunc.util.StringUtil;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class EstimateManageDao extends BaseDaoImpl<EstimateManage> {
	/**
	 * 获取列表
	 * 
	 * @parame UserName 用户名
	 * 
	 * @parame PlatName 平台名称
	 * 
	 * @parame StartTime 开始日期
	 * 
	 * @parame EndTime 结束日期
	 */
	@SuppressWarnings("unchecked")
	public List<EstimateManage> getEstimaManageList(String userName,
			String platName, String startTime, String endTime) throws Exception {
		Session se = super.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		
		//SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String endTimeValue;
		if(StringUtil.isNull(endTime)){
			endTimeValue = DateUtil.getCurDatetime("yyyy-MM-dd");//simple.format(new Date());
			map.put("endTime", endTimeValue);
		} else {
			endTimeValue = endTime;
			map.put("endTime", endTime);
		}
		if(StringUtil.isNull(startTime)){
			map.put("startTime",DateUtil.nDaysAfterOneDateString(endTimeValue, -7));
		} else {
			map.put("startTime", startTime);
		}
		String Sql = "from EstimateManage e where e.isDelete = 0 and e.addTime >= :startTime and e.addTime <= :endTime";
		if (!StringUtil.isNull(userName)) {
			Sql += " and e.userName = :userName";
			map.put("userName", userName);
		}
		if (!StringUtil.isNull(platName)) {
			Sql += " and e.platName = :platName";
			map.put("platName", platName);
		}
		List<EstimateManage> list = se.createQuery(Sql).setProperties(map)
				.list();
		
		return list;
	}
	public void deleteEstimateByUserName(String userName) throws Exception{
		super.getSession().createQuery("update EstimateManage set isDelete = 1 where userName = :userName").setParameter("userName", userName).executeUpdate();
	}
	public void deleteEstimateByKeywords(String keywords) throws Exception{
		super.getSession().createQuery("update EstimateManage set isDelete = 1 where userName like :keywords").setParameter("keywords", "%"+keywords+"%").executeUpdate();
	}
}
