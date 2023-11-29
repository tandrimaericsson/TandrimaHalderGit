package org.example.stepDefinitions;


import io.cucumber.java.Before;

public class hooks {

    @Before(order = 2)
    public void before2() {
        //System.out.println("Before method for all 2");
    }

    @Before("@TC_007")
    public void before07() {
        //System.out.println("Before method for 007");
    }

    @Before(value = "@TC_007", order = 2)
    public void before07_02() {
        //System.out.println("Before method for 007");
    }

    @Before()
    public void before3() {
        //System.out.println("Before method for all 3");
    }
}
