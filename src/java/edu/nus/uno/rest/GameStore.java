/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nus.uno.rest;

import edu.nus.uno.model.Game;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Kishore
 */
public class GameStore {

    private static final Map<Integer, JsonObject> storage = new LinkedHashMap<>();
    private static final Map<Integer, Game> gameList = new LinkedHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static int store(final JsonObject game) {
        final int id = counter.addAndGet(1);

        Game myGame = new Game();
        myGame.createNewGame(""+game.getJsonString("name"));
        
        myGame.changeGameStatus();
        
        gameList.put(id, myGame);
        
        JsonObject value = Json.createObjectBuilder()
                .add("id", "" + id)
                .add("name", game.getJsonString("name"))
                .add("status", ""+myGame.getStatus()).build();
        
        storage.put(id, value);
        return id;
    }
    
     public static JsonObject start(final int id) {

        JsonObject game = storage.get(id);

        Game myGame = gameList.get(id);

        // adding 2 dummy players
        myGame.addPlayer("abcd111", "Kishore");
        myGame.addPlayer("xyz111", "Ajay");
        
        myGame.changeGameStatus();
        
        System.out.println("testing... \n"+myGame.toString());
        
        JsonObject value = Json.createObjectBuilder()
                .add("id", "" + id)
                .add("name", game.getJsonString("name"))
                .add("status", ""+myGame.getStatus()).build();
        
        return storage.put(id, value);
    }

    public static JsonObject get(final int id) {
        return storage.get(id);
    }

    public static Collection<JsonObject> getAll() {
        return storage.values();
    }

    public static JsonObject remove(final int id) {
        return storage.remove(id);
    }

    public static void removeAll() {
        storage.clear();
    }

    /**
     * Prevent initialization.
     */
    private GameStore() {
    }
}
