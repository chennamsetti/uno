/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nus.uno.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@Path("/games")
@Consumes("application/json")
@Produces("application/json")
public class GameResource {

    @GET
    public JsonArray getAll() {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (final JsonObject game : GameStore.getAll()) {
            arrayBuilder.add(game);
        }
        return arrayBuilder.build();
    }

    @GET
    @Path("{id}")
    public JsonObject get(@PathParam("id") final int id) {
        return GameStore.get(id);
    }

    @DELETE
    @Path("{id}")
    public JsonObject remove(@PathParam("id") final int id) {
        return GameStore.remove(id);
    }

    @DELETE
    public void removeAll() {
        GameStore.removeAll();
    }

    @POST
    public JsonArray store(final JsonObject game) {
        return Json.createArrayBuilder().add(GameStore.store(game)).build();
    }
    
    @POST
    @Path("{id}")
    public JsonObject start(@PathParam("id") final int id) {

        JsonObject json = GameStore.start(id);

        return json;
    }

}
