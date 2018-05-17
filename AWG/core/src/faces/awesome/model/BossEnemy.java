package faces.awesome.model;

import faces.awesome.AwesomeGame;

/* This class represents a boss enemy. Simply a stronger form of enemy. If this dies, the player "wins" the game.*/

public class BossEnemy extends Enemy {

    public BossEnemy(Position pos, AwesomeGame game) {
        super(pos, game);
        baseDamage = 5;
        health = maxHealth*2;
        name = "bossEnemy";
    }

    public boolean checkDeath(){
        if(this.getHealth() == 0){
            return true;
        }

        return false;
    }

}
