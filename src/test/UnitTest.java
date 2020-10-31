package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import unsw.backend.*;

public class UnitTest{
    @Test
    public void blahTest(){
        assertEquals("a", "a");
    }
    
    @Test
    public void basic(){
        GameController newGame = new GameController();
        Player player1 = newGame.setPlayer("Rome");
        Player player2 = newGame.setPlayer("Gaul");
        Unit py1unit = newGame.createUnit("Lugdunensis", "NetFighter", "suck my balls");
        Unit py2unit = newGame.createUnit("Achaia", "NetFighter", "SF1000");
        newGame.bulid(player1, "TroopProduction", "Lugdunensis");
        newGame.bulid(player2, "TroopProduction", "Achaia");
        newGame.addsolider(player1, "Lugdunensis", py1unit, 100);
        newGame.addsolider(player2, "Achaia", py1unit, 100);


    }

    public GameController setup(){
        GameController newGame = new GameController();
        newGame.setPlayer("Roman");
    }
}

