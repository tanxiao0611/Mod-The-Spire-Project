package snake2025.cards;

import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.actions.common.*;
import snake2025.util.CardStats;

public class BabyCurse extends BaseCard {
    public static final String ID = makeID("BabyCurse");
    private static final CardStats info = new CardStats(
            CardColor.CURSE,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.NONE,
            -2
    );


    public BabyCurse() {
        super(ID, info);
        //this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //curse
    }

    @Override
    public void triggerOnExhaust(){
        addToBot(new MakeTempCardInDrawPileAction(new Dazed(), 1, true, true));
        addToBot(new MakeTempCardInDrawPileAction(new Burn(), 1, true, true));
    }

    @Override
    public void atTurnStart() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new WeakPower(AbstractDungeon.player, 1, false)));
    }

    @Override
    public void onRemoveFromMasterDeck() {
        AbstractCard wound = new Wound();
        AbstractDungeon.player.masterDeck.addToTop(wound);
    }

    @Override
    public AbstractCard makeCopy() {
        return new BabyCurse();
    }

    @Override
    public void upgrade() {
        // curse
    }
}
