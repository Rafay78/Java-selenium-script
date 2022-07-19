import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;



public class Mango_Test {
    WebDriver driver;
    //Declaring locator keys for jenna
    String srv_jenna = null;
    String BlogH_jenna = null;
    String ingH_jenna = null;
    String ing_jenna = null;
    String rel_recH_jenna = null;
    String p_jenna = null;
    String rel_blgs_jenna = null;
    String cukT_jenna = null;
    String rel_recipe_jenna = null;
    String rel_blog_jenna = null;
    String prpT_jenna = null;
    String related_blogs_new = null;


    //Declaring locator keys for mango
    String prpT_mango = null;
    String prp = null;
    String serve_m = null;
    String Blog_H = null;
    String ingH_mango = null;
    String cuk_H ;
    String cukT_mango = null;
    String rel_recpH = null;
    String srv_pm = null;
    String p = null;
    String R = null;
    String rel_recipe = null;
    String all_ingred = null;
    String rel_blog = null;
    String n;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
    }

    public boolean isElementPresent(By locatorKey) {
        try {
           if (driver.findElements(locatorKey).size()>0){
             n = driver.findElement(locatorKey).getAttribute("innerText");
               R="YES";
           }else{
               n = "Found No Element";
               R="NO";
           }
           return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void compare(String JennaElement, String MangoElement, String HEAD, String HeadVar){
        if (HeadVar.equalsIgnoreCase(HEAD)) {
            if (MangoElement != null) {
                if (JennaElement.equalsIgnoreCase(MangoElement)) {
                    if(JennaElement.equals("Found No Element") && MangoElement.equals("Found No Element")){
                        System.out.println("NO-1");
                    }else{
                    System.out.println("YES!");
                    }
                } else {
                    System.out.println("No1");
                }
            } else {
                System.out.println("No2");
            }
        } else {
            System.out.println("No3");
        }
    }

    public void compare(String JennaElement, String MangoElement){
        if (JennaElement.equalsIgnoreCase(MangoElement)) {
            if(JennaElement.equals("Found No Element") && MangoElement.equals("Found No Element")){
                System.out.println("NO-1");
            }else{
            System.out.println("YES");
            }
        } else {
            System.out.println("No");
        }
    }

    @Test
    public void file() {

        driver.manage().window().maximize();
        String filepath = "/home/developer/Downloads/Mango_Linkk_Data.csv";


        try {
            FileReader fr;
            BufferedReader br;
            File file = new File(filepath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            ArrayList<String> mango_Link = new ArrayList<String>();

//          Saving  the links in an array then appending the saved URLs in an arraylist
            while ((line = br.readLine()) != null) {
                int i = 1;
                String[] myList = line.split(",");
                mango_Link.add(myList[i]);
            }

//            Then accessing the Urls link by link
            for (int a =0; a < mango_Link.size(); a++) {

                driver.get(mango_Link.get(a));
                String s1 = mango_Link.get(a);
                boolean present = true;
                String pageName = driver.getTitle();
                System.out.print("link# "+a+" ");
                System.out.println(pageName);

                if (!pageName.equals("Page not found - Mango.org")) {  //Checking if page is not found(404)

                    File file1 = new File("/home/developer/Documents/ResultsMango.csv");
                    PrintWriter out = new PrintWriter(new FileWriter(file1, true));

                    out.append(pageName).append("\n");
                    out.append(s1).append("\n");

                    //GETTING VALUES FROM JENNA AND EXCEPTION HANDLING
                    if (isElementPresent(By.className("wprm-recipe-cook_time-minutes"))){
                        cukT_jenna = n; //cook time jenna
//                        System.out.println("cook time new: "+R);
                        out.append( "cook time new: " +R);
                    }
                    if (isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[1]/div[2]/ul/li[4]/p/span"))){
                        srv_jenna = n; //servings jenna
//                        System.out.println("serve time new : "+R);
                        out.append("serve time new: ").append(R).append("\n");
                    }
                    if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[1]/div[1]/h2"))){
                        BlogH_jenna = n; //PAGE Heading
                        out.append( "PAGE Heading new: " +R +"\n");
                    }
                  if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[2]/div[1]/h4"))){
                      ingH_jenna = n; //ingredients heading
                      out.append( "Ingredients heading new: " +R +"\n");
                  }
                  if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[2]/div[1]/ul"))){
                      ing_jenna = n; //Ingredients jenna
//                      System.out.println("Ingredients new : "+R);
                      out.append( "Ingredients new : " +R +"\n");
                  }
                  if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[3]/div[1]/h2"))){
                      rel_recH_jenna = n; //related recipe heading
                      out.append( "related recipes heading new : " +R +"\n");
                  }
                  if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[3]/div[2]/h2"))){
                      rel_blgs_jenna = n; //related blog heading
                      out.append( "related blog heading new : " +R +"\n");
                  }
                  if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[3]/div[1]/ul/div[1]/div"))){
                      rel_recipe_jenna = n; //related recipes
//                      System.out.println("related recipe new : "+R);
                      out.append("related recipe new : "+R +"\n");
                  }
                  if(isElementPresent(By.className("wprm-recipe-prep_time-minutes"))){
                      prpT_jenna = n; //prep time
//                      System.out.println("prep time new : "+R);
                      out.append("prep time new : "+R +"\n");
                  }
                  if(isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[3]/div[2]/ul/div[1]/div"))){
                  related_blogs_new=n; //RelatedBlogsJenna
//                  System.out.println("Related blogs new : "+R);
//                  data.add(new String[] { "related blogs new: ",R});
//                  System.out.println("NEW: "+related_blogs_new);
//                  System.out.println("------------------------------")
                      out.append("Related blogs new : "+R +"\n");
                  }
                  if (isElementPresent(By.xpath("//*[@id=\"main\"]/section/div[2]/div[2]/div[3]/ol"))) {
                        p_jenna = n;   //InstructionsJenna
//                      System.out.println("Instructions new : "+R);
//                      data.add(new String[] { "Instructions new: ",R});
                      out.append("Instructions new : "+R +"\n");
                      System.out.println("------------------------------");
                  }

                    //OPENING THE MANGO.org
                    String Mango = s1.replace("jennaworkingde.wpengine.com", "mango.org");//replaces all occurrences
                    driver.get(Mango);

//                GETTING VALUES FROM MANGO //
                        if (isElementPresent(By.className("wprm-recipe-instructions-container"))) {
                            p = n; //InstructionsMango
        //                            System.out.println("Instructions old : "+R);
                            out.append("---------------------------").append("\n");
                            out.append("Instructions old : "+R +"\n");
                        }
                        if (isElementPresent(By.xpath("//*[@id=\"main\"]/section[2]/div/div/div[1]/div/div[2]/h2"))) {
                            rel_recpH = n;  //related recipe heading
                            out.append("related recipe heading old : "+R +"\n");
                        }
                        if (isElementPresent(By.xpath("//*[@id=\"wprm-recipe-container-23185\"]/div/div[4]/h3"))) {
                            ingH_mango = n; //ingredients heading
                            out.append("ingredients heading old : "+R +"\n");
                        }
                        if (isElementPresent(By.xpath("//*[@id=\"main\"]/section[1]/div/div/div/div/h2"))) {
                            Blog_H = n; //page heading
                                        }
                        if (isElementPresent(By.className("wprm-recipe-cook-time-name"))) {
                            cuk_H = n; //cook heading
                        }
                       if (isElementPresent(By.className("wprm-recipe-servings-name"))) {
                           serve_m = n; //serve heading
                              }
                       if (isElementPresent(By.className("wprm-recipe-servings"))) {
                           srv_pm = n; //serve time mango
    //                           System.out.println("serve time old : "+R);
                           out.append("serve time old : "+R +"\n");
                       }
                       if (isElementPresent(By.className("wprm-recipe-prep-time-name"))) {
                           prp = n; //prep heading
                                }
                       if (isElementPresent(By.className("wprm-recipe-cook_time-minutes"))) {
                           cukT_mango = n; //cook time mango
    //                           System.out.println("cook time old : "+R);
                           out.append("cook time old : "+R +"\n");
                       }
                       if (isElementPresent(By.className("wprm-recipe-prep_time-minutes"))) {
                           prpT_mango = n; //prep time mango
//                           System.out.println("prep time old : "+R);
                           out.append("prep time old : "+R +"\n");
                       }
                       if (isElementPresent(By.xpath("//*[@id=\"wprm-recipe-container-23185\"]/div/div[4]/div[1]"))) {
                           all_ingred = n;  //Ingredients
        //                       System.out.println("Ingredients old : "+R);
                           out.append("Ingredients old : "+R +"\n");
                       }
                       if (isElementPresent(By.className("recipe-related-items"))) {
                           rel_recipe = n; //related recipe mango
        //                       System.out.println("related recipe old : "+R);
                           out.append("related recipe old : "+R +"\n");
                       }
                       if (isElementPresent(By.xpath("//*[@id=\"wprm-recipe-container-23185\"]/div/div[4]/div[2]/div[2]/p"))) {
                       rel_blog = n; //related blog heading
                                 }
/*
//            TEST initiates here--------->

                    //// checking condition for cook time
                    compare(cukT_jenna,cukT_mango,"COOK TIME:",cuk_H);
                    System.out.println("for cook time");
//                    System.out.println(cuk_H);
//                    System.out.println("new: "+cukT_jenna);
//                    System.out.println("old: "+cukT_mango);

                    System.out.println("------------------");

                    //            Checking for Servings
                    compare(srv_jenna,srv_pm,"SERVINGS:",serve_m);
                    System.out.println("for servings");
//                    System.out.println(serve_m);
//                    System.out.println("new: "+srv_jenna);
//                    System.out.println("old: "+srv_pm);

                    System.out.println("------------------");

                    //            Checking for PrepTime
                    compare(prpT_jenna,prpT_mango,"PREP TIME:",prp);
                    System.out.println("for prep time");
//                    System.out.println(prp);
//                    System.out.println("new: "+prpT_jenna);
//                    System.out.println("old: "+prpT_mango);

                    System.out.println("------------------");

                    //Checking for BlogNAme
                    compare(BlogH_jenna,Blog_H);
//                    System.out.println("new: "+BlogH_jenna);
//                    System.out.println("old: "+Blog_H);

                    System.out.println("------------------");

                    //Checking for ingredients headings
                    compare(ingH_jenna,ingH_mango);
                    System.out.println("for ingredients heading");
//                    System.out.println("new: "+ingH_jenna);
//                    System.out.println("old: "+ingH_mango);

                    System.out.println("------------------");

                    //Checking for related recipe heading
                    compare(rel_recH_jenna,rel_recpH);
                    System.out.println("for related recipe heading");
//                    System.out.println("new: "+rel_recH_jenna);
//                    System.out.println("old: "+rel_recpH);

                    System.out.println("------------------");


                    //Checking for Ingredients on the whole
                    compare(ing_jenna,all_ingred,"INGREDIENTS",ingH_mango);
                    System.out.println("for All Ingredients");
//                    System.out.println("new: "+ing_jenna);
//                    System.out.println("old: "+ingH_mango);
//                    System.out.println("OLD: "+all_ingred);

                    System.out.println("------------------");

                    //Checking for Blog headings
                    compare(rel_blgs_jenna,rel_blog);
                    System.out.println("for Blog Headings");
//                    System.out.println("new: "+rel_blgs_jenna);
//                    System.out.println("old: "+rel_blog);

                    System.out.println("------------------");

                    //Checking for Instructions
                    compare(p_jenna,p);
                    System.out.println("for Instructions");
//                    System.out.println("new: "+p_jenna);
//                    System.out.println("old: "+p);

                    System.out.println("------------------");
*/
                    out.append("\n");
                    out.close();

//               TODO; open after code is complete= driver.navigate().to(Mango);
                } else {
                    Assert.assertTrue(present, "Error during loading of Landing Page");
                    System.out.println("The page is not found for " + mango_Link.get(a));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// file() method ending}

//    @AfterTest
//    public void tearDown(){
//        driver.quit();
//    }
}