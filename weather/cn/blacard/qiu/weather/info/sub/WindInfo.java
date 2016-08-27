package cn.blacard.qiu.weather.info.sub;

import cn.blacard.qiu.weather.info.base.BaseInfo;

public class WindInfo extends BaseInfo{
/**
	 * 
	 */
	private static final long serialVersionUID = 7538469703396440002L;
	
	/**
	 * ���򡣵�λ�Ƕȡ���������Ϊ0�ȣ�˳ʱ�����ӵ�360�ȡ�
	 */
	private double direction;
	/**
	 * ���٣��������ǹ���ÿСʱ
	 */
	private double speed;
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public WindInfo(double direction, double speed) {
		super();
		this.direction = direction;
		this.speed = speed;
	}
	public WindInfo() {
		super();
	}
	
	
	
}
