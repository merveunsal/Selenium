package com.automationProject.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automationProject.page.base.BasePage;

public class ListPage extends BasePage {

	public ListPage() {
		super();
	}

	private By second_product = By.cssSelector("div[data-index='1'] h5");
	
	public ProductDetailPage selectSecondProduct() {
		clickWebElement(second_product, 30);
		return new ProductDetailPage();
	}
	

}
