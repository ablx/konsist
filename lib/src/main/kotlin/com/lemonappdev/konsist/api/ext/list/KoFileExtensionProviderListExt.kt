package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider

/**
 * List containing files with extension.
 *
 * @param extension The extension to include.
 * @param extensions The extensions to include.
 * @return A list containing files with extensions matching the specified extensions.
 */
fun <T : KoFileExtensionProvider> List<T>.withExtension(extension: String, vararg extensions: String): List<T> = filter {
    it.hasExtension(extension) || extensions.any { extension -> it.hasExtension(extension) }
}

/**
 * List containing files without extension.
 *
 * @param extension The extension to exclude.
 * @param extensions The extensions to exclude.
 * @return A list containing files without extensions matching the specified extensions.
 */
fun <T : KoFileExtensionProvider> List<T>.withoutExtension(extension: String, vararg extensions: String): List<T> = filter {
    !it.hasExtension(extension) && extensions.none { extension -> it.hasExtension(extension) }
}
