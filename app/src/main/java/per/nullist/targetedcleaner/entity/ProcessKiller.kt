package per.nullist.targetedcleaner.entity

class ProcessKiller(
    private val processRepository: ProcessRepository,
    private val packageRepository: PackageRepository
) {
    suspend fun killAll() {
        val runningProcessPackages = processRepository.getAllProcessPackages()
        val safeAppPackages = packageRepository.getSafeAppPackages()
        val unsafeProcessPackages = runningProcessPackages.filter { it !in safeAppPackages }

        unsafeProcessPackages.forEach { processRepository.killProcess(it) }
    }
}