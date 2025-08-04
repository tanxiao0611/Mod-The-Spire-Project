package snake2025.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StoreRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = ShopScreen.class, method = "getNewPrice", paramtypez = {StoreRelic.class})
public class ModifyRelicPrice {
    @SpirePrefixPatch
    public static void adjustRelicPrice(ShopScreen __instance, StoreRelic r) {
        if (AbstractDungeon.player.hasRelic("Membership Card")) {
            System.out.println("Membership Card detected for relics. Applying 1.5 multiplier.");
            r.price = MathUtils.round(r.price * AbstractDungeon.merchantRng.random(0.95F, 1.05F));
            r.price = MathUtils.round(r.price * 1.5F); // Custom multiplier
        }
    }
}
