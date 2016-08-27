package cn.blacard.qiu.weather.info.sub;

import cn.blacard.qiu.weather.info.base.BaseInfo;

/**
@author  Blacard
���䣺blacard@163.com
@date ����ʱ�䣺2016��6��29�� ����9:30:05 
  */
public class LocalPrecipitationInfo extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2629232758283248882L;
	
	
	private String status;
	/**
	 * ��ˮǿ��
	 */
	private double intensity;
	/**
	 * ����Դ
	 */
	private String datasource;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getIntensity() {
		return intensity;
	}
	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	

}
