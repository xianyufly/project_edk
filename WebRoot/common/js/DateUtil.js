
DateUtil = {
		// 格式化日期 date Date
		formatDate : function(date) {
			var month = (date.getMonth()+1).toString();
			var day = date.getDate().toString();
			if (date.getMonth() <= 9) {
				month = '0' + String(month);
			}
			if (date.getDate() <= 9)
				day = '0' + String(day);
			return date.getFullYear() + "-" + month + "-"
					+ day;
		},
		// 比较日期大小
		compareDate : function(start, end) {
			var arr = start.split("-");
			var starttime = new Date(arr[0], arr[1], arr[2]);
			var starttimes = starttime.getTime();

			var arrs = end.split("-");
			var lktime = new Date(arrs[0], arrs[1], arrs[2]);
			var lktimes = lktime.getTime();

			if (starttimes > lktimes) {
				return false;
			} else
				return true;

		},
		// 计算日期间隔
		Computation : function(sDate1, sDate2) { // sDate1和sDate2是2008-12-13格式
			var aDate, oDate1, oDate2, iDays;
			aDate = sDate1.split("-");
			oDate1 = new Date(aDate[0], aDate[1], aDate[2]); // 转换为12-13-2008格式
			aDate = sDate2.split("-");
			oDate2 = new Date(aDate[0], aDate[1], aDate[2]);
			iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); // 把相差的毫秒数转换为天数
			return iDays + 1;
		},
		// 计算时间间隔
		ComputationSec : function(sDate1, sDate2) { // sDate1和sDate2是Date
			var times = sDate1 - sDate2;
			if(times < 0){
				times = 0 ;
			}
			var m = times / 1000 / 60;
			var s = (times % (1000*60))/1000	
			var iDays = m+':'+s; // 把相差的毫秒数转换为天数
			return iDays;
		},
		// 计算相隔x天后的日期
		addDate : function(sDate1, day) {
			var aDate;
			aDate = sDate1.split("-");
			oDate1 = new Date(aDate[0], aDate[1]-1, aDate[2]); // 转换为12-13-2008格式
			oDate1 = oDate1.valueOf();
			oDate1 = oDate1 + day * 24 * 60 * 60 * 1000;
			oDate1 = new Date(oDate1);
			var month = (oDate1.getMonth()+1).toString();
			var day = oDate1.getDate().toString();
			if (oDate1.getMonth() <= 9) {
				month = '0' + String(month);
			}
			if (oDate1.getDate() <= 9)
				day = '0' + String(day);
			var date = oDate1.getFullYear() + "-" + month + "-" + day;
			return date;
		},
		//计算单天之后多少
		CurrentDateAddDay:function(n){
			 var s, d, t, t2;
			 t = new Date().getTime();
			    t2 = n * 1000 * 3600 * 24;
			    t+= t2;
			    d = new Date(t);
			    s = d.getUTCFullYear() + "-";
			    s += ("00"+(d.getUTCMonth()+1)).slice(-2) + "-";
			    s += ("00"+d.getUTCDate()).slice(-2);
			    return s;
		},
		//分割日期和 时间
		SplitDateAndTime:function(dateTime){
			var dateArray=dateTime.split(" ");
			return dateArray;
		}
};