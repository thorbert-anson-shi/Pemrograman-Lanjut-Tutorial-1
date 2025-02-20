package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HomePageControllerTest {
    private HomePageController homePageController;

    @BeforeEach
    public void setUp() {
        homePageController = new HomePageController();
    }

    @Test
    public void testHomePage() {
        assertEquals("home", homePageController.homePage());
    }
}
