package lotto

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(FooterTagLib)
class FooterTagLibTests {

    void testThisYear() {
        def todayYear = new Date().getAt(Calendar.YEAR)
        assert applyTemplate('<lotto:thisYear/>') == "${todayYear}"
    }

    void testCopyright() {
        assert applyTemplate('<lotto:copyright startYear="2001">Copyright Test</lotto:copyright>') == '&copy; 2001 - 2012 Copyright Test'
    }
}
