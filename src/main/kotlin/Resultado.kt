interface Resultado{
    val victoria: String
    val fracaso: String
    val suerte: String
    val jugar: String
}
open class ResultadoEN(): Resultado{

    override val victoria: String= "Congratulations!"
    override val fracaso: String= "Better luck next time!"
    override val suerte: String = "Good luck!"
    override val jugar: String = "Let's play!"
}
open class ResultadoESP(): Resultado{

    override val victoria: String= "Victoria!"
    override val fracaso: String= "¡Más suerte para la próxima!"
    override val suerte: String = "¡Buena suerte!"
    override val jugar: String = "¡Jugar!"
}