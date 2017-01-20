package pl.otekplay.loveotek.threads;

import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.JsonObject;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PasteThread extends Thread {
    private final ByteArrayOutputStream bout;

    public PasteThread(ByteArrayOutputStream bout) {
        super("Timings paste thread");
        this.bout = bout;
    }

    @Override
    public synchronized void start() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://timings.spigotmc.org/paste").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(false);
            try (final OutputStream out = con.getOutputStream()) {
                out.write(this.bout.toByteArray());
            }
            JsonObject location = new Gson().fromJson(new InputStreamReader(con.getInputStream()), JsonObject.class);
            con.getInputStream().close();
            String id = location.get("key").getAsString();
            Replacer.build(GlobalSettings.MESSAGE_SERVER_TIMINGS_PASTE).add("%link%", "https://www.spigotmc.org/go/timings?url=" + id).broadcast(UserRank.HEADADMIN);
        } catch (IOException var5) {
            Replacer.build(GlobalSettings.MESSAGE_SERVER_TIMINGS_FAILED).broadcast(UserRank.HEADADMIN);
        }
    }
}