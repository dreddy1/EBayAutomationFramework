package pages.android;

import logger.Log;
import objects.android.EBaySearchScreen;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * contains all methods to login on android app
 */
public class EBaySearch {

    EBaySearchScreen eBaySearchScreen;
    String productName;
    String productPrice;

    public EBaySearch(WebDriver driver) {
        eBaySearchScreen = new EBaySearchScreen(driver);
    }

    /**
     * method to search for item
     *
     * @param searchString
     *            - string to be searched
     */
    public void searchForItem(String searchString) throws InterruptedException {

        Log.info("Step - Search for an Item");
        int elementCount = 0, selectElementNumber = 0;
        try 
        {
            String prod_Name_text, prod_Price_text;
            WebElement prod_Name = null, prod_Price = null;
            WebElement searchBox = eBaySearchScreen.findElement(eBaySearchScreen.homePageSearchBox);
            if(searchBox!=null)
            {
                searchBox.click();
                searchBox = eBaySearchScreen.findElement(eBaySearchScreen.homePageSearchBox);
                if(searchBox!=null)
                {
                    searchBox.sendKeys(searchString);
                    
                    List<WebElement> elements = eBaySearchScreen.findElements(eBaySearchScreen.productList);
                    if(elements!=null)
                    {
                        elementCount = elements.size();
                        if (elementCount > 1) 
                        {
                            selectElementNumber = (elementCount - 1) / 2;
                            elements.get(selectElementNumber).click();
                            
                            prod_Name = eBaySearchScreen.findElement(eBaySearchScreen.productDetailsName);
                            prod_Price = eBaySearchScreen.findElement(eBaySearchScreen.productDetailsName);
                            
                            if(prod_Name != null && prod_Price != null)
                            {
                                prod_Name_text = prod_Name.getText();
                                prod_Price_text = prod_Price.getText();
                                Log.info("Product Name:" + prod_Name_text + "  Prodcut Cost:" + prod_Price_text);
                            }
                            else
                            {
                                Log.info("Product Name & Prodcut Cost are not displayed");
                            }
                        }
                    }
                    else
                    {
                        Log.info("No Products displayed for the search string " + searchString);
                    }
                }
            }
        }
        catch (Exception e) 
        {
            Log.logError(getClass().getName(), "Search for an item", "Unable to select the product");
        }       
    }

    /**
     * method to add item to cart
     */
    public void addItemToCart() throws InterruptedException {

        Log.info("Step - Add Item to Cart");
        
        try{
            WebElement addToCart_btn = eBaySearchScreen.findElement(eBaySearchScreen.productDetailsAddToCart);
            if (addToCart_btn != null) {
                addToCart_btn.click();
                Log.info("Add item to cart success");
            }
            else{
                Log.info("Add item to cart failed");
            }
        }catch (Exception e) {
            Log.logError(getClass().getName(), "add Item to Cart", "Unable to add item to cart");
        }
    }

    /**
     * method to search for item
     *
     * @param productDescription
     *            - product description to be validated
     */
    public boolean verifyProductDetailsInCart(String productDescription) throws InterruptedException {

        Log.info("Step -Verify Product Name, Cost and Description");
        
        boolean result = false;
        WebElement elementPrice;
        String textOnELement;      
        try {
            List<WebElement> elements = eBaySearchScreen.findElements(eBaySearchScreen.cartProductName);
            for (WebElement element : elements) {
                textOnELement = element.getText();
                if (textOnELement.contains(productName) && textOnELement.contains(productDescription)) {
                    elementPrice = eBaySearchScreen.findElement(eBaySearchScreen.cartProductPrice);
                    Assert.assertEquals(element.getText(), productDescription, "Verified Product description");
                    Assert.assertEquals(elementPrice.getText(), this.getProductPrice(), "Verified Product Price");
                    result = true;
                    break;
                }
            }

            if (!result) {
                Log.info("Product details displayed are not correct in the cart");
            }
        }
        catch (Exception e) {
            Log.logError(getClass().getName(), "Verify Product", "Product Details verification failed");
        }
        return result;
    }

    /**
     * method to place an order
     */
    public void placeAnOrder() throws InterruptedException {
        
        Log.info("Step - Place an Order");
        try {
            // click on Continue button
            eBaySearchScreen.click(eBaySearchScreen.continueButton);
            // select an earlier saved credit card
            eBaySearchScreen.click(eBaySearchScreen.selectExistingCard);
            // click on Pay button to make payment
            eBaySearchScreen.click(eBaySearchScreen.pay);
        }
        catch (Exception e) {
            Log.logError(getClass().getName(), "Place an order", "Unable to pay");
        }
    }

    /**
     * method to get product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * method to set product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * method to get product price
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * method to set product price
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
