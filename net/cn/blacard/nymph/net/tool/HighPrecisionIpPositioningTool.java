package cn.blacard.nymph.net.tool;

import cn.blacard.nymph.entity.HighPrecisionIpPositioningEntity;
import cn.blacard.nymph.entity.base.LocationEntity;
import cn.blacard.nymph.net.html.HtmlGet;
import net.sf.json.JSONObject;

/**
 * <h1>高精度IP定位</h1>
 * <a href="http://lbsyun.baidu.com/index.php?title=webapi/high-acc-ip">官网API文档:http://lbsyun.baidu.com/index.php?title=webapi/high-acc-ip</a><br/>
 * 
 * @author Blacard
 * @联系方式  邮箱：blacard@163.com <br/> 手机：18037170703
 * @Create 2016年12月13日 下午8:32:44
 */
public class HighPrecisionIpPositioningTool {
	
	//获取本类的静态实例化对象，本工具类只开放静态方法，非静态方法全部私有
	private static HighPrecisionIpPositioningTool tool = new HighPrecisionIpPositioningTool();
	
	/**
	 * 通过IP获取经纬度
	 * @author Blacard
	 * @create 2016年12月13日 下午9:06:48
	 * @param ip
	 * @return
	 */
	public static LocationEntity getLocationByIp(String ip){
		HighPrecisionIpPositioningEntity entity = tool.getHighPrecisionIpPositionByIp(ip);
		if(entity.getResult().getError()==161){
			return entity.getContent().getLocation();
		}else{
			System.out.println(tool.getClass().getName()+":通过IP获取经纬度是发生错误，错误码："+entity.getResult().getError());
			return null;
		}
	}
	
/* ==========================================
 * 			以下为私有方法
 * ==========================================
 */
	
	/**
	 * 获取百度返回的实体
	 * @author Blacard
	 * @create 2016年12月13日 下午9:33:59
	 * @param ip
	 * @return
	 */
	private HighPrecisionIpPositioningEntity getHighPrecisionIpPositionByIp(String ip){
		HtmlGet get = new HtmlGet();
		String result = get.getPage(createRequestUrl(ip));
		HighPrecisionIpPositioningEntity entity = (HighPrecisionIpPositioningEntity)JSONObject.toBean(JSONObject.fromObject(result), HighPrecisionIpPositioningEntity.class);
		return entity;
	}
	
	/**
	 * 
	 * @author Blacard
	 * @create 2016年12月13日 下午9:34:02
	 * @param ip
	 * @return
	 */
	private String createRequestUrl(String ip){
		return createRequestUrl(
				"http://api.map.baidu.com/highacciploc/v1",
				"pc",
				"yMOZ0v2ANY6UF0l6CNfVnVae",
				"bd09ll",
				ip);
	}
	
	/**
	 * 
	 * @author Blacard
	 * @create 2016年12月13日 下午9:34:10
	 * @param apiUrl
	 * @param qterm
	 * @param ak
	 * @param coord
	 * @param qcip
	 * @return
	 */
	private String createRequestUrl(String apiUrl,String qterm,String ak,String coord,String qcip){
		StringBuffer sb = new StringBuffer();
		//请求地址
		sb.append(apiUrl);
		//待定位终端类型,mb:移动设备，pc：固定设备
		sb.append("?qterm="+qterm);
		//开发者密钥
		sb.append("&ak="+ak);
		//返回坐标类型 bd09：百度墨卡托坐标，db09ll：百度经纬度坐标，gcj02：国测局经纬度坐标 
		sb.append("&coord="+coord);
		//待定位IP地址
		sb.append("&qcip="+qcip);
		return sb.toString();
	}
}
