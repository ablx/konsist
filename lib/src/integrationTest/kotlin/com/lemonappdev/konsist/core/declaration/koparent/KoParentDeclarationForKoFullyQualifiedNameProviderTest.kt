package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `parent-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("parent-fully-qualified-name")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
    }

    @Test
    fun `parent-fully-qualified-name-without-import`() {
        // given
        val sut = getSnippetFile("parent-fully-qualified-name-without-import")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo ""
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkofullyqualifiednameprovider/", fileName)
}