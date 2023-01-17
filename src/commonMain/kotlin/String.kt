import kotlin.math.max
import kotlin.math.min

fun underlineString(range: IntRange, text: String): String {
    require(range.last <= text.lastIndex && range.first >= 0) {
        "Underline (${range.first}..${range.last}) is out of range (0..${text.lastIndex})."
    }

    val cropRange = max(range.first - 5, 0)..min(range.last + 5, text.lastIndex)
    val crop = text.substring(cropRange)
    var underline = ""

    val newCroppedRange = (range.first - cropRange.first)..(range.last - cropRange.first)
    crop.forEachIndexed { index, _ ->
        if (index in newCroppedRange) {
            underline += "^"
        } else {
            underline += " "
        }
    }

    return crop + "\n" + underline
}

fun underlineString(index: Int, text: String): String {
    return underlineString(index..index, text)
}