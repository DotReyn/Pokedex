package dot.reyn.pokedex

import com.cobblemon.mod.common.api.storage.player.PlayerDataExtension
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import net.minecraft.util.Identifier

class PokedexDataExtension(
    val caughtSpecies: MutableSet<Identifier> = hashSetOf()
): PlayerDataExtension {

    companion object {
        const val NAME_KEY = "reynspokedex"

        private val GSON = GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create()
    }

    override fun name(): String {
        return NAME_KEY
    }

    override fun serialize(): JsonObject {
        val jsonObject = GSON.toJsonTree(this).asJsonObject
        jsonObject.addProperty(PlayerDataExtension.NAME_KEY, this.name())
        return jsonObject
    }

    override fun deserialize(json: JsonObject): PlayerDataExtension {
        return GSON.fromJson(json, PokedexDataExtension::class.java)
    }
}