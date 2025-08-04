package snake2025.relics;

import basemod.BaseMod;
import basemod.interfaces.RelicGetSubscriber;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.CultistMask;

import static basemod.BaseModInit.DESCRIPTION;
import static snake2025.BasicMod.makeID;

public class RedCultistHeadPiece extends BaseRelic implements RelicGetSubscriber{
    private static final String NAME = "RedCultistHeadPiece"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.

    public RedCultistHeadPiece(){
        super(ID, NAME, RARITY, SOUND);
    BaseMod.subscribe(this);
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTION;
    }
    @Override
    public void atBattleStart() {
        // Trigger the CAW CAAAW sound effect
        //CardCrawlGame.sound.play("CAW");  Assuming you have a sound file named  in your resources
        // Optionally, show a visual effect or a message
        CardCrawlGame.sound.play("VO_CrowCultist_1a");
        AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "无论新友与故交，明年春来再相邀～", true));
    }
    @Override
    public void receiveRelicGet(AbstractRelic relic) {
        // Check if the acquired relic is the Cultist Mask
        if (relic.relicId.equals(CultistMask.ID)) {
            removeSelf(); // Remove the RedCultistHeadPiece if the Cultist Mask is acquired
        }
    }
    private void removeSelf() {
        // Remove the RedCultistHeadPiece relic
        AbstractDungeon.player.loseRelic(this.relicId);
    }
    @Override
    public AbstractRelic makeCopy() {
        return new RedCultistHeadPiece();
    }
}
