package per.nullist.targetedcleaner.entity

interface ProcessRepository {
    val allProcessPackages: List<String>
    fun killProcess(packageName: String)
}
