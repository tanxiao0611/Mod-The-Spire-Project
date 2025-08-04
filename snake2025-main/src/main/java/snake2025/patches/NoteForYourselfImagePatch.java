package snake2025.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.events.shrines.NoteForYourself;

public class NoteForYourselfImagePatch {

        @SpirePatch(
                clz = NoteForYourself.class,
                method = SpirePatch.CONSTRUCTOR
        )
        public static class ReplaceNoteForYourselfImage{
            @SpirePostfixPatch
            public static void replaceImage(NoteForYourself __instance) {
                __instance.imageEventText.loadImage("snake2025/images/events/NoteForYourself.jpg");
            }
        }


    }

