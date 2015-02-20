package com.aiyounet.basicFunc.dao.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.UserInfo;
import com.aiyounet.basicFunc.util.DateUtil;
import com.aiyounet.basicFunc.util.StringUtil;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class UserInfoDao extends BaseDaoImpl<UserInfo> {
	private static final Logger log = LoggerFactory.getLogger(UserInfoDao.class);
 
	// 根据条件查询用户信息,flag参数用来标识是否显示全部
	public List<UserInfo> getUserInfoByCondition(String flag, int type) throws Exception {
		String hql = "from UserInfo u where u.isProtect = 0 and u.PId != 0";
		Map<String, Object> map = new HashMap<String, Object>();
		if (flag.equals("all"))
			hql = hql + "and u.userType != 2";
		else {
			hql = hql + "and u.userType = :type";
			map.put("type", type);
		}
		Session session = super.getSession();
		Query query = session.createQuery(hql).setProperties(map);
		return query.list();
	}

	 

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUsersOfManager() {
		Session session = super.getSession();
		return session.createQuery("from UserInfo o where o.userType=2 ").list();
	}

	public Integer getUsersOfManagerCount() {
		Session session = super.getSession();
		return session.createQuery("from UserInfo o where  o.userType=2 ").list().size();
	}

	public void setUserLoginState(String token, String lastLoginTime, Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery("update user_info set token = ?,last_login_time = ? where id = ?")
				.setParameter(0, token).setParameter(1, lastLoginTime).setParameter(2, userId)
				.executeUpdate();
	}

	public void setUserLoginIP(String ip, String lastLoginTime, Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery(
				"update user_info set last_login_ip = ?,last_login_time = ? where id = ?")
				.setParameter(0, ip).setParameter(1, lastLoginTime).setParameter(2, userId)
				.executeUpdate();
	}

	public void setProtectInfo(String q1, String q2, String q3, String a1, String a2, String a3,
			Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery(
				"update user_info set question1 = ?,question2 = ?,question3=?,answer1=?,answer2=?,answer3=? where id = ?")
				.setParameter(0, q1).setParameter(1, q2).setParameter(2, q3).setParameter(3, a1)
				.setParameter(4, a2).setParameter(5, a3).setParameter(6, userId).executeUpdate();
	}

	public void setReceivablesAccount(String bankName, String accoName, String accoNumb,
			String bankMemo, Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery(
				"update user_info set bank_name = ?,acco_name = ?,acco_numb=?,bank_memo=? where id = ?")
				.setParameter(0, bankName).setParameter(1, accoName).setParameter(2, accoNumb)
				.setParameter(3, bankMemo).setParameter(4, userId).executeUpdate();
	}

	public void setLoginPw(String passWd, Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery("update user_info set password=? where id = ?")
				.setParameter(0, passWd).setParameter(1, userId).executeUpdate();
	}

	public void setMoneyPw(String moneyPw, Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery("update user_info set draw_pass=? where id = ?")
				.setParameter(0, moneyPw).setParameter(1, userId).executeUpdate();
	}

	public void setUserInfo(String nickName, String qq, String address, Integer userId) {
		Session session = super.getSession();
		session.createSQLQuery("update user_info set nick_name = ?,qq = ?,address=? where id = ?")
				.setParameter(0, nickName).setParameter(1, qq).setParameter(2, address)
				.setParameter(3, userId).executeUpdate();
	}

/*	public void setMemberRebate(String userName, Double reWardPoint) {
		Session session = super.getSession();
		session.createSQLQuery("update user_info set reward_point=? where user_name = ?")
				.setParameter(0, reWardPoint).setParameter(1, userName).executeUpdate();
	}*/

	/**
	 * 查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo getUserInfo(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		String sql = " from UserInfo u where u.userName = :userName and isStop != 1  and userType = 2 ";
		Query query = getSession().createQuery(sql).setProperties(map);
		return (UserInfo) query.uniqueResult();
	}
	/**
	 * 查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo getUserInfo2(String userName,Integer platId) {
		Query query = getSession().createQuery(
				" from UserInfo u where u.userName = ? and isStop != 1 and u.platId=?");
		query.setParameter(0, userName).setParameter(1, platId);
		return (UserInfo) query.uniqueResult();
	}
	/**
	 * 查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo getUserInfoByIdAndPlat(Integer userId,Integer platId) {
		Query query = getSession().createQuery(
				" from UserInfo u where u.id = ? and u.platId=?");
		query.setParameter(0, userId).setParameter(1, platId);
		return (UserInfo) query.uniqueResult();
	}
 

	public UserInfo getUserInfoOfParent(String userName,Integer platId) {
		Query query = getSession().createQuery(
				" from UserInfo u where u.userName = ? and userType=1 and platId = ? ");
		query.setParameter(0, userName).setParameter(1, platId);
		return (UserInfo) query.uniqueResult();
	}
 

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserInfoOfApi(String userName) {
		Query query = getSession().createQuery(
				" from UserInfo u where u.userName = ? and userType!=2");
		query.setParameter(0, userName);
		return query.list();
	}

	public Object getUserInfoOfVipCount(String sql, Map<String, Object> paras) {
		Session session = super.getSession();
		Query query = session.createSQLQuery(sql);
		return query.setProperties(paras).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list().size();
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUsersOfVip(PageBean page, String sql,
			Map<String, Object> paras) {
		Session session = super.getSession();
		Query query = session.createSQLQuery(sql);
		return query.setProperties(paras).setFirstResult(page.getStart())
				.setMaxResults(page.getRows())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
 
	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserChils(Integer integer) {
		Session session = super.getSession();
		Query query = session.createQuery("from UserInfo where PId=? order by registTime desc")
				.setParameter(0, integer);
		return query.list();
	}
 

	/**
	 * 根据用户ID获取用户，排除已停用账号
	 * 
	 * @author CJianY
	 * @date 2013-1-28
	 * 
	 * @param id
	 * @return
	 */
	public UserInfo getUserById(String id) {
		Integer userId = Integer.parseInt(id);
		Session session = super.getSession();
		Query query = session.createQuery("from UserInfo where id=? and isStop != 1").setParameter(
				0, userId);
		return (UserInfo) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getchildsId(int parseInt) {
		Session session = super.getSession();

		return session.createSQLQuery("select id from user_info where p_id=? and is_stop != 1")
				.setParameter(0, parseInt).list();
	}
 
	public UserInfo getUserOfNick(String nickName,Integer platId) {
		Query query = getSession().createQuery(" from UserInfo u where u.nickName = ? and u.platId = ?");
		query.setParameter(0, nickName);
		query.setParameter(1, platId);
		return (UserInfo) query.uniqueResult();
	}
 
 
 
	public void setDes(Integer id, String des) {
		Query query = getSession()
				.createSQLQuery("update user_info set descrip = ?  where id = ? ");
		query.setParameter(0, des).setParameter(1, id).executeUpdate();
	}

	public void setStop(Integer id, Integer state) {
		Query query = getSession()
				.createSQLQuery("update user_info set is_stop = ?  where id = ? ");
		query.setParameter(0, state).setParameter(1, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserMessage(Integer id) {
		Query query = getSession()
				.createSQLQuery(
						"select ifnull(sum(bet_amount),0) betAmount,ifnull(sum(win_amount),0) winAmount,ifnull(sum(return_point),0) returnAmount from order_info where user_id = ? and state=1");
		return (Map<String, Object>) query.setParameter(0, id)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
	}

	public void setModel(int id, int model) {
		Query query = getSession().createSQLQuery("update user_info set model = ?  where id = ? ");
		query.setParameter(0, model).setParameter(1, id).executeUpdate();

	}

	public void updateRewardPoint(Integer userId, Double levelPoint, Integer userType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("levelPoint", levelPoint);
		param.put("userType", userType);
		Query query = getSession()
				.createSQLQuery(
						"update user_info set reward_point = :levelPoint, user_type = :userType  where id = :userId ");
		query.setProperties(param);
		query.executeUpdate();
	}

	public void updateDividendPoint(Integer userId, Double dividendPoint) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("dividendPoint", dividendPoint);
		Query query = getSession().createSQLQuery(
				"update user_info set divided_point = :dividendPoint  where id = :userId ");
		query.setProperties(param);
		query.executeUpdate();
	}

	public void addMoney(Integer id, Double addValue) {
		Query query = getSession().createSQLQuery(
				"UPDATE user_info SET money = money + ? WHERE id = ?");
		query.setParameter(0, addValue).setParameter(1, id).executeUpdate();
	}

	public Integer getFirstAgentByPid(Integer id) {
		String sql = "SELECT  id FROM (SELECT r.id  id,  r.user_name    userName,  getChildLst(r.id) lowerId, r.p_id  AS pId  FROM user_info r  WHERE  r.user_type = 1 AND r.p_id  != 0 AND r.p_id IN (SELECT id FROM user_info WHERE p_id = 0 ) ) r2 WHERE FIND_IN_SET(?,r2.lowerId) ";
		Query query = getSession().createSQLQuery(sql).setParameter(0, id);
		Map<String, Object> map = (Map<String, Object>) query.setParameter(0, id)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		if (StringUtil.isNull(map)) {
			return id;
		}
		return (Integer) map.get("id");
	}

	public String getFirstAgentByPidReturnName(Integer id) {
		String sql = "SELECT  userName FROM (SELECT r.id  id,  r.user_name    userName,  getChildLst(r.id) lowerId, r.p_id  AS pId  FROM user_info r  WHERE  r.user_type = 1 AND r.p_id  != 0 AND r.p_id IN (SELECT id FROM user_info WHERE p_id = 0 ) ) r2 WHERE FIND_IN_SET(?,r2.lowerId) ";
		Query query = getSession().createSQLQuery(sql).setParameter(0, id);
		Map<String, Object> map = (Map<String, Object>) query.setParameter(0, id)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		if (StringUtil.isNull(map)) {
			return "";
		}
		return (String) map.get("userName");
	}

	public Map<String, Object> getUserIdLower(Integer userId) {
		String sql = "SELECT SUBSTR(getChildLst(?),4) AS userIds FROM DUAL ";
		Query query = getSession().createSQLQuery(sql).setParameter(0, userId)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		if (!StringUtil.isNull(list) && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
 

	public void setUserLockState(Integer id) {
		Session session = super.getSession();
		session.createSQLQuery("UPDATE user_info SET err_time = NULL , err_count = 0 WHERE id = ? ")
				.setParameter(0, id).executeUpdate();
	}

	public void setUserAccoLockState(Integer id) {
		Session session = super.getSession();
		session.createSQLQuery(
				"UPDATE user_info SET err_acco_time = NULL , err_acco_count = 0 WHERE id = ? ")
				.setParameter(0, id).executeUpdate();
	}

	public List<UserInfo> searchSystemAgent() {
		String sql = " from UserInfo u where u.isProtect != 1 and u.isStop != 1 and u.userType = 1 and u.PId = 0 ";
		Query query = getSession().createQuery(sql);
		List<UserInfo> list = query.list();
		if (!StringUtil.isNull(list) && list.size() > 0) {
			return list;
		}
		return new ArrayList<UserInfo>();
	}

	/*public List<UserInfo> getUserByCardNo(String accountNo) {
		Query query = getSession().createQuery(" from UserInfo u where u.accoNumb = ?");
		query.setParameter(0, accountNo);
		return query.list();
	}*/

	public void setDrawPw(String encode32, Integer id) {
		Session session = super.getSession();
		session.createSQLQuery("update user_info set password=? where id = ?")
				.setParameter(0, encode32).setParameter(1, id).executeUpdate();
	}

 

	public List<UserInfo> getUserChilsNew(List<Integer> userIds) {
		String sql = "from UserInfo where id in ( :userIds ) and isStop = 0 ";
		@SuppressWarnings("unchecked")
		List<UserInfo> list = super.getSession().createQuery(sql)
				.setParameterList("userIds", userIds).list();
		return list;
	}

	public List<UserInfo> getUserChilsNew2(List<Integer> userIds) {
		String sql = "from UserInfo where id in ( :userIds )   ";
		@SuppressWarnings("unchecked")
		List<UserInfo> list = super.getSession().createQuery(sql)
				.setParameterList("userIds", userIds).list();
		return list;
	}

	public List<Map<String, Object>> getUserQuotaUsed(List<Integer> userIds, String registBegin) {
		String sql = "SELECT p_id userId,SUBSTR(`regist_time`,1,7) registMonth,COUNT(*) usedQuota,reward_point rewardPoint FROM user_info WHERE p_id IN (:userIds) AND `regist_time` >= :registBegin  GROUP BY p_id,reward_point";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("registBegin", registBegin);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = super.getSession().createSQLQuery(sql)
				.setParameterList("userIds", userIds).setProperties(map)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	/*public List<UserInfo> getUserListByMac(String mac) {
		String sql = "from UserInfo where moreInfo = ? ";
		List<UserInfo> list = super.getSession().createQuery(sql).setParameter(0, mac).list();
		return list;
	}*/

	public void updateUserInfo(UserInfo u, Integer userId, String userName) {
		StringBuffer updateSql = new StringBuffer(
				" update UserInfo set onlineStatus = onlineStatus ");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNull(u.getErrCount())) {
			updateSql.append(" ,errCount = :errCount ");
		}
		if (!StringUtil.isNull(u.getErrTime())) {
			updateSql.append(" ,errTime = :errTime ");
		}
		if (!StringUtil.isNull(u.getErrAccoCount())) {
			updateSql.append(" ,errAccoCount = :errAccoCount ");
		}
		if (!StringUtil.isNull(u.getErrAccoTime())) {
			updateSql.append(" ,errAccoTime = :errAccoTime ");
		}
		if (!StringUtil.isNull(u.getToken())) {
			updateSql.append(" ,token = :token ");
		}
		if (!StringUtil.isNull(u.getLastLoginTime())) {
			updateSql.append(" ,lastLoginTime = :lastLoginTime ");
		}
		if (!StringUtil.isNull(u.getLastLoginIp())) {
			updateSql.append(" ,lastLoginIp = :lastLoginIp ");
		}
		if (!StringUtil.isNull(u.getAccoName())) {
			updateSql.append(" ,accoName = :accoName ");
		}
		if (!StringUtil.isNull(u.getAccoNumb())) {
			updateSql.append(" ,accoNumb = :accoNumb ");
		}
		if (!StringUtil.isNull(u.getBankName())) {
			updateSql.append(" ,bankName = :bankName ");
		}
		if (!StringUtil.isNull(u.getMoreInfo())) {
			updateSql.append(" ,moreInfo = :moreInfo ");
		}
		if (!StringUtil.isNull(u.getPoint())) {
			updateSql.append(" ,point = :point ");
		}
		if (!StringUtil.isNull(u.getDrawPass())) {
			updateSql.append(" ,drawPass = :drawPass ");
		}
		if (!StringUtil.isNull(u.getPassword())) {
			updateSql.append(" ,password = :password ");
		}
		if (!StringUtil.isNull(u.getPId())) {
			updateSql.append(" ,PId = :PId ");
		}
		if (!StringUtil.isNull(u.getNickName())) {
			updateSql.append(" ,nickName = :nickName ");
		}
		if (!StringUtil.isNull(u.getQq())) {
			updateSql.append(" ,qq = :qq ");
		}
		if (!StringUtil.isNull(u.getEmail())) {
			updateSql.append(" ,email = :email ");
		}
		if (!StringUtil.isNull(u.getUserType())) {
			updateSql.append(" ,userType = :userType ");
		}
		if (!StringUtil.isNull(u.getRewardPoint())) {
			updateSql.append(" ,rewardPoint = :rewardPoint ");
		}
		if (!StringUtil.isNull(u.getDescrip())) {
			updateSql.append(" ,descrip = :descrip ");
		}
		if (!StringUtil.isNull(u.getEnsureMoney())) {
			updateSql.append(" ,ensureMoney = :ensureMoney ");
		}
		if (!StringUtil.isNull(u.getDividedPoint())) {
			updateSql.append(" ,dividedPoint = :dividedPoint ");
		}
		if (!StringUtil.isNull(u.getIsFreeze())) {
			updateSql.append(" ,isFreeze = :isFreeze ");
		}
		if (!StringUtil.isNull(u.getLowerPoint())) {
			updateSql.append(" ,lowerPoint = :lowerPoint ");
		}
		if (!StringUtil.isNull(u.getLastLoginTag())) {
			updateSql.append(" ,lastLoginTag = :lastLoginTag ");
		}
		if (!StringUtil.isNull(u.getLimitOdds())) {
			updateSql.append(" ,limitOdds = :limitOdds ");
		}
		if (!StringUtil.isNull(u.getLimitTime())) {
			updateSql.append(" ,limitTime = :limitTime ");
		}
		if (!StringUtil.isNull(u.getFirstRechTime())) {
			updateSql.append(" ,firstRechTime = :firstRechTime ");
		}
		if (!StringUtil.isNull(u.getFirstRechValue())) {
			updateSql.append(" ,firstRechValue = :firstRechValue ");
		}
		// where条件
		String whereSql = " where 1 = 1 ";
		if (!StringUtil.isNull(userId)) {
			whereSql = whereSql + " and id = :userId ";
			map.put("userId", userId);
		}
		if (!StringUtil.isNull(userName)) {
			whereSql = whereSql + " and userName = :userName ";
			map.put("userName", userName);
		}

		super.getSession().createQuery(updateSql.toString() + whereSql).setProperties(u)
				.setProperties(map).executeUpdate();

	}

	public void updateUserInfoPids(Integer id, Integer pid) {
		String updateSql = " update UserInfo set pId = ? where pId = ? ";
		super.getSession().createQuery(updateSql).setParameter(0, pid).setParameter(1, id)
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getTotalTeamsMeg(String userId) throws Exception {
		Map<String, Object> paras = new HashMap<String, Object>();

		String date = DateUtil.getCurDatetime(null);
		String today = DateUtil.getStartTime(date);
		String todayTime = DateUtil.getEndTime(date);
		String curMonthStart = DateUtil.getCurDatetime("yyyy-MM") + "-01 02:00:00";
		String lastMonthStart = DateUtil.getLastMonth("yyyy-MM") + "-01 02:00:00";
		String curMonthEnd = DateUtil.getCurDatetime(null);
		String lastMonthEnd = DateUtil.getEndTime(DateUtil.getLastMonth(null));
		log.info("相关日期：today:" + today + ",todayTime:" + todayTime + ",curMonthStart:"
				+ curMonthStart + ",lastMonthStart:" + lastMonthStart + ",curMonthEnd:"
				+ curMonthEnd + ",lastMonthEnd:" + lastMonthEnd);
		paras.put("today", today);
		paras.put("todayTime", todayTime);
		paras.put("curMonthStart", curMonthStart);
		paras.put("lastMonthStart", lastMonthStart);
		paras.put("curMonthEnd", curMonthEnd);
		paras.put("lastMonthEnd", lastMonthEnd);

		paras.put("id", userId);
		paras.put("curTime", DateUtil.getTime(null));
		Object obj = super.getSession().createSQLQuery("select getChildLst(?) result from dual")
				.setParameter(0, userId).uniqueResult();
		List<Integer> ids = new ArrayList<Integer>();
		if (!StringUtil.isNull(obj)) {
			String[] objs = obj.toString().split(",");
			for (String string : objs) {
				ids.add(Integer.parseInt(string));
			}
		}
		// paras.put("idList", ids);
		Map<String, Object> map1 = (Map<String, Object>) super
				.getSession()
				.createSQLQuery(
						"SELECT "
								+ " (select IFNULL(sum(g.draw_amount),0) from money_log g where g.user_id in ( :idList ) and g.status = 1 and g.apply_time >= :today ) drawAmout ,"
								+ " (select IFNULL(sum(e.amount),0) from rech_log e where e.user_id in ( :idList ) and e.state = 1 and e.rech_time  >= :today ) rechAmout ,"
								+ " (select count(fo.id) FROM user_info fo where  fo.id in (:idList)) teamCount,"
								+ " (select IFNULL(sum(f.money),0) FROM user_info f where f.id in (:idList) ) teamAmount ,"
								+ " (select count(ui.id)  from user_info ui where  ui.id in (:idList)  and last_login_time > :curTime) onlineCount,"
								// 投注返点
								// +
								// "(SELECT IFNULL(SUM(rde30.return_point),0) FROM order_info rde30 WHERE  rde30.user_id in (:idList) AND rde30.expiry_time >= :today  AND rde30.state=1) returnToday,"
								// 代理返点
								// +
								// "(SELECT IFNULL(SUM(rde31.agent_point_amount),0) FROM order_info rde31 WHERE  rde31.user_id in (:idList) AND rde31.expiry_time >= :today   AND rde31.state=1) returnAgentToday,"
								// +
								// "(SELECT IFNULL(SUM(std.change_amount),0) FROM trade_recode std WHERE  std.user_id = :id AND std.trade_time >= :today   AND std.change_type=13) returnAgentTodaySelf,"
								// +
								// "(SELECT IFNULL(SUM(rde32.win_amount),0) FROM order_info rde32 WHERE  rde32.user_id in (:idList) AND rde32.expiry_time >= :today   AND rde32.state=1) winAmountToday,"
								// +
								// " (select IFNULL(sum(orde.bet_amount),0) from order_info orde where  orde.user_id in (:idList) and orde.expiry_time >= :today and orde.state=1) betAmouToday,"
								// +
								// " (select -IFNULL(sum(rde.bet_amount),0)+IFNULL(sum(rde.win_amount),0)+IFNULL(sum(rde.return_point),0) +IFNULL(SUM(rde.agent_point_amount),0)  from order_info rde where rde.user_id in (:idList) and rde.expiry_time >= :today and rde.state=1 ) profitToday,"
								// 充值
								+ "(SELECT IFNULL(SUM(e2.amount),0) FROM rech_log e2 WHERE  e2.user_id in (:idList) AND e2.state = 1 AND e2.rech_time  >= :curMonthStart AND e2.rech_time <= :curMonthEnd) curMonthRecharge ,"
								// 取款
								+ "(SELECT IFNULL(SUM(g2.draw_amount),0) FROM money_log g2 WHERE g2.user_id in (:idList) AND g2.status = 1 AND   g2.apply_time >= :curMonthStart AND g2.apply_time <= :curMonthEnd ) curMonthWithdraws ,"
								+ " (select IFNULL(sum(e.amount),0) from rech_log e where  e.user_id in (:idList) and e.state = 1 ) totalRecharge ,"
								+ " (select IFNULL(sum(g.draw_amount),0) from money_log g where g.user_id in (:idList) and g.status = 1 )  totalWithdraws ,"
								// +
								// " (select ifnull(sum(d.change_amount),0) from trade_recode d where d.user_id IN ( :idList)  and d.trade_time >= :today and d.change_type in (7,23)) todayActivity,"
								// 充值
								+ "(SELECT IFNULL(SUM(e2.amount),0) FROM rech_log e2 WHERE  e2.user_id in (:idList) AND e2.state = 1 AND e2.rech_time  >= :lastMonthStart AND e2.rech_time <= :lastMonthEnd) lastMonthRecharge ,"
								// 取款
								+ "(SELECT IFNULL(SUM(g2.draw_amount),0) FROM money_log g2 WHERE  g2.user_id in (:idList) AND g2.status = 1 AND   g2.apply_time >= :lastMonthStart AND g2.apply_time <= :lastMonthEnd ) lastMonthWithdraws "
								+ " from user_info r where r.id = :id").setProperties(paras)
				.setParameterList("idList", ids)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		Map<String, Object> map2 = (Map<String, Object>) super
				.getSession()
				.createSQLQuery(
						"SELECT "
								// 投注返点
								+ "(SELECT IFNULL(SUM(rde30.return_point),0) FROM order_info rde30 WHERE  rde30.user_id in (:idList) AND rde30.expiry_time >= :today  AND rde30.state=1) returnToday,"
								// 代理返点
								+ "(SELECT IFNULL(SUM(rde31.agent_point_amount),0) FROM order_info rde31 WHERE  rde31.user_id in (:idList) AND rde31.expiry_time >= :today   AND rde31.state=1) returnAgentToday,"
								+ "(SELECT IFNULL(SUM(std.change_amount),0) FROM trade_recode std WHERE  std.user_id = :id AND std.trade_time >= :today   AND std.change_type=13) returnAgentTodaySelf,"
								+ "(SELECT IFNULL(SUM(rde32.win_amount),0) FROM order_info rde32 WHERE  rde32.user_id in (:idList) AND rde32.expiry_time >= :today   AND rde32.state=1) winAmountToday,"
								+ " (select IFNULL(sum(orde.bet_amount),0) from order_info orde where  orde.user_id in (:idList) and orde.expiry_time >= :today and orde.state=1) betAmouToday,"
								+ " (select -IFNULL(sum(rde.bet_amount),0)+IFNULL(sum(rde.win_amount),0)+IFNULL(sum(rde.return_point),0) +IFNULL(SUM(rde.agent_point_amount),0)  from order_info rde where rde.user_id in (:idList) and rde.expiry_time >= :today and rde.state=1 ) profitToday,"
								+ " (select ifnull(sum(d.change_amount),0) from trade_recode d where d.user_id IN ( :idList)  and d.trade_time >= :today and d.change_type in (7,23)) todayActivity "
								+ " from user_info r where r.id = :id").setProperties(paras)
				.setParameterList("idList", ids)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		map1.putAll(map2);
		return map1;
	}

	public List<Integer> getSpecialUser() {
		return super
				.getSession()
				.createSQLQuery(
						"SELECT id FROM user_info WHERE user_type = 2 OR `is_protect` = 1 OR p_id = 0 ")
				.list();
	}

	public Integer getUserByAccoName(String accountName,Integer platId) {
		List<Integer> l = super.getSession()
				.createSQLQuery("SELECT id FROM user_info WHERE acco_name = ? and plat_id = ? ")
				.setParameter(0, accountName).setParameter(1, platId).list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public Double getMaxChildPoint(Integer id) {
		String sql = "SELECT MAX(`reward_point`) FROM user_info WHERE p_id = ? ";
		Number max = (Number) super.getSession().createSQLQuery(sql).setParameter(0, id)
				.uniqueResult();
		if (max != null) {
			return max.doubleValue();
		} else {
			return null;
		}
	}

	public List<Integer> getAgentIdList() {
		return super.getSession()
				.createSQLQuery("SELECT id FROM user_info WHERE user_type = 1 and is_stop = 0 ")
				.list();
	}

	public Integer getChildsByPoint(Integer userId, Double rewardBegin, Double rewardEnd,
			String beginDate) {
		String sql = " SELECT COUNT(id) FROM user_info WHERE  `regist_time` >= :beginDate AND p_id = :userId   AND `reward_point` >= :rewardBegin AND `reward_point` <= :rewardEnd ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("rewardBegin", rewardBegin);
		map.put("rewardEnd", rewardEnd);
		map.put("beginDate", beginDate);
		Integer count = ((Number) super.getSession().createSQLQuery(sql).setProperties(map)
				.uniqueResult()).intValue();
		return count;
	}

	public Double getAllMoney(Integer platId) {
		String sql = " SELECT ifnull(SUM(money),0) money FROM user_info u WHERE u.`is_protect` = 0 AND is_stop = 0 AND `user_type` != 2 AND `p_id` !=0  and plat_id = ? ";
		Double d = ((Number) super.getSession().createSQLQuery(sql).setParameter(0, platId).uniqueResult()).doubleValue();
		return d;
	}

	public List<Map<String, Object>> getAgentTree(Integer userId) {
		String sql = "SELECT u1.id,CONCAT(IFNULL(u11.`user_name`,''),'->',IFNULL(u10.`user_name`,''),'->',IFNULL(u9.`user_name`,''),'->',IFNULL(u8.`user_name`,''),'->',IFNULL(u7.`user_name`,''),'->',IFNULL(u6.`user_name`,''),'->',IFNULL(u5.`user_name`,''),'->',IFNULL(u4.`user_name`,''),'->',IFNULL(u3.`user_name`,''),'->' ,IFNULL(u2.`user_name`,'')) agentTree FROM user_info  u1 "
				+ " LEFT JOIN user_info u2 ON u1.`p_id` = u2.`id` "
				+ "LEFT JOIN user_info u3 ON u2.`p_id` = u3.`id` "
				+ "LEFT JOIN user_info u4 ON u3.`p_id` = u4.`id` "
				+ "LEFT JOIN user_info u5 ON u4.`p_id` = u5.`id` "
				+ "LEFT JOIN user_info u6 ON u5.`p_id` = u6.`id` "
				+ "LEFT JOIN user_info u7 ON u6.`p_id` = u7.`id` "
				+ "LEFT JOIN user_info u8 ON u7.`p_id` = u8.`id` "
				+ "LEFT JOIN user_info u9 ON u8.`p_id` = u9.`id` "
				+ "LEFT JOIN user_info u10 ON u9.`p_id` = u10.`id` "
				+ "LEFT JOIN user_info u11 ON u10.`p_id` = u11.`id`" + " WHERE u1.user_type <> 2  ";
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNull(userId)) {
			sql = sql + " and u1.id = :userId ";
			map.put("userId", userId);
		}
		List<Map<String, Object>> list = super.getSession().createSQLQuery(sql).setProperties(map)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	public List<Map<String, Object>> getAgentTreeByIds(List<Integer> userIds) {
		String sql = "SELECT u1.id,CONCAT(IFNULL(u11.`user_name`,''),'->',IFNULL(u10.`user_name`,''),'->',IFNULL(u9.`user_name`,''),'->',IFNULL(u8.`user_name`,''),'->',IFNULL(u7.`user_name`,''),'->',IFNULL(u6.`user_name`,''),'->',IFNULL(u5.`user_name`,''),'->',IFNULL(u4.`user_name`,''),'->',IFNULL(u3.`user_name`,''),'->' ,IFNULL(u2.`user_name`,'')) agentTree FROM user_info  u1 "
				+ " LEFT JOIN user_info u2 ON u1.`p_id` = u2.`id` "
				+ "LEFT JOIN user_info u3 ON u2.`p_id` = u3.`id` "
				+ "LEFT JOIN user_info u4 ON u3.`p_id` = u4.`id` "
				+ "LEFT JOIN user_info u5 ON u4.`p_id` = u5.`id` "
				+ "LEFT JOIN user_info u6 ON u5.`p_id` = u6.`id` "
				+ "LEFT JOIN user_info u7 ON u6.`p_id` = u7.`id` "
				+ "LEFT JOIN user_info u8 ON u7.`p_id` = u8.`id` "
				+ "LEFT JOIN user_info u9 ON u8.`p_id` = u9.`id` "
				+ "LEFT JOIN user_info u10 ON u9.`p_id` = u10.`id` "
				+ "LEFT JOIN user_info u11 ON u10.`p_id` = u11.`id`" + " WHERE u1.user_type <> 2  ";
		Map<String, Object> map = new HashMap<String, Object>();
		sql = sql + " and u1.id in (:userId) ";
		List<Map<String, Object>> list = super.getSession().createSQLQuery(sql)
				.setParameterList("userId", userIds)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	public void updateqqlimiit(Integer userId, Integer state) {
		String sql = "update UserInfo u set u.qqLimit = ? where u.id = ? ";
		super.getSession().createQuery(sql).setParameter(0, state).setParameter(1, userId).executeUpdate();
		
	}

	public Integer countByIp(String ip,String time) {
		String sql = "select ifnull(count(id),0) from  User_Info u where regist_ip like ? and regist_time >= ?";
		Integer s =  ((BigInteger) super.getSession().createSQLQuery(sql).setParameter(0, "%"+ip+"%").setParameter(1,time).uniqueResult()).intValue();
		return s;
	}
}
