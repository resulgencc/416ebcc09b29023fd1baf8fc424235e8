package com.teknasyon.satellitetracker.util.filter

data class CachedFilterItem<T : FilterItem>(
    val key: String,
    val items: List<T>
)