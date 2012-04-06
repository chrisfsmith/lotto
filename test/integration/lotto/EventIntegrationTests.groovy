package lotto

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 4/6/12
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
class EventIntegrationTests extends GroovyTestCase {

    void testEventDatesBeforeToday() {
        def lastWeek = new Date() - 7
        def event = new Event(startDate: lastWeek)
        assertFalse "Validation should not succeed", event.validate()
        // It should have errors after validation fails
        assertTrue "There should be errors", event.hasErrors()
        println "\nErrors:"
        println event.errors ?: "no errors found"
        def badField = event.errors.getFieldError('startDate')
        println "\nBadField:"
        println badField ?: "startDate wasn't a bad field"
        assertNotNull "Expecting to find an error on the startDate field", badField
        def code = badField?.codes.find {
            it == 'event.startDate.validator.invalid'
        }
        println "\nCode:"
        println code ?: "the custom validator for startDate wasn't found"
        assertNotNull "startDate field should be the culprit", code
    }
}
