package pages;

import utilty.Reporting;
import utilty.Utility;

public class CartPage extends Utility{
	String productname= "//*[@resource-id='activeCartViewForm']//*[@class='android.widget.Image']";
	String checkout="//android.widget.Button[@text='Proceed to checkout']";
	
	
	/*Method to compare Products Added in Cartpage equals Product Selected
	 * Attributes: Object of Reporting event  passed , xpath of Product name as String
	 * Created By : Benarji Enamandala
	 */
	public void compareProduct(String Product,Reporting report) {
		String Cartproductname=null;
		
		Cartproductname=getText("xpath", productname, report);
			verifyText(Cartproductname,Product , report);
			report.reportpass("Product matched");
		
	}
	
	/*Method to Click on Checkout Button
	 * Attributes: Object of Reporting event  passed 
	 * Created By : Benarji Enamandala
	 */
	
	public void checkout(Reporting report) {
		
		clickElement("xpath", checkout, report);
		report.reportpass("Navigated to checkout");
		
	}
	
}
