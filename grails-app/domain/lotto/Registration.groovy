package lotto

class Registration {

    Date dateCreated

    static belongsTo = [event:Event, attendee:User]

    static constraints = {
        attendee()
        event()
        dateCreated()
    }

    String toString(){
        return "${event} : ${attendee}"
    }
}
