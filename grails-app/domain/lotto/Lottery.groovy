package lotto

class Lottery {

    String name
    Boolean completed
    List users
    Integer pickIndex
    Date dateCreated
    Date lastUpdated

    static hasMany = [events: Event, users: User]

    static constraints = {
        name blank: false, maxSize: 50
        events()
        users()
        completed()
        pickIndex nullable: true, min: 0
        dateCreated()
        lastUpdated()
    }

    static mapping = {
        sort "dateCreated"
    }

    String toString() {
        "${name}" + (completed ? "(completed)" : "")
    }

    User getPicker() {
        def picker
        if (pickIndex != null && pickIndex < users.size()) {
            picker = users[pickIndex]
        }
        picker
    }

    void incrementPickIndex() {
        if (!completed && pickIndex != null) {
            ++pickIndex
            if (pickIndex >= users.size()) {
                pickIndex = 0;
            }
        }
    }
}
