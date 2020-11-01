package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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
        /*GameController newGame = new GameController();
        Player player1 = newGame.setPlayer("Rome");
        Player player2 = newGame.setPlayer("Gaul");
        Unit py1unit = newGame.createUnit("Lugdunensis", "NetFighter", "suck my balls");
        Unit py2unit = newGame.createUnit("Achaia", "NetFighter", "SF1000");
        newGame.bulid(player1, "TroopProduction", "Lugdunensis");
        newGame.bulid(player2, "TroopProduction", "Achaia");
        newGame.addsolider(player1, "Lugdunensis", py1unit, 100);
        newGame.addsolider(player2, "Achaia", py1unit, 100);*/
        GameController newGame = new GameController();
        //set up the Rome 
        Player temp = newGame.setPlayer("Rome");
        Province MS = newGame.getProvinceFromString("Moesia Superior");
        Unit x = newGame.createUnit("Moesia Superior", "Crossbowman", "sps");
        Unit y = newGame.createUnit("Moesia Superior", "Druid", "meep");
        newGame.bulid(temp, "TroopProduction", "Moesia Superior");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.addsolider(temp, "Moesia Superior", x, 7);
        newGame.addsolider(temp, "Moesia Superior", x, 2);
        newGame.addsolider(temp, "Moesia Superior", x, 4);
        ArrayList<Object[]> trains = MS.getSoldierTraining();
        assertEquals(trains.size(), 3);
        assertEquals(x.getSoldiers(), 0);
        newGame.nextTurn();
        assertEquals(trains.size(), 1);
        assertEquals(x.getSoldiers(), 9);
        newGame.nextTurn();
        assertEquals(trains.size(), 0);
        assertEquals(x.getSoldiers(), 13);
        newGame.addsolider(temp, "Moesia Superior", y, 6);
        assertEquals(trains.size(), 0);
        assertEquals(y.getSoldiers(), 0);
        newGame.upgrade(temp, "TroopProduction", "Moesia Superior");
        newGame.addsolider(temp, "Moesia Superior", y, 6);
        assertEquals(trains.size(), 1);
        assertEquals(y.getSoldiers(), 0);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(trains.size(), 0);
        assertEquals(y.getSoldiers(), 6);

        // test move

        ArrayList<Unit> Army = new ArrayList<Unit>();
        Army.add(x);
        Army.add(y);
        newGame.MoveUnit(temp, Army, "Moesia Superior", "Thracia");
        assertEquals(x.getLocation(), newGame.getProvinceFromString("Thracia"));

        //add gual
        Player temp1 = newGame.setPlayer("Gaul");
        Province BEP = newGame.getProvinceFromString("Bithynia et Pontus");
        Unit x1 = newGame.createUnit("Bithynia et Pontus", "Crossbowman", "sps");
        Unit y1 = newGame.createUnit("Bithynia et Pontus", "Druid", "meep");
        newGame.bulid(temp1, "TroopProduction", "Bithynia et Pontus");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.addsolider(temp1, "Bithynia et Pontus", x1, 7);
        newGame.addsolider(temp1, "Bithynia et Pontus", x1, 2);
        newGame.addsolider(temp1, "Bithynia et Pontus", x1, 4);
        ArrayList<Object[]> trains1 = BEP.getSoldierTraining();
        assertEquals(trains1.size(), 3);
        assertEquals(x1.getSoldiers(), 0);
        newGame.nextTurn();
        assertEquals(trains1.size(), 1);
        assertEquals(x1.getSoldiers(), 9);
        newGame.nextTurn();
        assertEquals(trains1.size(), 0);
        assertEquals(x1.getSoldiers(), 13);
        newGame.addsolider(temp1, "Bithynia et Pontus", y1, 6);
        assertEquals(trains1.size(), 0);
        assertEquals(y1.getSoldiers(), 0);
        newGame.upgrade(temp1, "TroopProduction", "Bithynia et Pontus");
        newGame.addsolider(temp1, "Bithynia et Pontus", y1, 6);
        assertEquals(trains1.size(), 1);
        assertEquals(y1.getSoldiers(), 0);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(trains1.size(), 0);
        assertEquals(y1.getSoldiers(), 6);



        newGame.Battle(temp, Army, "Thracia", "Bithynia et Pontus");

        //atteck Bithynia et Pontus
        //from Thracia

    }

}

