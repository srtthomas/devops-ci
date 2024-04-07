package ch.zhaw.iwi.devops.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeinTest {
    @Test
    void testGetAll() {
        var testwein = new Wein(10,"Nebbiolo","Dunkler roter Wein");
        assertEquals(10,testwein.getId());
        assertEquals("Nebbiolo", testwein.getTitle());
        assertEquals("Dunkler roter Wein", testwein.getDescription());

    }

    @Test
    void testGetTitle() {
        var testwein = new Wein(10,"Nebbiolo","Dunkler roter Wein");
        assertEquals("Nebbiolo", testwein.getTitle());

    }
    @Test
    void testGetDescription() {
        var testwein = new Wein(10,"Nebbiolo","Dunkler roter Wein");
        assertEquals("Dunkler roter Wein", testwein.getDescription());

    }
    @Test
    void testSetId() {
        var testwein = new Wein(10,"Nebbiolo","Dunkler roter Wein");
        testwein.setId(20);
        assertEquals(20, testwein.getId());
    }
}
