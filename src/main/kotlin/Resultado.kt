interface Resultado{
    val victoria: String
    val fracaso: String
    val suerte: String
    val jugar: String
    val d1: String
    val d2: String
    val d3: String
    val d4: String
    val i1: String
    val i2: String
    val cont: String
}
open class ResultadoEN(): Resultado{

    override val victoria: String= "Congratulations!"
    override val fracaso: String= "Better luck next time!"
    override val suerte: String = "Good luck!"
    override val jugar: String = "Let's play!"
    override val d1: String = "Easy"
    override val d2: String = "Normal"
    override val d3: String = "Hard"
    override val d4: String = "Legendary"
    override val i1: String = "Spanish"
    override val i2: String = "English"
    override val cont: String = "Death Counter"
}
open class ResultadoESP(): Resultado{

    override val victoria: String= "Victoria!"
    override val fracaso: String= "¡Más suerte para la próxima!"
    override val suerte: String = "¡Buena suerte!"
    override val jugar: String = "¡Jugar!"
    override val d1: String = "Fácil"
    override val d2: String = "Normal"
    override val d3: String = "Difícil"
    override val d4: String = "Legendario"
    override val i1: String = "Español"
    override val i2: String = "Inglés"
    override val cont: String = "Contador de muertes"
}