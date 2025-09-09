package com.tap.Dao;


	import java.util.List;

import com.tap.models.Menu;


	public interface MenuDao {
	 
		void addMenu(Menu menu);
		
		int updateMenu(Menu menu);
		int deleteMenu(int id);
		
		Menu getMenu(int id);
		List<Menu>getAllMenus(int restaurantID);


}
