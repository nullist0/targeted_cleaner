package per.nullist.targetedcleaner.entity

interface ProcessRepository {
    fun readAllProcessPackages() : List<String>
    fun killProcess(packageName: String)
}
