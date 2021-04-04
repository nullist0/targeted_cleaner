package per.nullist.targetedcleaner.entity

interface ProcessRepository {
    suspend fun getAllRunnablePackages(): List<String>
    suspend fun killProcess(packageName: String)
}
