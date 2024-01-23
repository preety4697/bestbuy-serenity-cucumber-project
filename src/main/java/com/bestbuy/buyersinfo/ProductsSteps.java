package com.bestbuy.buyersinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductsSteps {
    @Step("Creating product with Name : {0}, Type : {1}, price : {2}, Shipping : {3}, UPC : {4}, Description : {5}, Manufacturer : {6}, Model : {7}, URL : {8}, Image : {9}")
    public ValidatableResponse createProduct(String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {

        ProductsPojo productPojo = new ProductsPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post()
                .then().log().all();
    }

    @Step("Getting the product information with Name : {0}")
    public String getStoreInfoByName(int productId) {

        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_Id)
                .then().log().all().statusCode(200)
                .extract().path("name");
    }

    @Step("Creating product with ProductId : {0}, Name : {1}, Type : {2}, price : {3}, Shipping : {4}, UPC : {5}, Description : {6}, Manufacturer : {7}, Model : {8}, URL : {9}, Image : {10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {

        ProductsPojo productPojo = new ProductsPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_Id)
                .then().log().all();
    }

    @Step("delete product information with productId : {0}")
    public ValidatableResponse deleteProduct(int productId) {

        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Getting product information with productId : {0}")
    public ValidatableResponse getProductById(int productId) {

        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_Id)
                .then();

    }
}
