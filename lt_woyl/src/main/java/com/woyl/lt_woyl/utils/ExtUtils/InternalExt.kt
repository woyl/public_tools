package com.woyl.lt_woyl.utils.ExtUtils

internal const val NO_GETTER: String = "Property does not have a getter"

internal fun noGetter(): Nothing = throw NotImplementedError(NO_GETTER)