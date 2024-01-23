package com.bestbuy.buyersinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoresSteps {
    @Step("Creating stores with Name : {0}, Type : {1}, Address : {2}, Address2 : {3}, City : {4}, State : {5}, Zip : {6}, Lat : {7}, Lag : {8}, Hours : {9}, Services : {10}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lag, String hours, HashMap<Object, Object> services) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lag);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post()
                .then().log().all();

    }

    @Step("Getting the store information with Name : {0}")
    public String getStoreInfoByName(int storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_Id)
                .then().log().all().statusCode(200)
                .extract().path("name");
    }

    @Step("Creating stores with StoreId : {0}, Name : {1}, Type : {2}, Address : {3}, Address2 : {4}, City : {5}, State : {6}, Zip : {7}, Lat : {8}, Lag : {9}, Hours : {10}, Services : {11}")
    public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2, String city, String state, String zip, double lat, double lag, String hours, HashMap<Object, Object> services) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lag);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .pathParam("storeId", storeId)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_Id)
                .then().log().all();
    }

    @Step("delete store information with storeId : {0}")
    public ValidatableResponse deleteStore(int storeId){

        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Getting store information with storeId : {0}")
    public ValidatableResponse getStoreById(int storeId){

        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_Id)
                .then();

    }
}
