package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * Sequence containing all declarations that have the fully qualified name.
 */
fun Sequence<KoPackageDeclaration>.withQualifiedName(vararg names: String) = filter {
    names.any { name -> it.qualifiedName == name }
}

/**
 * Sequence containing all declarations that don't have the fully qualified name.
 */
fun Sequence<KoPackageDeclaration>.withoutQualifiedName(vararg names: String) = filter {
    names.none { name -> it.qualifiedName == name }
}