package per.nullist.targetedcleaner.view_model.converter

class IntervalConverterImpl : IntervalConverter {
    override fun convertToMillis(interval: Int): Long = interval * MILLISECOND_PER_MINUTE

    override fun convertToMin(interval: Long): Int = (interval / MILLISECOND_PER_MINUTE).toInt()

    companion object {
        private const val MILLISECOND_PER_MINUTE = 60 * 1000L
    }
}