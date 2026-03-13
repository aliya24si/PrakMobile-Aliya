package com.example.aliya_apps.pertemuan_2

fun main() {
    println("Hai rekan rekan...")
    println("Selamat datang di bahasa pemrograman Kotlin")

    println("=================")

    var angka = 15
    println("Hasil dari 15 = 10 = ${angka + 10}")

    val nilaiInt = 10000
    val nilaiDouble = 100.003
    val nilaiFloat = 1000.0f

    println("Nilai Integer = $nilaiInt")
    println("Nilai Double = $nilaiDouble")
    println("Nilai Float = $nilaiFloat")

    println("=========STRING=========")
    val huruf = 'a'
    println("Ini penggunaan karater '$huruf'")

    val nilaiString = "Mawar"
    println("Halo $nilaiString!\nApa kabar?")

    val nilai = 10
    if(nilai<0)
        println("Bilanganb negatif")
    else {
        if(nilai%2 == 0)
            println("Bilangan Genap")
        else
            println("Bilangan Ganjil")
    }

    println("========= Perulangan =========")
    val kampusKu: Array<String> = arrayOf("Kampus", "Politeknik", "Caltex", "Riau")
    for (kampus: String in kampusKu) {
        println(kampus)
    }
}