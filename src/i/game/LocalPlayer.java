package i.game;

import i.enums.CardShape;
import i.enums.CardinalDirection;
import i.enums.PlayerID;
import i.enums.SpellID;
import i.tile.PropertyTile;
import i.tile.Tile;

import java.util.Random;

public class LocalPlayer implements PlayerRepresentative {

    private static final Random r = new Random();
    private static final CardinalDirection[] possibleDirections = {
        CardinalDirection.SOUTH, CardinalDirection.EAST,
        CardinalDirection.NORTH, CardinalDirection.WEST
    };
    private static final CardShape[] possibleCards = {
        CardShape.SHAPE1, CardShape.SHAPE2, CardShape.SHAPE3
    };
    private static final PlayerID[] possiblePlayers = {
        PlayerID.PLAYER1, PlayerID.PLAYER2, PlayerID.PLAYER3, PlayerID.PLAYER4
    };
    private static final SpellID[] possibleSpells = {
        SpellID.SPELL1, SpellID.SPELL2, SpellID.SPELL3, SpellID.SPELL4, SpellID.SPELL5
    };

    private CardinalDirection anyRandomDirection() {
        return possibleDirections[r.nextInt(possibleDirections.length)];
    }

    private CardShape anyRandomCard() {
        return possibleCards[r.nextInt(possibleCards.length)];
    }

    private PlayerID anyRandomPlayer() {
        return possiblePlayers[r.nextInt(possiblePlayers.length)];
    }

    private SpellID anyRandomSpell() {
        return possibleSpells[r.nextInt(possibleSpells.length)];
    }

    private PropertyTile generateRandomPropertyTile() {
        return new PropertyTile(r.nextInt(100), r.nextInt(100));
    }

    // Public Methods

    @Override
    public SpellID getSpellCast(int q, SpellID[] availableSpells) {
        return anyRandomSpell();
    }

    @Override
    public int getUsersRoll(int q) { return r.nextInt(6) + 1; }

    @Override
    public CardinalDirection forkInTheRoad(int q,CardinalDirection[] availableDirections) {
        return anyRandomDirection();
    }

    @Override
    public boolean buyThisTile(int q,PropertyTile tileForPurchase) { return true; }

    @Override
    public CardShape placeWhichCard(int q) {
        return anyRandomCard();
    }

    @Override
    public CardShape swapCardOnThisTile(int q,PropertyTile tileForSwapping) {
        return anyRandomCard();
    }

    @Override
    public Tile swapCardOnWhichTile(int q) {
        return generateRandomPropertyTile();
    }

    @Override
    public Tile upgradeWhichTile(int q, PropertyTile[] upgradeableTiles) {
        return generateRandomPropertyTile();
    }

    @Override
    public int upgradeToWhatLevel(int q, PropertyTile upgradingTile) {
        return r.nextInt(5) + 1;
    }

    @Override
    public PropertyTile sellWhichTile(int q, PlayerID sellingPlayer) {
        return generateRandomPropertyTile();
    }

    @Override
    public PlayerID castOnPlayer(int q, SpellID spellCast) {
        return anyRandomPlayer();
    }
}
