package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForPrimaryConstructorTest {
    @Test
    fun `primary-constructor`() {
        // given
        val sut = getSut("primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `primary-constructor-without-visibility-modifiers`() {
        // given
        val sut = getSut("primary-constructor-without-visibility-modifiers")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.run {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo false
        }
    }

    @Test
    fun `public-primary-constructor`() {
        // given
        val sut = getSut("public-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.run {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo false
        }
    }

    @Test
    fun `private-primary-constructor`() {
        // given
        val sut = getSut("private-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo false
        }
    }

    @Test
    fun `protected-primary-constructor`() {
        // given
        val sut = getSut("protected-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo true
        }
    }

    @Test
    fun `internal-primary-constructor`() {
        // given
        val sut = getSut("internal-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo true
            isProtected shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-annotation`() {
        // given
        val sut = getSut("primary-constructor-has-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.map { annotation -> annotation.name } shouldBeEqualTo listOf("SampleAnnotation")
            it.hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            it.hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-no-annotation`() {
        // given
        val sut = getSut("primary-constructor-has-no-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.isEmpty() shouldBeEqualTo true
            it.hasAnnotation(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("kodeclaration/snippet/forprimaryconstructor/", fileName)
}