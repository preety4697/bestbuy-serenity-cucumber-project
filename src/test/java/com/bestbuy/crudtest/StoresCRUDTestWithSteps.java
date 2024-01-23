package com.bestbuy.crudtest;

import com.bestbuy.buyersinfo.StoresSteps;
import com.bestbuy.testbase.StoreTestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class StoresCRUDTestWithSteps extends StoreTestBase {
    static String name = "Shakti";
    static String type = "OffLicence";
    static String address = "104, Close Avenue";
    static String address2 = "High Road";
    static String city = "Harrow";
    static String state = "Misslesex";
    static String zip = "HA6 7RT";
    static double lat = 55.789554;
    static double lng = -85.859674;
    static String hours = "Mon: 6-10; Tue: 6-10; Wed: 6-10; Thurs: 6-10; Fri: 6-10; Sat: 8-7; Sun: 10-5";


    static int storeId;

    @Steps
    StoresSteps steps;

    @Title("This will create new stores")
    @Test
    public void test001(){

        HashMap<Object, Object> services = new HashMap<>();

        ValidatableResponse response =  steps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services).statusCode(201);
        storeId = response.extract().jsonPath().getInt("id");
        System.out.println(storeId);
    }

    @Title("Verify the store added to the application")
    @Test
    public void test002(){

        String storeMap = steps.getStoreInfoByName(storeId);


        Assert.assertEquals(name, storeMap);

    }

    @Title("Update and verify the store information ")
    @Test
    public void test003(){
        name = name + "_updated";
        HashMap<Object, Object> services = new HashMap<>();
        steps.updateStore(storeId, name, type, address, address2, city, state, zip, lat, lng, hours, services).statusCode(200);

        String storeMap = steps.getStoreInfoByName(storeId);
        Assert.assertEquals(name, storeMap);
    }

    @Title("delete the store and verify if the store is deleted")
    @Test
    public void test004(){
        steps.deleteStore(storeId).statusCode(200);
        steps.getStoreById(storeId).statusCode(404);
    }
}
