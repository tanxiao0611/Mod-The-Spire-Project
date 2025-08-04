package snake2025.events;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Mango;
import com.megacrit.cardcrawl.relics.Pear;
import com.megacrit.cardcrawl.relics.Strawberry;

import static snake2025.BasicMod.imagePath;
import static snake2025.BasicMod.makeID;

public class FruitRelics extends PhasedEvent {
    public static final String ID = makeID("FruitRelics");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    //For this example, an image from a basegame event is used.
    //private static final String IMG = "images/events/weMeetAgain.jpg";
    //To use your own image, it would look more like
    private static final String IMG = imagePath("events/FruitRelics.jpg");
    //This would load yourmod/images/events/ExampleEvent.jpg

    public FruitRelics() {
        super(ID, NAME, IMG);
        registerPhase("start", new TextPhase(DESCRIPTIONS[0])
                .addOption(new TextPhase.OptionInfo(OPTIONS[0])
                        .enabledCondition(() -> AbstractDungeon.player.gold >= 30, OPTIONS[1])
                        .setOptionResult((i) -> {
                            AbstractRelic relic = getRandomFruitRelic();
                            AbstractDungeon.player.loseGold(30); // Deduct some gold for the relic
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
                            AbstractEvent.logMetricObtainRelicAtCost(ID, "Obtained Fruit Relic", relic, 30); // Log this action
                            transitionKey("they took the fruit");
                        }))
                .addOption(OPTIONS[2], (i) -> transitionKey("they didn't take the fruit")));
        registerPhase("they took the fruit", new TextPhase(DESCRIPTIONS[1]).addOption(OPTIONS[3], (i) -> openMap()));
        registerPhase("they didn't take the fruit", new TextPhase(DESCRIPTIONS[2]).addOption(OPTIONS[3], (i) -> openMap()));
        transitionKey("start");
    }
    private AbstractRelic getRandomFruitRelic() {
        // Randomly select one of the fruit relics
        int choice = AbstractDungeon.cardRandomRng.random(0, 2); // Generate a random number (0-2) to select a relic
        switch (choice) {
            case 0:
                return new Mango(); // Mango relic (assumed custom relic)
            case 1:
                return new Pear();  // Pear relic (assumed custom relic)
            case 2:
                return new Strawberry(); // Strawberry relic (assumed custom relic)
            default:
                return new Mango(); // Default to Mango if there's an issue
        }
    }


}
