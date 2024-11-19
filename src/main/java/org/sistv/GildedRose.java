package org.sistv;

import org.sistv.model.Item;

import java.util.List;

public class GildedRose {
    private final List<Item> inventory;

    public GildedRose(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void updateInventory() {
        for (Item item : inventory) {
            if (item.getName().equals("Sulfuras")) {
                continue; // Legendary item, no changes
            }

            // Process "Backstage passes"
            if (item.getName().equals("Backstage passes")) {
                if (item.getSellIn() <= 0) {
                    item.setQuality(0); // Quality drops to 0 after the concert
                } else if (item.getSellIn() <= 5) {
                    item.setQuality(item.getQuality() + 3);
                } else if (item.getSellIn() <= 10) {
                    item.setQuality(item.getQuality() + 2);
                } else {
                    item.setQuality(item.getQuality() + 1);
                }
            }
            // Process "Aged Brie"
            else if (item.getName().equals("Aged Brie")) {
                item.setQuality(item.getQuality() + 1);
            }
            // Process "Conjured" items
            else if (item.getName().equals("Conjured")) {
                item.setQuality(item.getQuality() - 2);
            }
            // Process normal items
            else if (item.getName().equals("Normal Item")) {
                item.setQuality(item.getQuality() - 1);
            } else {
                // Invalid item type
                item.setName("NO SUCH ITEM");
                continue;
            }

            // Reduce quality for expired items
            if (item.getSellIn() <= 0 && !item.getName().equals("Backstage passes") && !item.getName().equals("NO SUCH ITEM")) {
                if (item.getName().equals("Aged Brie")) {
                    item.setQuality(item.getQuality() + 1); // Aged Brie increases in quality past sell date
                } else {
                    item.setQuality(item.getQuality() - (item.getName().equals("Conjured") ? 2 : 1)); // Degrade faster
                }
            }

            // Clamp quality between 0 and 50
            if (!item.getName().equals("Sulfuras")) {
                item.setQuality(Math.max(0, Math.min(50, item.getQuality())));
            }

            // Decrease sellIn for all items except Sulfuras
            item.setSellIn(item.getSellIn() - 1);
        }
    }

    public List<Item> getUpdatedInventory() {
        return inventory;
    }
}
