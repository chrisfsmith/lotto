package lotto

import grails.test.GroovyPagesTestCase

class FooterTagLibIntegrationTests extends GroovyPagesTestCase {

    void testThisYear() {
        def template = '<lotto:thisYear/>'
        def result = applyTemplate(template)

        assertEquals "${new Date().getAt(Calendar.YEAR)}", result
    }

    void testCopyright() {
        def template = '<lotto:copyright startYear="1999">Copyright Test</lotto:copyright>'
        def result = applyTemplate(template)

        assertEquals '&copy; 1999 - 2012 Copyright Test', result
    }
}
