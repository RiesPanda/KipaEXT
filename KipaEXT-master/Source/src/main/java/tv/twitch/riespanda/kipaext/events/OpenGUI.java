package tv.twitch.riespanda.kipaext.events;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OpenGUI implements Listener {

    public static ItemStack item(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        List<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore1);
        }
        item.setItemMeta(meta);

        return item;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        // Calling variables
        Player player = event.getPlayer();
        Block ClickedBlock = event.getClickedBlock();
        int two = 2;
        int one = 1;
        int four2 = 4;
        int four = 5;
        int six = 7;

        item(Material.BLAZE_POWDER, "-", "Crit Damage");
        item(Material.STONE_BUTTON, "+", "Slots");

    // Creating inventory + items
    Inventory inv = Bukkit.createInventory(player, 54, ChatColor.BOLD + "Stat Upgrades");
        for(int i = 0; i <= 5; i++) {
        inv.setItem(two, item(Material.LADDER, "", ""));
        inv.setItem(four, item(Material.LADDER, "", ""));
        inv.setItem(six, item(Material.LADDER, "", ""));
        inv.setItem(one,item(Material.PAPER, ChatColor.GRAY + "+", "0"));
        inv.setItem(four2,item(Material.PAPER, ChatColor.GRAY + "+", "0"));
        two = two + 9;
        four = four + 9;
        six = six + 9;
        one = one + 9;
        four2 = four2 + 9;
    }


    // Setting items
        inv.setItem(0,item(Material.RED_DYE, ChatColor.GRAY +"-", "STR"));
        inv.setItem(3,item(Material.PURPLE_DYE, ChatColor.GRAY + "-", "Magic ATK"));
        inv.setItem(6,item(Material.WOODEN_SWORD, "Warrior", "Click this to select Warrior"));
        inv.setItem(8,item(Material.GREEN_CONCRETE, "Save", ""));
        inv.setItem(9,item(Material.YELLOW_DYE, ChatColor.GRAY + "-", "DEX"));
        inv.setItem(12,item(Material.WOODEN_SWORD, "+", "ATK"));
        inv.setItem(15,item(Material.BLAZE_ROD, "Magician", "Click this to select Magician"));
        inv.setItem(17,item(Material.WOODEN_SWORD, "Sword", ""));
        inv.setItem(18,item(Material.BLUE_DYE, ChatColor.GRAY + "-", "LUK"));
        inv.setItem(21,item(Material.SHIELD, "-", "DEF"));
        inv.setItem(24,item(Material.CROSSBOW, "Archer", "Click this to select Archer"));
        inv.setItem(26,item(Material.BLAZE_ROD, "Wand", ""));
        inv.setItem(27,item(Material.PURPLE_DYE, ChatColor.GRAY + "-", "INT"));
        inv.setItem(30,item(Material.MAGMA_CREAM, "-", "Crit Chance"));
        inv.setItem(35,item(Material.CROSSBOW, "Bow", ""));

        if(event.getClickedBlock() != null && event.getClickedBlock().getType() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType().equals(Material.FLETCHING_TABLE)) {
                if (player.isOp()) {
                    player.openInventory(inv);

                }
            }
        }
    }
    @EventHandler
    public void onClickEvent(InventoryClickEvent event){
        Bukkit.broadcastMessage("Hi");
        event.setCancelled(true);
        ItemStack item = event.getInventory().getItem(event.getSlot());
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(ChatColor.BOLD + "Stat Upgrades")) {

            if (item == null && item.getItemMeta() == null && !(item.getItemMeta().hasDisplayName())) return;
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();

            if (meta.getDisplayName().equals("+")) {
                ItemStack item1 = event.getInventory().getItem(event.getSlot() - 1);
                ItemMeta meta1 = item1.getItemMeta();
                List<String> lore1 = meta.getLore();


                if (item1 == null && item1.getItemMeta() == null && !item1.getItemMeta().hasDisplayName() && !item1.getItemMeta().hasLore())
                    return;
                if(event.getClick().isLeftClick()
                ){
                    lore1.set(0,Integer.valueOf(item1.getItemMeta().getLore().get(0)) + 1 + "");
                    meta1.setLore(lore1);
                    item1.setItemMeta(meta1);
                } else {
                    lore1.set(0,Integer.valueOf(item1.getItemMeta().getLore().get(0)) + 10 + "");
                    meta1.setLore(lore);
                    item1.setItemMeta(meta1);
                }

            } else if(meta.getDisplayName().equals("-")){
                if(event.getClick().isLeftClick() && meta.getLore() != null) {
                    lore.set(0, Integer.parseInt(item.getItemMeta().getLore().get(0)) - 1 + "");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                } else {
                    lore.set(0, Integer.parseInt(item.getItemMeta().getLore().get(0)) - 10 + "");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                }
            }
        }
    }
}
