package com.lemonappdev.konsist.architecture4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture4Test {
    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture4.project.data..")
        val koArchitecture = Konsist
            .architecture(domain, presentation, data)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
        val scope = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture4/project")

        // then
        assert(koArchitecture, scope)
    }

    @Test
    fun `fails when bad dependency is set`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture4.project.data..")
        val koArchitecture = Konsist
            .architecture(domain, presentation, data)
            .addDependencies {
                data.notDependOnAnyLayer()
                presentation.dependsOn(data)
                domain.dependsOn(data)
            }
        val scope = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture4/project")
        val sut = { assert(koArchitecture, scope) }

        // then
        sut shouldThrow KoCheckFailedException::class
    }
}
