package cn.blacard.qiu.weather.info.base;

import java.util.Date;

import cn.blacard.qiu.weather.info.RealTimeWeatherResultInfo;
import cn.blacard.qiu.weather.info.sub.LocationInfo;

/**
@author  Blacard
���䣺blacard@163.com
@date ����ʱ�䣺2016��6��29�� ����10:26:51 
  */
public class BaseWeatherInfo extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2606746959222967023L;

	
	/**
	 * ״̬
	 */
	private String status;
	
	/**
	 * Ŀǰֻ֧�ּ������ģ�zh_CN��zh_SG�����������ģ�zh_TW��zh_HK����Ӣ�en_US��en_GB���ڲ�����
	 */
	private String lang;
	
	/**
	 * ������ʱ��
	 */
	private Date server_time;
	/**
	 * ʱ����ƫ���������綫�������� 28800 �룬ʹ������Ϊ��֧�����Ჴ�������Ĳ� 5 Сʱ 45 ���ӵĵ����������з������ƫ����
	 */
	private int tzshift;
	/**
	 * ��γ��
	 */
	private LocationInfo location;
	 /**
	  * Ŀǰֻ֧�����ƣ�metric���Ϳ�ѧ��������SI����Ӣ�ƻ��д�����
	  */
	private String unit;
	/**
	 * ���
	 */
	private RealTimeWeatherResultInfo result;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Date getServer_time() {
		return server_time;
	}
	public void setServer_time(Date server_time) {
		this.server_time = server_time;
	}
	public int getTzshift() {
		return tzshift;
	}
	public void setTzshift(int tzshift) {
		this.tzshift = tzshift;
	}
	public LocationInfo getLocation() {
		return location;
	}
	public void setLocation(LocationInfo location) {
		this.location = location;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public RealTimeWeatherResultInfo getResult() {
		return result;
	}
	public void setResult(RealTimeWeatherResultInfo result) {
		this.result = result;
	}
}
