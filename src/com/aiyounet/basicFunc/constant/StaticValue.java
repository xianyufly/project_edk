package com.aiyounet.basicFunc.constant;

import java.util.HashMap;
import java.util.Map;

public class StaticValue {
	/**
	 * session 中保存验证码图片的名称
	 */
	public static final String AUTHCODE_IMAGE = "authcodeImage";

	/**
	 * session 中保存验证码的名称
	 */
	public static final String AUTHCODE = "authcode";
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String RESULT = "result";
	public static final String MSG = "msg";
	public static final String ROWS = "rows";
	public static final String RETSING = "retSign";
	public static final String USER_TOKEN = "userToken";
	public static final String IS_WINNING = "isWinning";
	
	


	public static final String PLAT_NAME_MAP = "platNameMap";

	public static final String SELECT_DATA_MAP = "selectDataMap";
	////////////////////////////////////////////////////////////////////////////////////////
	public static final String FTL_PATH = "/client/ftl";// 模版路径
	public static final String HTML_PATH = "/client/html";// html路径
	public static final String JS_PATH = "/client/js";// html路径

//	public static final String KEY_INDEX_LABEL = "index_label_keys";// 首页的标签(TOP30,银行背景，国资背景……);
//	public static final String INDEX_PLAT_LIST = "indexPlatList";// 首页key
	public static final Integer INDEX_ROWS = 8;// 首页显示项目条数

	/**
	 * 首页广告数据
	 */
	public static final String INDEX_AD_KEY = "index_ad";
	/**
	 * 首页平台信息,redis中的key
	 */
	public static final String INDEX_PLAT_LIST_KEY = "index_platList";
	/**
	 * 在mysql数据库数据字典表中，id
	 */
	public static final int INDEX_PLAT_LIST_ID = 128;

	/**
	 * 平台首页platIndex/platIndex.ftl
	 */
	public static final String PLATINDEX_PLAT_LABEL_KEY = "platIndex_platLbael";
	
	public static final String PROJECTSEARCH_PROJECT_LABEL_KEY = "platSearch_projectLbael";
	/**
	 * 平台首页显示标签分类id
	 */
	public static final Integer PLATINDEX_PLAT_LABEL_ID = 133;
	public static final Integer PROJECTSEARCH_PROJECT_LABEL_ID = 267;
	public static final Integer PROJECTSEARCH_PROJECT_PLAT_LABEL_ID = 268;
	/**
	 * 平台首页-平台排行
	 */
	public static final Integer PLATINDEX_PLAT_ORDER_ID = 158;

	public static final String PLATINDEX_PLAT_ORDER_KEY = "platIndex_platOrder";

	public static final String KEY_WORD = "关键词";
	/**
	 * 首页下拉框，理财收益
	 */
	public static final Integer SELECT_DATA_PROFIT_ID = 269;	/**
	 * 首页下拉框，理财期限
	 */
	public static final Integer SELECT_DATA_DATE_ID = 270;

	public static final Object BASE_PATH = "http://127.0.0.1/wangdai/";

	public static final Integer PROJECTSEARCH_ALPHABET_LABEL_ID = 163;

	public static final String PROJECTSEARCH_ALPHABET_LABEL_KEY = "projectSearc_Alphabet_label";

	public static final String INDEX_PRODUCT_RATE_LIST_KEY = "index_productRateList";

	public static final String INDEX_PRODUCT_DATE_LIST_KEY = "index_productDateList";

	public static final String INDEX_PRODUCT_HOT_LIST_KEY = "index_productHotList";

	public static final String INDEX_PRODUCT_SCHEDULE_LIST_KEY = "index_projectScheduleList";

	public static final String PRODUCT_ALL_KEY = "productAllList";

	public static final String PRODUCT_NAME_MAP = "productNameMap";

	public static final String PRODUCT_NEW_NUMBER = "productNewNumber";
	//存储一些单独字段的key
	public static final String HASH_MAP_KEY = "hashMapKey";

	public static final Integer PLATINDEX_HOT_PLAT_ID = 292;

	public static final String PLATINDEX_HOT_PLAT_KEY = "platIndexHotPlat";

	public static final String LABEL_PLAT_MAP = "labelPlatMap";

	public static Map<String, Object> getReturnMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", StaticValue.KEY_WORD); 
		map.put("basePath",  StaticValue.BASE_PATH);
		return map;
	}
}
