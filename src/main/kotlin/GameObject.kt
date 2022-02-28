import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.openrndr.math.Vector2
import org.openrndr.math.mod
import kotlin.random.Random

class ShipData : GameObject() {
    override var size: Double = 40.0
    var visualAngle: Double = 0.0

    fun fire(game: Game) {
        val ship = this
        val rnds = (1..3).random()
        if(rnds==1){
            game.gameObjects.add(BulletData(ship.speed * 4.0, ship.visualAngle, ship.position*0.9))
        }
        if(rnds==2){
            game.gameObjects.add(BulletData(ship.speed * 4.0, ship.visualAngle, ship.position*0.9))
            game.gameObjects.add(BulletData(ship.speed * 4.0, ship.visualAngle, ship.position))
        }
        if(rnds==3){
            game.gameObjects.add(BulletData(ship.speed * 4.0, ship.visualAngle, ship.position*0.9))
            game.gameObjects.add(BulletData(ship.speed * 4.0, ship.visualAngle, ship.position))
            game.gameObjects.add(BulletData(ship.speed * 4.0, ship.visualAngle, ship.position*1.1))
        }
    }
}

class AsteroidData(speed: Double = 0.0, angle: Double = 0.0, position: Vector2 = Vector2.ZERO) :
    GameObject(speed, angle, position) {
    override var size: Double = 120.0
}

class BulletData(speed: Double = 0.0, angle: Double = 0.0, position: Vector2 = Vector2.ZERO) :
    GameObject(speed, angle, position) {
    override val size: Double = 4.0
}

sealed class GameObject(speed: Double = 0.0, angle: Double = 0.0, position: Vector2 = Vector2.ZERO) {
    var speed by mutableStateOf(speed)
    var angle by mutableStateOf(angle)
    var position by mutableStateOf(position)
    var movementVector get() = (Vector2.UNIT_X * speed).rotate(angle)
    set(value) {
        speed = value.length
        angle = value.angle()
    }
    abstract val size: Double // Diameter
    fun update(realDelta: Float, game: Game) {
        val obj = this
        val velocity = movementVector * realDelta.toDouble()*0.5
        obj.position += velocity
        obj.position = obj.position.mod(Vector2(game.width.value.toDouble(), game.height.value.toDouble()))
    }
    fun update2(realDelta: Float, game: Game) {
        val obj = this
        val velocity = movementVector * realDelta.toDouble()
        obj.position += velocity
        obj.position = obj.position.mod(Vector2(game.width.value.toDouble(), game.height.value.toDouble()))
    }
    fun update3(realDelta: Float, game: Game) {
        val obj = this
        val velocity = movementVector * realDelta.toDouble()*12.0
        obj.position += velocity
        obj.position = obj.position.mod(Vector2(game.width.value.toDouble(), game.height.value.toDouble()))
    }

    fun update4(realDelta: Float, game: Game) {
        val obj = this
        val velocity = movementVector * realDelta.toDouble()*30.0
        obj.position += velocity
        obj.position = obj.position.mod(Vector2(game.width.value.toDouble(), game.height.value.toDouble()))
    }

    fun overlapsWith(other: GameObject): Boolean {
        // Overlap means the the center of the game objects are closer together than the sum of their radiuses
        return this.position.distanceTo(other.position) < (this.size / 2 + other.size / 2)
    }
}

