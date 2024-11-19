package org.sistv;

import org.junit.jupiter.api.Test;
import org.sistv.model.Item;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class GildedRoseTest {

    @Test
    public void testAgedBrieIncreasesInQuality() {
        List<Item> items = Arrays.asList(new Item("Aged Brie", 1, 1));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateInventory();

        assertEquals("Aged Brie 0 2", items.get(0).toString());
    }

    @Test
    public void testBackstagePassesIncreaseInQuality() {
        List<Item> items = Arrays.asList(
                new Item("Backstage passes", 9, 2),
                new Item("Backstage passes", 4, 2),
                new Item("Backstage passes", -1, 2)
        );
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateInventory();

        assertEquals("Backstage passes 8 4", items.get(0).toString());
        assertEquals("Backstage passes 3 5", items.get(1).toString());
        assertEquals("Backstage passes -2 0", items.get(2).toString());
    }

    @Test
    public void testSulfurasRemainsUnchanged() {
        List<Item> items = Arrays.asList(new Item("Sulfuras", 2, 2));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateInventory();

        assertEquals("Sulfuras 2 2", items.get(0).toString());
    }

    @Test
    public void testNormalItemDegrades() {
        List<Item> items = Arrays.asList(
                new Item("Normal Item", 2, 2),
                new Item("Normal Item", -1, 55)
        );
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateInventory();

        assertEquals("Normal Item 1 1", items.get(0).toString());
        assertEquals("Normal Item -2 50", items.get(1).toString());
    }

    @Test
    public void testConjuredItemsDegradeTwiceAsFast() {
        List<Item> items = Arrays.asList(
                new Item("Conjured", 2, 2),
                new Item("Conjured", -1, 5)
        );
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateInventory();

        assertEquals("Conjured 1 0", items.get(0).toString());
        assertEquals("Conjured -2 1", items.get(1).toString());
    }

    @Test
    public void testInvalidItem() {
        List<Item> items = Arrays.asList(new Item("INVALID ITEM", 2, 2));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateInventory();

        assertEquals("NO SUCH ITEM", items.get(0).getName());
    }
}

