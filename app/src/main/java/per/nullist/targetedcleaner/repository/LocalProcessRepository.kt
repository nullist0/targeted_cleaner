package per.nullist.targetedcleaner.repository

import per.nullist.targetedcleaner.entity.ProcessRepository

class LocalProcessRepository: ProcessRepository {
    override fun readAllProcessPackages(): List<String> {
        TODO("Not yet implemented")
    }

    override fun killProcess(packageName: String) {
        TODO("Not yet implemented")
    }
}
