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
        inv.setItem(one,item(Material.PAPER, "+", "0"));
        inv.setItem(four2,item(Material.PAPER, "+", "0"));
        two = two + 9;
        four = four + 9;
        six = six + 9;
        one = one + 9;
        four2 = four2 + 9;
    }


    // Setting items
        inv.setItem(0,item(Material.RED_DYE, "-", ChatColor.RED + "STR"));
        inv.setItem(3,item(Material.BLAZE_ROD, "-", ChatColor.LIGHT_PURPLE + "Magic ATT"));
        inv.setItem(6,item(Material.WOODEN_SWORD, "Warrior", "Click this to select Warrior"));
        inv.setItem(8, player.getInventory().getItemInMainHand());
        inv.setItem(9,item(Material.YELLOW_DYE, "-", ChatColor.YELLOW + "DEX"));
        inv.setItem(12,item(Material.WOODEN_SWORD, "-", ChatColor.RED + "Weapon ATT"));
        inv.setItem(15,item(Material.BLAZE_ROD, "Magician", "Click this to select Magician"));
        inv.setItem(17,item(Material.WOODEN_SWORD, "Sword", ""));
        inv.setItem(18,item(Material.BLUE_DYE, "-", ChatColor.AQUA + "LUK"));
        inv.setItem(21,item(Material.SHIELD, "-", ChatColor.GREEN + "DEF"));
        inv.setItem(24,item(Material.CROSSBOW, "Archer", "Click this to select Archer"));
        inv.setItem(26,item(Material.BLAZE_ROD, "Wand", ""));
        inv.setItem(27,item(Material.PURPLE_DYE, "-", ChatColor.LIGHT_PURPLE + "INT"));
        inv.setItem(30,item(Material.MAGMA_CREAM, "-", ChatColor.RED + "Crit Damage"));
        inv.setItem(35,item(Material.CROSSBOW, "Crossbow", ""));
        inv.setItem(44,item(Material.IRON_CHESTPLATE, "Armor", ""));
        inv.setItem(39,item(Material.BLAZE_POWDER, "-", ChatColor.YELLOW + "Crit Chance"));
        inv.setItem(36,item(Material.STONE_BUTTON, "-", ChatColor.GRAY + "Slots"));

        if(event.getClickedBlock() != null && event.getClickedBlock().getType() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
            if (event.getClickedBlock().getType().equals(Material.FLETCHING_TABLE)) {
                if (player.isOp()) {
                    player.openInventory(inv);
                }
            }
        }
    }
}
