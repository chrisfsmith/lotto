package lotto

class Lottery {

    String name
    Boolean completed
    Date dateCreated

    static hasMany = [events: Event]

    static constraints = {
        name blank: false, maxSize: 50
        events()
        completed()
        dateCreated()
    }

    String toString() {
        return "${name} (" + (completed ? "completed" : "in progress") + ")"
    }
}
