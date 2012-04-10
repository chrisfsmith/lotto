package lotto

import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(LoginTagLib)
class LoginTagLibTests {

    void testLoginControl() {
        tagLib.springSecurityService = setUpMockSpringSecurityService(true)
        assert applyTemplate('<lotto:loginControl/>') == 'Jon Doe [<a href="/logout">Logout</a>]'

        tagLib.springSecurityService = setUpMockSpringSecurityService(false)
        assert applyTemplate('<lotto:loginControl/>') == '[<a href="/login">Login</a>]'
    }

    void testIsAdmin() {
        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> return false }
        assert applyTemplate('<lotto:isAdmin>Secure Content</lotto:isAdmin>') == ''

        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> return true }
        assert applyTemplate('<lotto:isAdmin>Secure Content</lotto:isAdmin>') == 'Secure Content'
    }

    void testIsNotAdmin() {
        SpringSecurityUtils.metaClass.'static'.ifNotGranted = { String role -> return false }
        assert applyTemplate('<lotto:isNotAdmin>Boring Content</lotto:isNotAdmin>') == ''

        SpringSecurityUtils.metaClass.'static'.ifNotGranted = { String role -> return true }
        assert applyTemplate('<lotto:isNotAdmin>Boring Content</lotto:isNotAdmin>') == 'Boring Content'
    }

    def setUpMockSpringSecurityService = { boolean loggedIn ->
        def user = new User(firstName: "Jon", lastName: "Doe")
        def springSecurityService = new Object()
        springSecurityService.metaClass.isLoggedIn {-> return loggedIn }
        springSecurityService.metaClass.getProperty = { propertyName ->
            if (propertyName == 'currentUser') { user }
            else { delegate }
        }

        springSecurityService
    }
}
