package com.exequieltiglao.covid.utils

import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable?.hasNoInternet(): Boolean {
    if (this == null) return false
    return (this is SocketTimeoutException || this is UnknownHostException)
}