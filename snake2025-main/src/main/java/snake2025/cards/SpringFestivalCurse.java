package snake2025.cards;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import snake2025.util.CardStats;

public class SpringFestivalCurse extends BaseCard {
    public static final String ID = makeID("SpringFestivalCurse");
    private static final CardStats info = new CardStats(
            CardColor.CURSE,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.NONE,
            -2
    );
    public SpringFestivalCurse(){
        super(ID, info);
        //this.isEthereal = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Curse, no actions
    }
    @Override
    public void onRemoveFromMasterDeck() {
        AbstractCard babyCurse = new BabyCurse();
        AbstractDungeon.player.masterDeck.addToTop(babyCurse);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpringFestivalCurse();
    }
    @Override
    public void upgrade() {
        // can't upgrade
    }



}
