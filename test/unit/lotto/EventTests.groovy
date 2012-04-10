package lotto

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Event)
class EventTests {

    void testNullConstraints() {
        mockForConstraintsTests(Event)

        def event = new Event()

        assertFalse event.validate()

        assertEquals "nullable", event.errors["name"]
        assertEquals "nullable", event.errors["description"]
        assertEquals "nullable", event.errors["startDate"]
        assertEquals "nullable", event.errors["lottery"]
    }

    void testSimpleConstraints() {
        mockForConstraintsTests(Event)

        def event = new Event(name: '', description: '', startDate: new Date() - 1, cost: Integer.MAX_VALUE,
                maxAttendees: 0, lottery: new Lottery())

        assertFalse event.validate()

        assertEquals "blank", event.errors["name"]
        assertEquals "blank", event.errors["description"]
        assertEquals "validator", event.errors["startDate"]
        assertEquals "max", event.errors["cost"]
        assertEquals "min", event.errors["maxAttendees"]
    }

    void testIsFull() {
        def event = new Event(maxAttendees: 2)
        assertFalse event.isFull()

        event.registrations = [new Registration()]
        assertFalse event.isFull()

        event.registrations.add(new Registration())
        assertTrue event.isFull()

        event.registrations.add(new Registration())
        assertTrue event.isFull()
    }
}
