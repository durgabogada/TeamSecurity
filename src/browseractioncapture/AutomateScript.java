/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browseractioncapture;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author nvlakshmidurgab
 */
public class AutomateScript {

    public static String scriptExecute(ArrayList<String> list, String browserType) {
        ChromeDriver cdriver;
        FirefoxDriver fdriver;
        System.out.println("browsertype"+browserType);
        try {
            if (browserType.equalsIgnoreCase("Chrome")) {
                String url = null;
                cdriver = new ChromeDriver();
                for (int k = 0; k < list.size(); k++) {
                    String[] splitValue = list.get(k).split(",");
                    String cmd = splitValue[0];
                    String page = splitValue[1];
                    String object = null;
                    if (splitValue.length > 2) {
                        object = splitValue[2];
                    }

                    String value = null;
                    if (splitValue.length > 3) {
                        value = splitValue[3];
                    }
                    System.out.println("cmd " + cmd + " page " + page + " object " + "value" + value);

                    if (cmd.equalsIgnoreCase("click")) {
                        WebElement element = getElement(cdriver, page, object);
                        element.click();

                    } else if (cmd.equalsIgnoreCase("getUrl")) {
                        cdriver.get(page);
                    } else if (cmd.equalsIgnoreCase("enter")) {
                        WebElement element = getElement(cdriver, page, object);
                        element.sendKeys(value);
                        url = cdriver.getCurrentUrl();
                    }

                }
                // for response retrieval 
                System.out.println("URL" + cdriver.getCurrentUrl() + "cookies" + cdriver.manage().getCookies());
                switchTabChrome(cdriver, url);
            } else if (browserType.equalsIgnoreCase("firefox")) {
                String url = null;
                fdriver = new FirefoxDriver();
                for (int k = 0; k < list.size(); k++) {
                    String[] splitValue = list.get(k).split(",");
                    String cmd = splitValue[0];
                    String page = splitValue[1];
                    String object = null;
                    if (splitValue.length > 2) {
                        object = splitValue[2];
                    }

                    String value = null;
                    if (splitValue.length > 3) {
                        value = splitValue[3];
                    }
                    System.out.println("cmd " + cmd + " page " + page + " object " + "value" + value);

                    if (cmd.equalsIgnoreCase("click")) {
                        WebElement element = getElement(fdriver, page, object);
                        element.click();

                    } else if (cmd.equalsIgnoreCase("getUrl")) {
                        fdriver.get(page);
                    } else if (cmd.equalsIgnoreCase("enter")) {
                        WebElement element = getElement(fdriver, page, object);
                        element.sendKeys(value);
                        url = fdriver.getCurrentUrl();
                    }

                }
                // for response retrieval 
                System.out.println("URL" + fdriver.getCurrentUrl() + "cookies" + fdriver.manage().getCookies());
                switchTabFireFox(fdriver,url);
               
            }

//            for (int k = 0; k < list.size(); k++) {
//                String[] splitValue = list.get(k).split(",");
//                String cmd = splitValue[0];
//                String page = splitValue[1];
//                String object = null;
//                if (splitValue.length > 2) {
//                    object = splitValue[2];
//                }
//
//                String value = null;
//                if (splitValue.length > 3) {
//                    value = splitValue[3];
//                }
//                System.out.println("cmd " + cmd + " page " + page + " object " + "value" + value);
//
//                if (cmd.equalsIgnoreCase("click")) {
//                    WebElement element = getElement(driver, page, object);
//                    element.click();
//                   
//                } else if (cmd.equalsIgnoreCase("getUrl")) {
//                    driver.get(page);
//                } else if (cmd.equalsIgnoreCase("enter")) {
//                    WebElement element = getElement(driver, page, object);
//                    element.sendKeys(value);
//                    url = driver.getCurrentUrl();
//                }
//                
//            }
//            // for response retrieval 
//            System.out.println("URL" +driver.getCurrentUrl() + "cookies" + driver.manage().getCookies());
//            switchTab(driver,url);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return "Session Management : Authentication related cookies are not propely handled after logout";
    }

    public static void switchTabChrome(ChromeDriver driver,String url) {
        System.out.println("url" + url);
        driver.get(url);
    }
    
    public static void switchTabFireFox(FirefoxDriver driver,String url) {
        System.out.println("url" + url);
        driver.get(url);
        
    }

    public static WebElement getElement(WebDriver driver, String page, String object) {
        By paramBy = null;
        WebElement element = null;
        try {
            if (page != null && object != null) {
                switch (page.toUpperCase()) {
                    case "XPATH":
                        paramBy = By.xpath(object);
                        break;
                    case "ID":
                        paramBy = By.id(object);
                        break;

                    case "CLASS":
                        paramBy = By.className(object);
                        break;

                    default:
                        return null;
                }
            }
            element = driver.findElement(paramBy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return element;
    }

}
