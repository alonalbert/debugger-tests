package com.example.debuggertests

import android.content.Context
import dalvik.system.BaseDexClassLoader
import dalvik.system.DexFile
import java.lang.reflect.Field
import java.util.Locale

internal fun Context.getTestCases(): List<String> {
  // Here we do some reflection to access the dex files from the class loader. These implementation details vary by platform version,
  // so we have to be a little careful, but not a huge deal since this is just for testing. It should work on 21+.
  // The source for reference is at:
  // https://android.googlesource.com/platform/libcore/+/oreo-release/dalvik/src/main/java/dalvik/system/BaseDexClassLoader.java
  val classLoader = classLoader as BaseDexClassLoader
  val pathListField = field("dalvik.system.BaseDexClassLoader", "pathList")
  val pathList = pathListField.get(classLoader) // Type is DexPathList

  val dexElementsField = field("dalvik.system.DexPathList", "dexElements")

  @Suppress("UNCHECKED_CAST")
  val dexElements = dexElementsField.get(pathList) as Array<Any> // Type is Array<DexPathList.Element>

  val dexFileField = field("dalvik.system.DexPathList\$Element", "dexFile")

  @Suppress("DEPRECATION")
  return dexElements.map {
    dexFileField.get(it) as DexFile
  }.flatMap {
    it.entries().asSequence()
  }.filter {
    val split = it.split(".")
    split.size == 2 && "${split[0].capitalize()}Kt" == split[1]
  }
}

private fun field(className: String, fieldName: String): Field {
  val clazz = Class.forName(className)
  val field = clazz.getDeclaredField(fieldName)
  field.isAccessible = true
  return field
}

private fun String.capitalize()  = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
