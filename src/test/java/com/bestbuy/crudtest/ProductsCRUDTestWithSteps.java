package com.bestbuy.crudtest;

import com.bestbuy.buyersinfo.ProductsSteps;
import com.bestbuy.testbase.ProductTestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTestWithSteps extends ProductTestBase {
    static  String name = "Duracell - AAA Batteries (25-Pack)";
    static  String type = "HardGood";
    static  double price = 5.99;
    static  int shipping = 0;
    static  String upc = "041333424999";
    static  String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static  String manufacturer = "Duracell";
    static  String model = "MN1500B4Z";
    static  String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static  String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;

    @Steps
    ProductsSteps steps;

    @Title("This will create new stores")
    @Test
    public void test001(){

        ValidatableResponse response = steps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(201);
        productId = response.extract().jsonPath().getInt("id");
        System.out.println(productId);
    }

    @Title("Verify the product added to the application")
    @Test
    public void test002(){
        String productMap = steps.getStoreInfoByName(productId);

        Assert.assertEquals(name, productMap);

    }

    @Title("Update and verify the product information ")
    @Test
    public void test003(){

        name = name + "_updated";

        steps.updateProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(200);

        String productMap = steps.getStoreInfoByName(productId);

        Assert.assertEquals(name, productMap);
    }

    @Title("delete the product and verify if the product is deleted")
    @Test
    public void test004(){
        steps.deleteProduct(productId).statusCode(200);
        steps.getProductById(productId).statusCode(404);
    }
}
