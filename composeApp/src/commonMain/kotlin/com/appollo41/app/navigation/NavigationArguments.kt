package com.appollo41.app.navigation

import androidx.lifecycle.SavedStateHandle

const val CUSTOMER_ID = "customerId"
inline val SavedStateHandle.customerId: Long? get() = get(CUSTOMER_ID)
inline val SavedStateHandle.customerIdOrThrow: Long get() = get(CUSTOMER_ID)
    ?: throw IllegalStateException("$CUSTOMER_ID is mandatory and can not be null")
