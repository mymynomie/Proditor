package mymyxyz.noctuaAPI.player;

import mymyxyz.noctuaAPI.items.StoneOfReminder;

import java.util.UUID;

public class Profile {

    private String name;
    private UUID uuid;
    private final Classe classe = new Classe();
    private StoneOfReminder.StoneOfReminderConfig stone_of_reminder = new StoneOfReminder.StoneOfReminderConfig();

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Classe getClasse() {
        return classe;
    }

    public StoneOfReminder.StoneOfReminderConfig getStone_of_reminder() {
        return stone_of_reminder;
    }
}
