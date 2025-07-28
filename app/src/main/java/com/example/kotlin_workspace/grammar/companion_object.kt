package com.example.kotlin_workspace.grammar

fun main() {
    val bookFactory = Book
    println(bookFactory)
    println(Book.myBook)
    println(Book.myBook)
    println(Book.getId())
    val book = Book.create() // Book.BookFactory.create()
    println("${book.id}. ${book.name}")
}

// 직접 생성 불가능
class Book private constructor(val id: Int, val name: String){
    // 내부에 선언된 특별한 객체
    // 정적인 메서드나 변수를 선언하고 객체를 생성하는 기능을 구현할 수 있다
    companion object BookFactory : IdProvider {
        val myBook = "Harry Potter and the Philosopher's Stone"

        override fun getId(): Int {
            return 3
        }

        fun create() = Book(getId(), myBook)
    }
    // val book = Book.create() 오류
//    fun create() = Book(1, "Harry Potter and the Philosopher's Stone")
}

/**
 * 자바에서는 어떻게 구현했을까?
 *
 * @Builder
 * class Book {
 *  private Int id;
 *  private String name;
 *
 *  public Book create() {
 *      return Book.builder().id().name().build();
 *  }
 * }
 */

interface IdProvider {
    fun getId() : Int
}