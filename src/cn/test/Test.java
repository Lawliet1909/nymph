package cn.test;

import cn.blacard.nymph.date.ChineseCalendar;
import cn.blacard.nymph.entity.HighPrecisionIpPositioningEntity;
import cn.blacard.nymph.entity.base.LocationEntity;
import cn.blacard.nymph.net.tool.GeocodingTool;
import cn.blacard.nymph.net.tool.HighPrecisionIpPositioningTool;
import cn.blacard.nymph.net.tool.IPTool;
import cn.blacard.nymph.net.weather.Weather;
import net.sf.json.JSONObject;

/**
 * 
 * <h3>title:</h3>
 * <p>测试类</p>
 * @author Blacard
 * @createTime 下午2:21:40
 * @e_mail blacard@163.com
 * @phone 18037170703
 */
public class Test {
	
	/**
	 * 地理位置 转 经纬度
	 * @author Blacard
	 * @create 2016年12月20日 上午7:08:33
	 * @param args
	 */
	public static void main_address_to_location(String[] args) {
		
		LocationEntity location = GeocodingTool.addressToLocation("洛阳市");
		
		System.out.println("经纬度 ： "+location.toString());
	}
	/**
	 * 获取天气预报，测试
	 * @author Blacard
	 * @create 2016年12月20日 上午5:06:11
	 * @param args
	 */
	public static void main_weather(String[] args) {
		
		Weather weather = new Weather("洛阳");
	
		System.out.println(weather.getForecastWeather().getResult().getHourly().getDescription());
		System.out.println(weather.getRealtimeWeather().getResult().getSkycon().toString());
		System.out.println("当前温度："+weather.getRealtimeWeather().getResult().getTemperature()+" ℃");
//		System.out.println(weather.getForecastWeather().getResult().getDaily().getSkycon()[0].getValue());
	}
	
	/**
	 * IP 转 地址
	 * @author Blacard
	 * @create 2016年12月13日 上午11:51:33
	 * @param args
	 */
	public static void main(String[] args) {
//		HighPrecisionIpPositioningEntity entity = IPTool.getHighPrecisionIpPositioningByIP("58.34.140.86");
//		System.out.println(IPTool.getAddressByIp("115.51.96.159"));
		System.out.println(HighPrecisionIpPositioningTool.getLocationByIp("58.34.140.86").toString());
		
	}
	/**
	 * JSONObject转 Bean测试
	 * @author Blacard
	 * @create 2016年12月13日 上午10:29:00
	 * @param args
	 */
	public static void main_json_to_bean(String[] args) {
		JSONObject hehe = JSONObject.fromObject("{'content':{'location':{'lat':31.225112,'lng':121.443857},'locid':'8a6e357f270e6ef2688ab1a34ef2b89e','radius':30,'confidence':1.0},'result':{'error':161,'loc_time':'2016-12-13 10:07:34'}}");
		HighPrecisionIpPositioningEntity entity = (HighPrecisionIpPositioningEntity)JSONObject.toBean(hehe,HighPrecisionIpPositioningEntity.class);
		System.out.println(entity.getResult().getError());
	}
	/**
	 * 农历，公历转换测试
	 * @author Blacard
	 * @create 2016年12月13日 上午10:28:25
	 * @param args
	 */
	public static void main_nongli(String[] args) {
		String solar = ChineseCalendar.sCalendarLundarToSolar(2016, 10, 11);
		System.out.println(solar);
	}
}