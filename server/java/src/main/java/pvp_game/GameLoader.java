package pvp_game;

import io.gamemachine.core.Commands;
import io.gamemachine.core.GameMachineLoader;
import akka.actor.Props;

public class GameLoader {

	public static void load() {
		
		GameMachineLoader.getActorSystem().actorOf(Props.create(CharacterHandler.class), CharacterHandler.name);
		GameMachineLoader.getActorSystem().actorOf(Props.create(HarvestHandler.class), HarvestHandler.name);
		GameMachineLoader.getActorSystem().actorOf(Props.create(CraftingHandler.class), CraftingHandler.name);
		GameMachineLoader.getActorSystem().actorOf(Props.create(CombatHandler.class), CombatHandler.name);
		GameMachineLoader.getActorSystem().actorOf(Props.create(PlayerSkillHandler.class), PlayerSkillHandler.name);
		
		Commands.seedCharacterItem("wood", 50);
		Commands.seedCharacterItem("iron_ore", 50);
	}
}
