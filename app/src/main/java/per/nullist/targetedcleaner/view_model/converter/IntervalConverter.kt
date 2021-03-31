package per.nullist.targetedcleaner.view_model.converter

interface IntervalConverter {
    fun convertToMillis(interval: Int) : Long
    fun convertToMin(interval: Long) : Int
}
