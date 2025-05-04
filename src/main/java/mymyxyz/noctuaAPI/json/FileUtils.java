package mymyxyz.noctuaAPI.json;

import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.player.Profile;
import mymyxyz.noctuaAPI.player.classe.Assassin;
import net.minecraft.entity.player.EntityPlayer;

import java.io.*;
import java.util.UUID;

public class FileUtils {

    public static String ReturnPath(UUID uuid) {
        return "Proditor/" + uuid + "/";
    }

    public static File file(UUID uuid) {
        return new File(ReturnPath(uuid) + "user.json");
    }

    public static void initJson(EntityPlayer player) throws IOException {
        profileInit(player);
    }

    public static void profileInit(EntityPlayer player) throws IOException {
        ProfileManager profileManager = new ProfileManager();
        if (isNotExisteFile(file(player.getUniqueID()))) {
            Profile profile = new Profile();
            profile.setName(player.getName());
            profile.setUuid(player.getUniqueID());
            profile.getClasse().setActive(EnumClasse.NO_CLASS);
            profile.getClasse().setAssassin(new Assassin());
            save(player.getUniqueID(), profileManager.serialize(profile));
        } else {
            Profile profile = profileManager.loadJson(player.getUniqueID());
            if (!profile.getName().equals(player.getName())) {
                profile.setName(player.getName());
            }
        }
    }

    public static boolean isNotExisteFile(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return true;
        }
        return false;
    }

    public static void save(UUID uuid, String text) {
        File file = file(uuid);
        final FileWriter fw;

        try {
            if (isNotExisteFile(file)) {
                save(uuid, text);
            } else {
                fw = new FileWriter(file);
                fw.write(text);
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadContent(File file) {
        if (file.exists()) {
            try {
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                final StringBuilder text = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }
                reader.close();
                return text.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
