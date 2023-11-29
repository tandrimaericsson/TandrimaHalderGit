package org.example.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private ManageWebDriver manageWebDriver;
    private RequestSpecification requestSpecification;
    private Response response;
    private Map<String,Object> hashMap = new HashMap<>();

    public TestContext() {
        manageWebDriver = new ManageWebDriver();
        requestSpecification = new RequestSpecBuilder().build();
    }

    public ManageWebDriver getManageWebDriver() {
        return manageWebDriver;
    }

    public void setManageWebDriver(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Map<String, Object> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, Object> hashMap) {
        this.hashMap = hashMap;
    }
}
