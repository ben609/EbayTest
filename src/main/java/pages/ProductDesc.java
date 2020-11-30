package pages;

import utilty.Reporting;
import utilty.Utility;


public class ProductDesc extends Utility{
	String DiscoverProduct= "//android.view.View[@text='Discover additional products']";
	String Close="//android.view.View[@text='close']";
	String cart="com.amazon.mShop.android.shopping:id/action_bar_cart";
	
	String Nametext="Add to Cart";
	String AddtoCart="//android.widget.Button[@text='Add to Cart']";
	String id="add-to-cart-button";
	String productname="com.amazon.mShop.android.shopping:id/item_title";
	String product="//*[@resource-id='title_feature_div']//*[@class='android.view.View']";
	
	
	/*Method to get Product Details  from Product Description page
	 * Attributes: Object of generating  Html Reports and screenshots
	 * Created By : Benarji Enamandala
	 */
	
public String getproductname(Reporting report) throws InterruptedException {

String name=null;
	
		Thread.sleep(10000);
		name=getText("xpath", product, report);
	

	return name;
}


/*Method to Add product to Cart by clicking on Add to Cart Button
 * Attributes: Object of generating  Html Reports and screenshots
 * Created By : Benarji Enamandala
 */
public  void addToCart(Reporting report) throws InterruptedException {
	
	Thread.sleep(5000);
	
	Scroll(Nametext,report);
	Thread.sleep(5000);
	waitForElementToBeClickable("xpath", AddtoCart, report);
	clickElement("xpath", AddtoCart,report);

	 
	
	
}
/*Method to click on Cart Button to Open Cart
 * Attributes: Object of generating  Html Reports and screenshots
 * Created By : Benarji Enamandala
 */
public void opencart(Reporting report) throws InterruptedException {
	
	Thread.sleep(15000);
		clickElement("id", cart,report);
		report.reportpass("cart clicked");
}

}
