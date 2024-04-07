package ch.zhaw.iwi.devops.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class WeinControllerTest {

    private WeinController weinController;

    @BeforeEach
    void setUp() {
        weinController = new WeinController();
        weinController.init(); // Populate the wines map
    }

    @Test
    void testGetWeinList() {
        List<PathListEntry<Integer>> weinList = weinController.wein();
        assertNotNull(weinList, "The returned list should not be null");
        assertEquals(5, weinList.size(), "The returned list should contain 5 wines");
    }

    @Test
    void testGetWeinByKey() {
        Wein wein = weinController.getWein(1);
        assertNotNull(wein, "Wein should not be null");
        assertEquals("Barolo", wein.getTitle(), "The wine title should match");
    }

    @Test
    void testCreateWein() {
        Wein newWein = new Wein(6, "Merlot", "Ein runder roter Wein");
        weinController.createWein(newWein);
        Wein retrievedWein = weinController.getWein(6);
        assertNotNull(retrievedWein, "The wine should be added to the map");
        assertEquals("Merlot", retrievedWein.getTitle(), "The wine title should match");
    }

    @Test
    void testUpdateWein() {
        Wein updatedWein = new Wein(1, "Updated Barolo", "Updated description");
        weinController.createWein(1, updatedWein);
        Wein retrievedWein = weinController.getWein(1);
        assertEquals("Updated Barolo", retrievedWein.getTitle(), "The wine title should be updated");
    }

    @Test
    void testDeleteWein() {
        Wein deletedWein = weinController.deleteWein(1);
        assertNotNull(deletedWein, "The wine should be returned upon deletion");
        Wein shouldBeNull = weinController.getWein(1);
        assertNull(shouldBeNull, "The wine should be removed from the map");
    }

}