package pl.otekplay.loveotek.managers;

import lombok.Getter;
import ninja.amp.ampmenus.items.HistoryItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.History;
import pl.otekplay.loveotek.basic.Participant;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.enums.ParticipantType;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.RankingSettings;
import pl.otekplay.loveotek.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HistoryManager {
    @Getter
    private final List<History> historyFights = new ArrayList<>();
    private final ItemStack
            KILL_ITEM = new ItemBuilder(Material.WOOL, 1, (short) 13).toItemStack(),
            ASSIST_ITEM = new ItemBuilder(Material.WOOL, 1, (short) 4).toItemStack(),
            DEATH_ITEM = new ItemBuilder(Material.WOOL, 1, (short) 14).toItemStack();

    public History registerHistory(List<Participant> participants) {
        History history = new History(participants,System.currentTimeMillis());
        historyFights.add(history);
        return history;
    }

    public List<History> getHistory(UUID uuid) {
        List<History> list = new ArrayList<>();
        for (History history : historyFights) {
            for (Participant part : history.getParticipants()) {
                if (!part.getUniqueID().equals(uuid)) {
                    continue;
                }
                list.add(history);
            }
        }
        return list;
    }

    public List<History> getHistory(UUID uuid, ParticipantType type) {
        List<History> list = new ArrayList<>();
        for (History history : historyFights) {
            for (Participant part : history.getParticipants()) {
                if (part.getParticipantType() != type) {
                    continue;
                }
                if (!part.getUniqueID().equals(uuid)) {
                    continue;
                }
                list.add(history);
            }
        }
        return list;
    }

    public ItemMenu generateHistoryFightMenu(UUID uuid) {
        ItemMenu menu = new ItemMenu(Replacer.build(RankingSettings.MESSAGE_HISTORY_ITEM_HEADER).add("%name%", Users.get(uuid).getName()).get()[0], ItemMenu.Size.SIX_LINE, Core.getInstance());
        List<History> histories = getHistory(uuid);
        Collections.sort(histories, (o1, o2) -> Long.compare(o2.getDateFight(),o1.getDateFight()));
        for (int i = 0; i < histories.size(); i++) {
            if(i == 54){
                break;
            }
            menu.setItem(i, new HistoryItem(getPropertyItem(uuid, histories.get(i))));
        }
        return menu;
    }

    private ItemStack getPropertyItem(UUID uuid, History history) {
        Participant part = history.getParticipants().stream().filter(participant -> participant.getUniqueID().equals(uuid)).findFirst().orElse(null);
        Participant death = history.getDeath();
        Participant killer = history.getKiller();
        ItemStack item = null;
        switch (part.getParticipantType()) {
            case KILLER:
                item = KILL_ITEM;
                break;
            case ASSISTANT:
                item = ASSIST_ITEM;
                break;
            case DEATH:
                item = DEATH_ITEM;
                break;
        }
        String[] lore = Replacer.build(RankingSettings.MESSAGE_HISTORY_ITEM_LORE)
                .add("%death%", death.getUser().getName())
                .add("%minus%", death.getDeservePoints() + "")
                .add("%plus%", killer.getDeservePoints() + "")
                .add("%killer%", killer.getUser().getName())
                .add("%get%", part.getDeservePoints() + "")
                .add("%type%", part.getParticipantType().getString())
                .add("%damage%", part.getDamage() + "")
                .add("%percetage%", part.getPercetage() + "")
                .add("%total%", death.getDamage() + "")
                .get();
        return new ItemBuilder(item).setName(TimeUtil.getDate(history.getDateFight())).setLore(lore).toItemStack();
    }


}
