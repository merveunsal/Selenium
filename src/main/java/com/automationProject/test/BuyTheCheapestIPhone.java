package com.automationProject.test;

import org.testng.annotations.Test;

import com.automationProject.base.BaseTest;
import com.automationProject.page.MainPage;

public class BuyTheCheapestIPhone extends BaseTest {

	private String product_name = "iPhone";
	
	@Test
	public void buyTheCheapestIPhone() {
		new MainPage().searchAndSelectProduct(product_name).selectSecondProduct().compareIPhonePrices();
	}
}
