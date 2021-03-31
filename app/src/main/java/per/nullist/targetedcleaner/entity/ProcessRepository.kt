package per.nullist.targetedcleaner.entity

interface ProcessRepository {
    suspend fun getAllProcessPackages(): List<String>
    suspend fun killProcess(packageName: String)
}
