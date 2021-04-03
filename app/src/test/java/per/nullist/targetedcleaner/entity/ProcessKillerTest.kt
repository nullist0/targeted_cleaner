package per.nullist.targetedcleaner.entity

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

@ExperimentalCoroutinesApi
class ProcessKillerTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var killerTest: ProcessKiller

    private lateinit var processRepository: ProcessRepository
    private lateinit var packageRepository: PackageRepository

    @Before
    fun setUp() {
        processRepository = mock(ProcessRepository::class.java)
        packageRepository = mock(PackageRepository::class.java)
        killerTest = ProcessKiller(processRepository, packageRepository)
    }

    @Test
    fun testKillCorrectly() = runBlockingTest {
        // given
        val beforeProcesses = listOf("1", "2", "3")
        val allSafeProcesses = setOf("2")

        given(processRepository.getAllProcessPackages()).willReturn(beforeProcesses)
        given(packageRepository.getSafeAppPackages()).willReturn(allSafeProcesses)

        // when
        killerTest.killAll()

        // then
        val killedProcesses = listOf("1", "3")
        for (process in killedProcesses) {
            verify(processRepository).killProcess(process)
        }
    }


    @Test
    fun testDoNotKillCorrectly() = runBlockingTest {
        // given
        val beforeProcesses = listOf("1", "2", "3")
        val allSafeProcesses = setOf("2", "4")

        given(processRepository.getAllProcessPackages()).willReturn(beforeProcesses)
        given(packageRepository.getSafeAppPackages()).willReturn(allSafeProcesses)

        // when
        killerTest.killAll()

        // then
        val notKilledProcesses = listOf("2", "4")
        for (process in notKilledProcesses) {
            verify(processRepository, never()).killProcess(process)
        }
    }
}