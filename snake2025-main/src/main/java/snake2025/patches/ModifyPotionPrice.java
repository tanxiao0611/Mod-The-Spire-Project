package snake2025.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;

@SpirePatch(clz = ShopScreen.class, method = "getNewPrice", paramtypez = {StorePotion.class})
public class ModifyPotionPrice {
    @SpirePrefixPatch
    public static void adjustPotionPrice(ShopScreen __instance, StorePotion r) {
        if (AbstractDungeon.player.hasRelic("Membership Card")) {
            System.out.println("Membership Card detected for potions. Applying 1.5 multiplier.");
            r.price = MathUtils.round(r.price * AbstractDungeon.merchantRng.random(0.95F, 1.05F));
            r.price = MathUtils.round(r.price * 1.5F); // Custom multiplier
        }
    }
}
