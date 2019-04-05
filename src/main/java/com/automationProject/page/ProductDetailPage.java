package com.automationProject.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automationProject.page.base.BasePage;

public class ProductDetailPage extends BasePage {

	public ProductDetailPage() {
		super();
	}

	private By other_sellers_price = By.xpath("//*[@data-brand=\"Apple\"]//span[@class='a-declarative']/div/div[1]/span[1]");

	List<Double> pricelist = new ArrayList<Double>();
	
	public void compareIPhonePrices() {
		
		List<WebElement> elements = getListOfElementsByLocator(other_sellers_price);
		
		for (WebElement element : elements) {
			String text = element.getText();
			text = text.split(",")[0];
			pricelist.add(Double.parseDouble(text));
		}
		Collections.sort(pricelist);
		System.out.println("list : " + pricelist);
		System.out.println("min value : " + pricelist.get(0));
		
		By min_price_add_basket_button = By.xpath("//*[@data-brand='Apple']//span[@class='a-declarative']/div/div[1]/span[1][contains(text()," + pricelist.get(0) + ")]/../../../..//a[text()='Sepete Ekle']");
		clickWebElement(min_price_add_basket_button, 30);
		
	}
}
