package lotto

class Event {

    String name
    String description
    Date startDate
    BigDecimal cost = 0
    Integer maxAttendees = 1

    static hasMany = [registrations: Registration]
    static belongsTo = [lottery: Lottery]

    static constraints = {
        name blank: false, maxSize: 50
        description blank: false, maxSize: 250
        startDate validator: {return (it > new Date())}
        cost min: 0.0, max: 1000.0, scale: 2
        maxAttendees min: 1, max: 1000
    }

    static mapping = {
        sort "startDate"
    }

    static searchable = true

    String toString() {
        "${name} (${startDate.format('MM/dd/yyyy HH:mm')})"
    }

    boolean isFull() {
        registrations?.size() >= maxAttendees
    }
}
