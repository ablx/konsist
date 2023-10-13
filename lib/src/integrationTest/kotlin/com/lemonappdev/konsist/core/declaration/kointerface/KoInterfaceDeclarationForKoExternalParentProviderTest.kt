package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoExternalParentProviderTest {
    @Test
    fun `interface-has-no-external-parent`() {
        // given
        val sut = getSnippetFile("interface-has-no-external-parent")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            externalParents shouldBeEqualTo emptyList()
            numExternalParents shouldBeEqualTo 0
            countExternalParents { it.name == "SampleExternalParent" } shouldBeEqualTo 0
            hasExternalParents() shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalParent" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasExternalParentOf(SampleParentInterface1::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-only-external-parents`() {
        // given
        val sut = getSnippetFile("interface-has-only-external-parents")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            externalParents.map { it.name } shouldBeEqualTo listOf("SampleExternalParent1", "SampleExternalParent2")
            numExternalParents shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalParent1" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternalParent") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalParent1") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalParent1", "OtherInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalParent1") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalParent1", "OtherInterface") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalParent1" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalParent1" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasExternalParentOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-internal-and-external-parents`() {
        // given
        val sut = getSnippetFile("interface-has-internal-and-external-parents")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            externalParents.map { it.name } shouldBeEqualTo listOf("SampleExternalParent1", "SampleExternalParent2")
            numExternalParents shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalParent1" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternalParent") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalParent1") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalParent1", "OtherInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalParent1") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalParent1", "OtherInterface") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalParent1" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalParent1" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasExternalParentOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoexternalparentprovider/", fileName)
}
