package snake2025.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.events.beyond.SecretPortal;

public class SecretPortalImagePatch {
    @SpirePatch(
            clz = SecretPortal.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class ReplaceSecretPortalImage{
        @SpirePostfixPatch
        public static void replaceImage(SecretPortal __instance) {
                  __instance.imageEventText.loadImage("snake2025/images/events/SecretPortal.jpg");
        }
    }
}
