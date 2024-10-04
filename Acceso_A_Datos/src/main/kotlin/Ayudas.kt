import java.math.BigDecimal
import java.math.RoundingMode

class Ayudas {
    companion object {
        fun round(num: Double, dec: Int): Double {
            return BigDecimal(num * 1.21).setScale(dec, RoundingMode.HALF_EVEN).toDouble()
        }
    }
}