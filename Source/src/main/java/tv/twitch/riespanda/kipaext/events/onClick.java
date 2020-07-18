package tv.twitch.riespanda.kipaext.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class onClick implements Listener {

    public static String statreturn(String stat, int amount) {
        String stat1 = stat + " " + ChatColor.WHITE + amount + ChatColor.GREEN + "↑";
        return stat1;
    }

    public static int searchLine(List<String> lore, String stat) {
        int a = 0;
        for (int n = 0; n < lore.size(); n++) {
            if (lore.get(n) != null) {
                String num = lore.get(n);
                if (num.contains(stat)) {
                    a = n;
                }
            }
        }
        return a;
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getWhoClicked().getOpenInventory().getTitle().equals(ChatColor.BOLD + "Stat Upgrades")) {
            Bukkit.broadcastMessage("Hi");
            event.setCancelled(true);
            ItemStack item = event.getWhoClicked().getOpenInventory().getItem(event.getSlot());

            if (item == null || item.getItemMeta() == null || !(item.getItemMeta().hasDisplayName())) return;
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            ItemStack item1 = new ItemStack(Material.LEATHER);

            if (!(event.getWhoClicked().getOpenInventory().getItem(event.getSlot() + 1) == null)){
               item1 = event.getWhoClicked().getOpenInventory().getItem(event.getSlot() + 1);
            }
                ItemMeta meta1 = item1.getItemMeta();
                List<String> lore1 = meta1.getLore();
                ItemStack item2 = event.getWhoClicked().getOpenInventory().getItem(event.getSlot() - 1);
                ItemStack save = event.getWhoClicked().getOpenInventory().getItem(8);
                ItemMeta save1 = save.getItemMeta();
                List<String> save_lore = save1.getLore();
                String floatStat = "";

                int c = 0;
                if (!save1.hasLore()) {
                    save_lore = new ArrayList<String>();
                    save_lore.add(ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    save_lore.add(ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                }

                if (meta.getDisplayName().equals("-")) {
                    floatStat = lore.get(0);
                    int valueOf = Integer.parseInt(lore1.get(0));
                    // If the clicked item isn't paper
                    if (event.getClick().isLeftClick()) {
                        lore1.set(0, valueOf - 1 + "");
                        meta1.setLore(lore1);
                        item1.setItemMeta(meta1);
                        c = -1;
                    } else {
                        lore1.set(0, valueOf - 10 + "");
                        meta1.setLore(lore1);
                        item1.setItemMeta(meta1);
                        c = -10;
                    }
                    int line = searchLine(save_lore, floatStat);
                    if (searchLine(save_lore, floatStat) == 0) {
                        save_lore.add(1, statreturn(floatStat, c));
                    } else {
                        String numberOnly = save_lore.get(line).replaceAll("[^0-9]", "");
                        int numon = Integer.parseInt(numberOnly);
                        save_lore.set(line, statreturn(floatStat, numon + c));
                    }
                }
                // If the clicked item is paper
                if (meta.getDisplayName().equals("+")) {
                    floatStat = item2.getItemMeta().getLore().get(0);
                    if (event.getClick().isLeftClick() && meta.getLore() != null) {
                        lore.set(0, Integer.valueOf(lore.get(0)) + 1 + "");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        c = 1;
                    } else {
                        lore.set(0, Integer.valueOf(lore.get(0)) + 10 + "");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        c = 10;
                    }
                    int line = searchLine(save_lore, floatStat);
                    if (searchLine(save_lore, floatStat) == 0) {
                        save_lore.add(1, statreturn(floatStat, c));
                    } else {
                        String numberOnly = save_lore.get(line).replaceAll("[^0-9]", "");
                        int numon = Integer.parseInt(numberOnly);
                        save_lore.set(line, statreturn(floatStat, numon + c));
                    }
                }
                save1.setLore(save_lore);
                save.setItemMeta(save1);
                int middleLine = searchLine(save_lore, ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                int lastLine = middleLine + 1;

                if (meta.getDisplayName().equals("Warrior") || meta.getDisplayName().equals("Archer") || meta.getDisplayName().equals("Magician")) {
                    String job = "";
                    if (meta.getDisplayName().equals("Warrior")) {
                        job = ChatColor.RED + "Warrior";
                    } else if (meta.getDisplayName().equals("Archer")) {
                        job = ChatColor.YELLOW + "Archer";
                    } else if (meta.getDisplayName().equals("Magician")) {
                        job = ChatColor.LIGHT_PURPLE + "Magician";
                    }
                    if (searchLine(save_lore, ChatColor.GRAY + "Can be used by: ") == 0) {
                        save_lore.add(middleLine + 1, ChatColor.GRAY + "Can be used by: " + job);
                    } else {
                        save_lore.set(searchLine(save_lore, ChatColor.GRAY + "Can be used by: "), ChatColor.GRAY + "Can be used by: " + job);
                    }
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                }
                if (meta.getDisplayName().equals("Sword") || meta.getDisplayName().equals("Crossbow") || meta.getDisplayName().equals("Wand") || meta.getDisplayName().equals("Armor")) {
                    String weapon = "";
                    if (meta.getDisplayName().equals("Sword")) {
                        weapon = ChatColor.RED + "Sword";
                    } else if (meta.getDisplayName().equals("Crossbow")) {
                        weapon = ChatColor.YELLOW + "Crossbow";
                    } else if (meta.getDisplayName().equals("Wand")) {
                        weapon = ChatColor.LIGHT_PURPLE + "Wand";
                    } else if (meta.getDisplayName().equals("Armor")) {
                        weapon = ChatColor.GREEN + "Armor";
                    }
                    if (searchLine(save_lore, ChatColor.GRAY + "Type: ") == 0) {
                        save_lore.add(lastLine, ChatColor.GRAY + "Type: " + weapon);
                    } else {
                        save_lore.set(searchLine(save_lore, ChatColor.GRAY + "Type: "), ChatColor.GRAY + "Type: " + weapon);
                    }
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                }
                if (event.getCurrentItem().equals(player.getOpenInventory().getItem(8))) {
                    player.getInventory().getItemInMainHand().setItemMeta(save1);
                    player.closeInventory();
                }

        }
    }
}
