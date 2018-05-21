package faces.awesome.events;

import faces.awesome.model.Enemy;

public class EnemyDiedEvent {

    private Enemy enemy;

    public EnemyDiedEvent(Enemy enemy) {

        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

}
