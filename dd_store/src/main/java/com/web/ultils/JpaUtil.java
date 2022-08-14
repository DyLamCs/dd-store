package com.web.ultils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
//	EntityManagerFactory được dùng để tạo ra một thể hiện của EntityManager
	private static EntityManagerFactory factory;

	public static EntityManager getEntityManager() {
		if(factory == null || factory.isOpen()) {
//			Tạo EntityManagerFactory chuẩn bị lập trình csdl, để kết nối csdl
			factory = Persistence.createEntityManagerFactory("dd_store");
		}
		return factory.createEntityManager();
	}
	
	public static void shutDown() {
		if (factory != null || factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
