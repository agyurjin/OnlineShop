package edu.onlineshop.optimizer;

import java.util.List;

import edu.onlineshop.dal.repository.impl.OrderDAO;

public class Optimize {

	private List<Points> points;
	private OrderDAO orderDaoImpl;
	
	public Optimize(List<Points> points, OrderDAO orderDaoImpl) {
		this.points = points;
		this.orderDaoImpl = orderDaoImpl;
	}
	
	public void optimization() {
		
		int index = 0;
		double latitude = 0;
		double longitude = 0;
		double min = 10e8;
		double value = 0;

		while(!points.isEmpty()){
			for(int i = 0; i < points.size(); i++) {
				value = Math.sqrt( Math.pow((points.get(i).getLatitude() - latitude), 2) + 
						Math.pow((points.get(i).getLongitude() - longitude), 2) );
				if(value <= min) {
					index = i;
					latitude = points.get(i).getLatitude();
					longitude = points.get(i).getLongitude();
					min = value;
				}
			}
			System.out.println(points.get(index).toString());
			orderDaoImpl.updateOrderStatus(points.get(index).getId(), "Delivered");
			points.remove(index);
		}
	}
}