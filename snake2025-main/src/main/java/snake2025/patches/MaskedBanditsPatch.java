package snake2025.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.events.city.MaskedBandits;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.Snecko;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

@SpirePatch(
        clz = MaskedBandits.class,
        method = SpirePatch.CONSTRUCTOR
)
public class MaskedBanditsPatch {

    @SpirePostfixPatch
    public static void replaceMonsters(MaskedBandits __instance) {
        AbstractMonster snecko = new Snecko();
        MonsterGroup monsterGroup = new MonsterGroup(snecko);
        AbstractDungeon.getCurrRoom().monsters = monsterGroup;
    }
}



