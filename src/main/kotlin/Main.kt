import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun main() = Window(size = IntSize(800, 900), title = "Asteroids for Desktop") {
    val esp = ResultadoESP()
    val en = ResultadoEN()
    var game = remember { Game(en) }

    var gameObjects = mutableStateListOf<GameObject>()
    val density = LocalDensity.current
    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos {
                game.update(it)
            }
        }
    }

    Column(modifier = Modifier.background(Color(51, 153, 255)).fillMaxHeight()) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button({
                game.startGame(1)
            }) {
                Text(game.idioma.d1)
            }
            Button({
                game.startGame(2)
            }) {
                Text(game.idioma.d2)
            }
            Button({
                game.startGame(3)
            }) {
                Text(game.idioma.d3)
            }
            Button({
                game.startGame(4)
            }) {
                Text(game.idioma.d4)
            }

        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(game.gameStatus, modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 16.dp), color = Color.White)
            Text(game.idioma.cont+": "+game.contador, modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 16.dp), color = Color.White)
            //Text("Score: "+game.contador, modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 16.dp), color = Color.White)
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button({
                game.changeIdioma(esp)
                game.startGame(2)
            }) {
                Text(game.idioma.i1)
            }
            Button({
                game.changeIdioma(en)
                game.startGame(2)
            }) {
                Text(game.idioma.i2)
            }

        }
        Box(modifier = Modifier
            .aspectRatio(1.0f)
            .background(Color(0, 0, 30))
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clipToBounds()
                .pointerMoveFilter(onMove = {
                    with(density) {
                        game.targetLocation  = DpOffset(it.x.toDp(), it.y.toDp())
                    }
                    false
                })
                .clickable() {
                    game.ship.fire(game)
                }
                .onSizeChanged {
                    with(density) {
                        game.width = it.width.toDp()
                        game.height = it.height.toDp()
                    }
                }) {
                game.gameObjects.forEach {
                    when (it) {
                        is ShipData -> Ship(it)
                        is BulletData -> Bullet(it)
                        is AsteroidData -> Asteroid(it)
                    }
                }
            }
        }

    }
}