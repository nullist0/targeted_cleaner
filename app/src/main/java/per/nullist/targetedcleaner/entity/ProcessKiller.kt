package per.nullist.targetedcleaner.entity

class ProcessKiller(
    private val processRepository: ProcessRepository,
    private val packageRepository: PackageRepository
) {
    fun killAll() {
        val runningProcesses = processRepository.readAllProcessPackages()
        val notToKillProcesses = packageRepository.readAllPackagesNotToKill()
        val willKilledProcesses = runningProcesses.filter { it !in notToKillProcesses }

        willKilledProcesses.forEach { processRepository.killProcess(it) }
    }
}