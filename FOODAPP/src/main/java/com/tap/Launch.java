
package com.tap;

import java.util.List;
import java.util.Scanner;

//import com.sun.tools.javac.util.List;
import com.tap.DaoImpl.UserImpl;
import com.tap.models.User;

public class Launch {
	static Scanner sc=null;
	public static void main(String[] args) {
		userOperations();
		
	}

	private static void userOperations() {
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.Add User");
			System.out.println("2.Update User");
			System.out.println("3.Delete User");
			System.out.println("4.Get User");
			System.out.println("5.GetAll User");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: addUser();break;
			case 2: updateUser();break;
			case 3: deleteUser();break;
			case 4: getUser();break;
			case 5: getAllUser();break;
			default:
				System.exit(0);
				break;
			}
		}
	}

	private static void updateUser() {
		System.out.println("Enter the id to Update details:");
		int id = sc.nextInt();

		UserImpl udi=new UserImpl();
		User user = udi.getUser(id);
		System.out.println("We are Updating: \n Enter the value:");
		String value = sc.next();
		user.setName(value);

		int res = udi.updateUser(user);
		if(res==1) {
			System.out.println("Succesful");
		}
		else {
			System.out.println("Failed");
		}
	}

	private static void getAllUser() {
		UserImpl udi = new UserImpl();

		List<User> list = udi.getAllUsers();
		//		System.out.println(list);
		for (User user : list) {
			System.out.println(user);
		}
	}

	private static void deleteUser() {
		System.out.println("Enter the id which user data want to delete:");
		int id = sc.nextInt();

		UserImpl udi = new UserImpl();
		int res = udi.deleteUser(id);
		if(res==1) {
			System.out.println("User data deleted Successfully");
		}
		else {
			System.out.println("User data is not deleted");
		}
	}

	private static void getUser() {
		System.out.println("Enter the id to get User details:");
		int id = sc.nextInt();

		UserImpl u=new UserImpl();
		User user = u.getUser(id);

		
		System.out.println(user);
	}


	private static void addUser() {
		System.out.println("Adding User");
		System.out.println("Enter the User Id:");
		int userId = sc.nextInt();
		System.out.println("Enter the Name:");
		String name = sc.next();
		System.out.println("Enter the User-Name:");
		String username = sc.next();
		System.out.println("Enter the Password:");
		String password = sc.next();
		System.out.println("Enter the Email:");
		String email = sc.next();
		System.out.println("Enter the Phone No:");
		String phone = sc.next();
		System.out.println("Enter the Address:");
		String address = sc.next();
		System.out.println("Enter the role:");
		String role = sc.next();
		//		String name = sc.next();


		User user=new User(userId, name, username, password, email, userId, address, role);

		UserImpl u=new UserImpl();
		u.addUser(user);
		System.out.println(user);
	}
}
