package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReceiverTypeProviderListExtTest {
    @Test
    fun `withReceiverType() returns declaration with any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType() } returns true
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReceiverType() returns declarations with one of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val declaration3: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReceiverType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReceiverType() returns declaration without any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType() } returns true
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReceiverType(name) returns declaration without any of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val declaration3: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReceiverType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withReceiverTypeOf(KClass) returns declaration with one of given receiver`() {
        // given
        val typeName = "SampleType1"
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName) } returns true
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReceiverTypeOf(KClass) returns declarations with one of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val declaration3: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReceiverTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReceiverTypeOf(KClass) returns declaration without one of given receiver`() {
        // given
        val typeName = "SampleType1"
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName) } returns true
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReceiverTypeOf(KClass) returns declaration without any of given receivers`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns true
            every { hasReceiverType(typeName2) } returns false
        }
        val declaration2: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns true
        }
        val declaration3: KoReceiverTypeProvider = mockk {
            every { hasReceiverType(typeName1) } returns false
            every { hasReceiverType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReceiverTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}