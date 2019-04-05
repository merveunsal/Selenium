package com.automationProject.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automationProject.page.base.BasePage;

public class MainPage extends BasePage {

	public MainPage() {
		super();
	}

	private By search_box = By.id("twotabsearchtextbox");
	private By suggest_product = By.cssSelector("#suggestions-template div[data-keyword='iphone x']");
	
	public ListPage searchAndSelectProduct(String product) {
		sendTextToElement(search_box, product, 20);
		clickWebElement(suggest_product, 20);
		return new ListPage();
	}
	

}
