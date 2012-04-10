import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(WelcomeController)
class WelcomeControllerTests {

    void testIndexNotLoggedIn() {
        controller.springSecurityService = setUpMockSpringSecurityService(false)

        controller.index()
        assert "/welcome" == response.redirectedUrl
    }

    void testIndexLoggedIn() {
        controller.springSecurityService = setUpMockSpringSecurityService(true)

        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> true }
        controller.index()
        assert "/admin" == response.redirectedUrl

        response.reset()

        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> false }
        controller.index()
        assert "/lottery/list" == response.redirectedUrl
    }

    def setUpMockSpringSecurityService = { boolean loggedIn ->
        def springSecurityService = new Object()
        springSecurityService.metaClass.isLoggedIn {-> return loggedIn }

        springSecurityService
    }

}
