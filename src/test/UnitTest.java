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
        GameController newGame = new GameController();
        //set up the Rome 
        Player temp = newGame.setPlayer("Rome");
        Province MS = newGame.getProvinceFromString("Moesia Superior");
        Unit x = newGame.createUnit("Moesia Superior", "Crossbowman", "my1");
        Unit y = newGame.createUnit("Moesia Superior", "Druid", "my2");
        newGame.bulid(temp, "TroopProduction", "Moesia Superior");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.addsolider(temp, "Moesia Superior", x, 7);
        newGame.addsolider(temp, "Moesia Superior", x, 2);
        newGame.addsolider(temp, "Moesia Superior", x, 4);
        newGame.addsolider(temp, "Moesia Superior", x, 9);
        newGame.addsolider(temp, "Moesia Superior", x, 9);
        newGame.addsolider(temp, "Moesia Superior", x, 9);
        ArrayList<Object[]> trains = MS.getSoldierTraining();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.addsolider(temp, "Moesia Superior", y, 6);
        newGame.upgrade(temp, "TroopProduction", "Moesia Superior");
        newGame.addsolider(temp, "Moesia Superior", y, 6);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();

        // test move
        ArrayList<Unit> Army = new ArrayList<Unit>();
        Army.add(x);
        Army.add(y);
        newGame.MoveUnit(temp, Army, "Moesia Superior", "Thracia");
        assertEquals(x.getLocation(), newGame.getProvinceFromString("Thracia"));

        //add gual
        Player temp1 = newGame.setPlayer("Gaul");
        Province BEP = newGame.getProvinceFromString("Bithynia et Pontus");
        Unit x1 = newGame.createUnit("Bithynia et Pontus", "Crossbowman", "enemy1");
        Unit y1 = newGame.createUnit("Bithynia et Pontus", "Druid", "enemy2");
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
        newGame.addsolider(temp1, "Bithynia et Pontus", y1, 4);
        assertEquals(trains1.size(), 1);
        assertEquals(y1.getSoldiers(), 0);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(trains1.size(), 0);
        assertEquals(y1.getSoldiers(), 4);


        if(newGame.Battle(temp, Army, "Thracia", "Bithynia et Pontus")){
            System.out.println("WE WON THE BATTLE HOORRRAAAAYYYYY :) :) :) :) :) :) :) :) :) :) :) :) ");
        } else {
            System.out.println("FUCK MAN WE LOST THE BATTLE FML :(( :(( :(( :(( :(( :(( :(( :(( :(( :(( :(( :(( ");
        }

        //atteck Bithynia et Pontus
        //from Thracia

    }

}

