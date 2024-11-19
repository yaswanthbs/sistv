package org.sistv;

import org.sistv.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test Input
        List<Item> items = new ArrayList<>();
        items.add(new Item("Aged Brie", 1, 1));
        items.add(new Item("Backstage passes", -1, 2));
        items.add(new Item("Backstage passes", 9, 2));
        items.add(new Item("Sulfuras", 2, 2));
        items.add(new Item("Normal Item", -1, 55));
        items.add(new Item("Normal Item", 2, 2));
        items.add(new Item("INVALID ITEM", 2, 2));
        items.add(new Item("Conjured", 2, 2));
        items.add(new Item("Conjured", -1, 5));

        GildedRose gildedRose = new GildedRose(items);

        // Update inventory
        gildedRose.updateInventory();

        // Print updated inventory
        List<Item> updatedInventory = gildedRose.getUpdatedInventory();
        for (Item item : updatedInventory) {
            System.out.println(item);
        }
    }
}