package per.nullist.targetedcleaner.entity

class ProcessKiller(
    private val processRepository: ProcessRepository,
    private val packageRepository: PackageRepository
) {
    fun killAll() {
        val runningProcessPackages = processRepository.allProcessPackages
        val safeAppPackages = packageRepository.safeAppPackages
        val unsafeProcessPackages = runningProcessPackages.filter { it !in safeAppPackages }

        unsafeProcessPackages.forEach { processRepository.killProcess(it) }
    }
}