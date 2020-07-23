package tv.twitch.riespanda.kipaext.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class onClick implements Listener {

    // Equation for stats and the way they are displayed on the item itself
    public static String statreturn(String stat, int amount) {
        return stat + " " + ChatColor.WHITE + amount + ChatColor.GREEN + "↑";
    }

    // Equation to look for a specific clear line and get it
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

    public static String updateName(int successRate, String whichItem, String mainStat) {
        return ChatColor.AQUA + "Book of " + whichItem + ChatColor.AQUA + " for " + mainStat + ChatColor.AQUA + " | " + successRate + "%";
    }

    public void addStat(Player player) {
        List<Integer> intList = new ArrayList<>();
        ItemStack oldItem = player.getInventory().getItemInMainHand();
        ItemStack finalItem = player.getOpenInventory().getItem(8);
        ItemMeta meta = finalItem.getItemMeta();
        List<String> lore = meta.getLore();
        int newInt = 1;
        int newInt2 = 4;
        int numon = 0;
        for (int i = 0; i <= 4; i++) {
            ItemStack item = player.getOpenInventory().getItem(newInt);
            ItemStack item2 = player.getOpenInventory().getItem(newInt2);
            int line1 = Integer.parseInt(item.getItemMeta().getLore().get(0));
            int line2 = Integer.parseInt(item2.getItemMeta().getLore().get(0));
            intList.add(line1);
            intList.add(line2);
            newInt += 9;
            newInt2 += 9;
        }

        String stat = "";
        for (int i = 0; i <= 9; i++) {
            if (i == 0) {
                stat = "STR";
            } else if (i == 1) {
                stat = "Magic ATT";
            } else if (i == 2) {
                stat = "DEX";
            } else if (i == 3) {
                stat = "Weapon ATT";
            } else if (i == 4) {
                stat = "LUK";
            } else if (i == 5) {
                stat = "Defence";
            } else if (i == 6) {
                stat = "INT";
            } else if (i == 7) {
                stat = "Crit Damage";
            } else if (i == 8) {
                stat = "Ignore";
            } else if (i == 9) {
                stat = "Crit Chance";
            }
            if (stat != "ignore") {
                if (intList.get(i) != 0) {
                    if (searchLine(lore, stat) == 0) {
                        String numberOnly = lore.get(searchLine(oldItem.getItemMeta().getLore(), stat)).replaceAll("[^0-9]", "");
                        if (!numberOnly.contains("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")) {
                            numon = Integer.parseInt(numberOnly);
                            int finalInt = numon + intList.get(i);
                            lore.add(1, statreturn(stat, finalInt));
                            meta.setLore(lore);
                            finalItem.setItemMeta(meta);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        // Variables
        Player player = (Player) event.getWhoClicked();

        // Inventory title check
        if (event.getWhoClicked().getOpenInventory().getTitle().equals(ChatColor.BOLD + "Stat Upgrades")) {
            event.setCancelled(true);
            ItemStack item = event.getWhoClicked().getOpenInventory().getItem(event.getSlot());

            // Null checks
            if (item == null || item.getItemMeta() == null || !(item.getItemMeta().hasDisplayName())) return;

            // item info
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            ItemStack item1 = new ItemStack(Material.LEATHER);

            // Fixing Armor & Crossbow
            if (!(event.getWhoClicked().getOpenInventory().getItem(event.getSlot() + 1) == null)) {
                item1 = event.getWhoClicked().getOpenInventory().getItem(event.getSlot() + 1);
            }
            // Null checks
            if (item1 != null && item1.getItemMeta() != null) {

                // Item1 info
                ItemMeta meta1 = item1.getItemMeta();
                List<String> lore1 = meta1.getLore();
                // Item2 info
                ItemStack item2 = event.getWhoClicked().getOpenInventory().getItem(event.getSlot() - 1);
                // Save block info
                ItemStack save = event.getWhoClicked().getOpenInventory().getItem(8);
                ItemMeta save1 = save.getItemMeta();
                List<String> save_lore = save1.getLore();
                save1.setDisplayName(ChatColor.AQUA + "Book of " + ChatColor.AQUA + " for " + ChatColor.AQUA + " | ");

                String floatStat = "";
                int c = 0;
                // Lore checks + adding lines
                if (!save1.hasLore()) {
                    save_lore = new ArrayList<>();
                    save_lore.add(ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    save_lore.add(ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                }


                // Clicking on the item will run these blocks of code
                if (meta.getDisplayName().equals("-")) {
                    floatStat = lore.get(0);
                    int valueOf = Integer.parseInt(lore1.get(0));

                    // If the clicked item isn't paper
                    // And if the clicked item is clicked with the left button
                    if (event.getClick().isLeftClick()) {
                        lore1.set(0, valueOf - 1 + "");
                        meta1.setLore(lore1);
                        item1.setItemMeta(meta1);
                        c = -1;
                    }

                    // Otherwise, this will run if the item is clicked with the right button
                    else {
                        lore1.set(0, valueOf - 10 + "");
                        meta1.setLore(lore1);
                        item1.setItemMeta(meta1);
                        c = -10;
                    }

                    //
                    int line = searchLine(save_lore, floatStat);
                    if (searchLine(save_lore, floatStat) == 0) {
                    } else {
                        String numberOnly = save_lore.get(line).replaceAll("[^0-9]", "");
                        int numon = Integer.parseInt(numberOnly);
                    }
                }
                int middleLine = searchLine(save_lore, ChatColor.GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                int lastLine = middleLine + 1;
                // If the clicked item is paper
                if (meta.getDisplayName().equals("+")) {
                    floatStat = item2.getItemMeta().getLore().get(0);
                    if (event.getClick().isLeftClick()) {
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
                    for (int i = save_lore.size(); i <= lastLine + 3; i++) {
                        save_lore.add("");
                    }

                    int line = searchLine(save_lore, floatStat);
                    Bukkit.broadcastMessage(searchLine(meta.getLore(), "Slots") + "");
                    if (searchLine(save_lore, floatStat) == 0) {
                        if (floatStat.contains("Slots")) {
                            Bukkit.broadcastMessage("Slots");
                            save_lore.set(lastLine + 3, ChatColor.GRAY + "Slots: " + c);
                        }
                        save1.setLore(save_lore);
                        save.setItemMeta(save1);
                    } else {
                        String numberOnly = save_lore.get(line).replaceAll("[^0-9]", "");
                        String finalMasho = numberOnly.substring(1, numberOnly.length());
                        int cplus = c + Integer.parseInt(finalMasho);
                        int numon = Integer.parseInt(numberOnly);
                        if (floatStat.contains("Slots")) {
                            save_lore.set(lastLine + 3, ChatColor.GRAY + "Slots: " + cplus);
                        }
                    }
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                }
                addStat(player);


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
                        save_lore.set(middleLine + 1, ChatColor.GRAY + "Can be used by: " + job);
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
                        save_lore.set(lastLine + 1, ChatColor.GRAY + "Type: " + weapon);
                    } else {
                        save_lore.set(searchLine(save_lore, ChatColor.GRAY + "Type: "), ChatColor.GRAY + "Type: " + weapon);
                    }
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                }
                if (event.getCurrentItem() != null && event.getCurrentItem().equals(player.getOpenInventory().getItem(8))) {
                    player.getInventory().getItemInMainHand().setItemMeta(save1);
                    player.closeInventory();
                }
            }
        } else if (player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Scroll Upgrade")) {
            event.setCancelled(true);
            ItemStack item = event.getWhoClicked().getOpenInventory().getItem(event.getSlot());
            int successRate = 0;
            String mainStat = "";
            String whichItem = "";


            // Null checks
            if (item == null || item.getItemMeta() == null || !(item.getItemMeta().hasDisplayName())) return;

            // item info
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            ItemStack item1 = new ItemStack(Material.LEATHER);

            // Fixing Armor & Crossbow
            if (!(event.getWhoClicked().getOpenInventory().getItem(event.getSlot() + 1) == null)) {
                item1 = event.getWhoClicked().getOpenInventory().getItem(event.getSlot() + 1);
            }
            // Null checks
            if (item1 != null && item1.getItemMeta() != null) {

                // Item1 info
                ItemMeta meta1 = item1.getItemMeta();
                List<String> lore1 = meta1.getLore();
                // Item2 info
                ItemStack item2 = event.getWhoClicked().getOpenInventory().getItem(event.getSlot() - 1);
                // Save block info
                ItemStack save = event.getWhoClicked().getOpenInventory().getItem(8);
                ItemMeta save1 = save.getItemMeta();
                List<String> save_lore = save1.getLore();

                String floatStat = "";
                int c = 0;

                // Clicking on the item will run these blocks of code
                if (meta.getDisplayName().equals("-")) {
                    floatStat = lore.get(0);
                    int valueOf = Integer.parseInt(lore1.get(0));

                    // If the clicked item isn't paper
                    // And if the clicked item is clicked with the left button
                    if (event.getClick().isLeftClick()) {
                        lore1.set(0, valueOf - 1 + "");
                        meta1.setLore(lore1);
                        item1.setItemMeta(meta1);
                        c = -1;
                    }

                    // Otherwise, this will run if the item is clicked with the right button
                    else {
                        lore1.set(0, valueOf - 10 + "");
                        meta1.setLore(lore1);
                        item1.setItemMeta(meta1);
                        c = -10;
                    }

                    //
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
                    if (floatStat.equals("Scroll Success Chance")) {
                        if (event.getClick().isLeftClick()) {
                            successRate++;
                        } else if (event.getClick().isRightClick()) {
                            successRate += 10;
                        }
                    }
                    floatStat = item2.getItemMeta().getLore().get(0);
                    if (event.getClick().isLeftClick()) {
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
                        save1.setLore(save_lore);
                        save.setItemMeta(save1);
                    } else {
                        String numberOnly = save_lore.get(line).replaceAll("[^0-9]", "");
                        String finalMasho = numberOnly.substring(1, numberOnly.length());
                        int cplus = c + Integer.parseInt(finalMasho);
                        int numon = Integer.parseInt(numberOnly);
                        save_lore.set(line, statreturn(floatStat, numon + cplus));
                    }
                    save1.setLore(save_lore);
                    save.setItemMeta(save1);
                } else if (meta.getDisplayName().equals("-")) {
                    if (floatStat.equals("Scroll Success Chance")) {
                        if (event.getClick().isLeftClick()) {
                            successRate = successRate - 1;
                        } else {
                            successRate = successRate - 10;
                        }
                    }
                }
                if (mainStat.equals("")) {
                    mainStat = floatStat;
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
                    whichItem = weapon;
                }
                save1.setDisplayName(updateName(successRate, whichItem, mainStat));
                save.setItemMeta(save1);
            }
        }
    }
}