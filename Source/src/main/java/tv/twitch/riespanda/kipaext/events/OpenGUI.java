package tv.twitch.riespanda.kipaext.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OpenGUI implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){

        // Calling variables
        Player player = event.getPlayer();
        Block ClickedBlock = event.getClickedBlock();
        itemStacks();

        // Creating inventory + items
        Inventory inv = Bukkit.createInventory(player, 54, ChatColor.BOLD + "Stat Upgrades");


        if (ClickedBlock != null && ClickedBlock.getType() == Material.FLETCHING_TABLE) {
            if(player.hasPermission("KipaEXT.gui"))
            if(!(player.getInventory().getItemInMainHand().getItemMeta() == null) && player.getInventory().getItemInMainHand().getItemMeta().getLore()!= null && !(player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"))) {
                player.openInventory(inv);
            }

        }
    }

    void itemStacks(){
        // Stats
        ItemStack STR = new ItemStack(Material.RED_DYE);
        ItemStack INT = new ItemStack(Material.PURPLE_DYE);
        ItemStack DEX = new ItemStack(Material.YELLOW_DYE);
        ItemStack LUK = new ItemStack(Material.BLUE_DYE);

        ItemStack MATK = new ItemStack(Material.BLAZE_ROD);
        ItemStack ATK = new ItemStack(Material.WOODEN_SWORD);
        ItemStack DEF = new ItemStack(Material.SHIELD);
        ItemStack CRITDAMAGE = new ItemStack(Material.MAGMA_CREAM);
        ItemStack CRITCHANCE = new ItemStack(Material.BLAZE_POWDER);

        // Getting item meta for all items in the GUI
        ItemMeta STR_META = STR.getItemMeta();
        ItemMeta INT_META = INT.getItemMeta();
        ItemMeta DEX_META = DEX.getItemMeta();
        ItemMeta LUK_META = LUK.getItemMeta();

        ItemMeta MATK_META = MATK.getItemMeta();
        ItemMeta ATK_META = ATK.getItemMeta();
        ItemMeta DEF_META = DEF.getItemMeta();
        ItemMeta CRITD_META = CRITDAMAGE.getItemMeta();
        ItemMeta CRITC_META = CRITCHANCE.getItemMeta();

        // Changing name + lore
        if(STR_META != null && INT_META!= null && DEX_META != null && LUK_META != null && MATK_META != null && ATK_META != null && DEF_META != null && CRITD_META != null && CRITC_META != null){
            STR_META.setDisplayName(ChatColor.RED + "STR");
            
        }

    }
}
