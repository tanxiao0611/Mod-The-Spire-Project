package snake2025.patches;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;

import java.lang.reflect.Field;
import java.util.ArrayList;

@SpirePatch(clz = ShopScreen.class, method = "applyDiscount")
public class ModifyApplyDiscountPostfix {
    @SpirePostfixPatch
    public static void adjustDiscountAfter(ShopScreen __instance, float multiplier, boolean affectPurge) {
        if (AbstractDungeon.player.hasRelic("Membership Card") && multiplier == 0.5F) {
            System.out.println("Membership Card detected. Adjusting multiplier to 1.5 after discount application.");

            try {
                // Access `relics` field
                Field relicsField = ShopScreen.class.getDeclaredField("relics");
                relicsField.setAccessible(true);
                ArrayList<StoreRelic> relics = (ArrayList<StoreRelic>) relicsField.get(__instance);

                // Adjust relic prices
                for (StoreRelic storeRelic : relics) {
                    storeRelic.price = MathUtils.round(storeRelic.price / 0.5F * 1.5F);
                    System.out.println("Relic price adjusted to: " + storeRelic.price);
                }

                // Access `potions` field
                Field potionsField = ShopScreen.class.getDeclaredField("potions");
                potionsField.setAccessible(true);
                ArrayList<StorePotion> potions = (ArrayList<StorePotion>) potionsField.get(__instance);

                // Adjust potion prices
                for (StorePotion storePotion : potions) {
                    storePotion.price = MathUtils.round(storePotion.price / 0.5F * 1.5F);
                    System.out.println("Potion price adjusted to: " + storePotion.price);
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            // Adjust card prices
            AbstractDungeon.shopScreen.coloredCards.forEach(card -> {
                card.price = MathUtils.round(card.price / 0.5F * 1.5F);
            });
            AbstractDungeon.shopScreen.colorlessCards.forEach(card -> {
                card.price = MathUtils.round(card.price / 0.5F * 1.5F);
            });

            // Adjust purge cost
            if (affectPurge) {
                ShopScreen.actualPurgeCost = MathUtils.round(ShopScreen.actualPurgeCost / 0.5F * 1.5F);
            }
        }
    }
}
