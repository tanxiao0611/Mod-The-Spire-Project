package snake2025.events;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.cards.blue.Defend_Blue;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.cards.purple.Defend_Watcher;
import com.megacrit.cardcrawl.cards.purple.Strike_Purple;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import static snake2025.BasicMod.imagePath;
import static snake2025.BasicMod.makeID;

public class StrikeAndDefense extends PhasedEvent {
    public static final String ID = makeID("StrikeAndDefense");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String IMG = imagePath("events/StrikeAndDefense.jpg");

    public StrikeAndDefense(){
        super(ID, NAME, IMG);
    registerPhase("start", new TextPhase(DESCRIPTIONS[0])
            .addOption(new TextPhase.OptionInfo(OPTIONS[0])
                    .setOptionResult((i) -> {
                        AbstractPlayer player = AbstractDungeon.player;
                        for(int j = 0; j < 5; j++){
                            if(player instanceof Ironclad){
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Strike_Red(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Defend_Red(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            }
                            else if(player instanceof TheSilent){
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Strike_Green(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Defend_Green(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            }
                            else if(player instanceof Defect){
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Strike_Blue(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Defend_Blue(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            }
                            else if(player instanceof Watcher){
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Strike_Purple(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Defend_Watcher(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            }
                        }
                        transitionKey("gained_cards");
                    }))
            .addOption(OPTIONS[1], (i) -> transitionKey("ignored_offer")));
        registerPhase("gained_cards", new TextPhase(DESCRIPTIONS[1]).addOption(OPTIONS[2], (i) -> openMap())); // End event
        registerPhase("ignored_offer", new TextPhase(DESCRIPTIONS[2]).addOption(OPTIONS[2], (i) -> openMap())); // End event
        transitionKey("start");
    }
}
