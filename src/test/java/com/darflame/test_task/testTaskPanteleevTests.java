package com.darflame.test_task;

import com.darflame.test_task.controllers.CurrencyController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class testTaskPanteleevTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CurrencyController controller;

    @org.junit.Test
    public void test() throws Exception {
        Assert.assertTrue(controller.equals(null));
    }

}
