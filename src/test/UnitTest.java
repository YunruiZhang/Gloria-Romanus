package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.gloriaromanus.*;
import unsw.backend.*;

public class UnitTest{
    @Test
    public void blahTest(){
        assertEquals("a", "a");
    }
    
    @Test
    public void basic(){
        GameController newGame = new GameController();
        //newGame.setPlayer("Rome");
        //newGame.createUnit("Lugdunensis", "NetFighter", "suck my balls");
        

    }

    public GameController setup(){
        GameController newGame = new GameController();
        newGame.setPlayer("Roman");
    }
}

