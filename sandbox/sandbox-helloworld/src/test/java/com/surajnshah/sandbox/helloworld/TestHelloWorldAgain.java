package com.surajnshah.sandbox.helloworld;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author surajshah on 11/07/2018
 * @project surajnshah.com
 */
public class TestHelloWorldAgain {

    private HelloWorldAgain h;

    @Before
    public void setUp() throws Exception
    {
        h = new HelloWorldAgain();
    }

    @Test
    public void testHelloEmpty()
    {
        assertEquals(h.getName(),"");
        assertEquals(h.getMessage(),"Hello!");
    }

    @Test
    public void testHelloWorldAgain()
    {
        h.setName("World");
        assertEquals(h.getName(),"World");
        assertEquals(h.getMessage(),"Hello World!");
    }

}
