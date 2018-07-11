package com.surajnshah.sandbox.helloworld;

/**
 * @author surajshah on 11/07/2018
 * @project surajnshah.com
 */
public class HelloWorldAgain {

    private String name = "";

    public String getName() {
        return name;
    }

    public String getMessage() {
        if (name == "") {
            return "Hello!";
        } else {
            return "Hello " + name + "!";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

}
