package snake2025.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.NoteForYourself;

@SpirePatch(
        clz = NoteForYourself.class,
        method = "update"
)
public class NoteForYourselfLogicPatch {

    @SpirePostfixPatch
    public static void addRegretAfterStoring(NoteForYourself __instance) {
        // Check if a new card has been stored
        if (__instance.saveCard != null) {
                AbstractCard regret = new Regret();
                AbstractDungeon.player.masterDeck.addToTop(regret);

                __instance.saveCard = null;
        }

    }
}

