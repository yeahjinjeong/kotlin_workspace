package com.example.kotlin_workspace.grammar

fun main() {
    val ticketA = TicketA("Asiana", "Saekdong", "2024-03-11", 6)
    val ticketB = TicketB("Asiana", "Saekdong", "2024-03-11", 6)

    println(ticketA) // Ticket(companyName=Asiana, name=Saekdong, date=2024-03-11, seatNumber=6)
    println(ticketB) // com.example.practice.TicketB@5010be6
    println(ticketB.companyName)
}

/**
 * toString(), hashCode(), equals(), copy() 등의 메서드를 컴파일러가 자동으로 생성해줌
 */
data class TicketA(val companyName: String, val name: String, var date: String, var seatNumber: Int) {
}

class TicketB(val companyName: String, val name: String, var date: String, var seatNumber: Int) {
}