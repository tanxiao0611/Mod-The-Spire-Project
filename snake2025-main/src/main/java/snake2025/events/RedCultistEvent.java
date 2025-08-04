package snake2025.events;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.CultistMask;
import snake2025.relics.RedCultistHeadPiece;

import static snake2025.BasicMod.imagePath;
import static snake2025.BasicMod.makeID;

public class RedCultistEvent extends PhasedEvent {
    public static final String ID = makeID("RedCultistEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    //For this example, an image from a basegame event is used.
    //private static final String IMG = "images/events/weMeetAgain.jpg";
    //To use your own image, it would look more like
    private static final String IMG = imagePath("events/RedCultistEvent.jpg");
    //This would load yourmod/images/events/ExampleEvent.jpg
    public RedCultistEvent() {
        super(ID, NAME, IMG);

        // Register the phases of the event.
        registerPhase("start", new TextPhase(DESCRIPTIONS[0])
                .addOption(new TextPhase.OptionInfo(OPTIONS[0])
                        .enabledCondition(() -> !AbstractDungeon.player.hasRelic(CultistMask.ID), OPTIONS[1]) // if the player doesn't have cultist mask
                        .setOptionResult((i) -> {
                            // Obtain the RedCultistHeadpiece when the first option is selected.
                            AbstractRelic relic = new RedCultistHeadPiece();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
                            AbstractEvent.logMetricObtainRelicAtCost(ID, "Obtained RedCultistHeadPiece", relic, 0); // Optional logging
                            transitionKey("obtained_relic");
                        }))
                .addOption(OPTIONS[2], (i) -> transitionKey("nothing_happens")));
        registerPhase("obtained_relic", new TextPhase(DESCRIPTIONS[1]).addOption(OPTIONS[3], (i) -> openMap()));
        registerPhase("nothing_happens", new TextPhase(DESCRIPTIONS[2]).addOption(OPTIONS[3], (i) -> openMap()));

        transitionKey("start"); // Start the event with the first phase.
    }
}
