package xyz.luakionmc.luakion.protocol.login.data;

import com.google.gson.Gson;

public record LoginDisconnectData(String text) {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
