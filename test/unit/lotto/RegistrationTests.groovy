package lotto



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Registration)
class RegistrationTests {

    void testNullConstraints() {
        mockForConstraintsTests(Registration)

        def registration = new Registration()

        assertFalse registration.validate()
        assertEquals "nullable", registration.errors["event"]
        assertEquals "nullable", registration.errors["attendee"]
    }
}
