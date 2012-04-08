package lotto

class Lottery {

    String name
    Boolean completed
    List users
    Date dateCreated
    Date lastUpdated

    static hasMany = [events: Event, users: User]

    static constraints = {
        name blank: false, maxSize: 50
        events()
        users()
        completed()
        dateCreated()
        lastUpdated()
    }

    String toString() {
        return "${name} (" + (completed ? "completed" : "in progress") + ")"
    }
}
