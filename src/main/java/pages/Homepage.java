package pages;




import utilty.Reporting;
import utilty.Utility;

public class Homepage extends Utility {
	
	String Searchbox="com.amazon.mShop.android.shopping:id/rs_search_src_text";
	String productname="com.amazon.mShop.android.shopping:id/item_title";
	
	/*Method to Search for a Product By Entering Text in Search box
	 * Attributes: Object of Reporting event  passed , product name from Exceldata
	 * Created By : Benarji Enamandala
	 */
public void searcBox(String product ,Reporting report) throws InterruptedException {
	
		
		

	clickElement("id", Searchbox, report);
	Thread.sleep(5000);
	sendkeys("id", Searchbox,product,report);
	enter(report);
	Thread.sleep(15000);
	
// Repeating Above Code again due to App Redirection to Amazon Home page again  
	clickElement("id", Searchbox,report);
	Thread.sleep(5000);
	System.out.println();
	sendkeys("id", Searchbox,product,report);
	enter(report);
}
	
	

/*Method to Select random Product From Search  List
 * Attributes: Object of Reporting event  passed 
 * Created By : Benarji Enamandala
 */
	
public void selectProduct(Reporting report) {

	randSelection("id",productname ,report);
	report.reportpass("Selected Random Product Success");
	


	
}
	
}
