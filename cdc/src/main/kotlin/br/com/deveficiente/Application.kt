package br.com.deveficiente

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.deveficiente")
		.start()
}

