package mymyxyz.noctuaAPI.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mymyxyz.noctuaAPI.player.Profile;

import java.util.UUID;

public class ProfileManager {

    private final Gson gson;

    public ProfileManager() {
        this.gson = createGsonInstance();
    }

    private Gson createGsonInstance() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();

    }

    public String serialize(Profile profile) {
        return this.gson.toJson(profile);
    }

    public Profile deserialize(String json) {
        return this.gson.fromJson(json, Profile.class);
    }

    public Profile loadJson(UUID uuid) {
        String json = FileUtils.loadContent(FileUtils.file(uuid));
        return this.deserialize(json);
    }
}
