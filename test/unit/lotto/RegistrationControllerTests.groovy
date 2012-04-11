package lotto

import grails.buildtestdata.mixin.Build
import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RegistrationController)
@Build([Registration, User])
@Mock(SpringSecurityService)
class RegistrationControllerTests {

    void testIndex() {
        controller.index()
        assert "/registration/list" == response.redirectedUrl
    }

    void testList() {
        def user1 = User.build().save()
        def user2 = User.build().save()
        (1..5).collect { Registration.build(attendee: user1).save() }
        (1..5).collect { Registration.build(attendee: user2).save() }

        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> false }

        def mockSpringSecurityService = mockFor(SpringSecurityService)
        mockSpringSecurityService.demand.getCurrentUser {-> user1 }
        controller.springSecurityService = mockSpringSecurityService.createMock()

        def model = controller.list()

        assert model.registrationInstanceList.size() == 5
        assert model.registrationInstanceTotal == 5
        mockSpringSecurityService.verify()

    }

    void testAdminList() {
        def user1 = User.build().save()
        def user2 = User.build().save()
        (1..6).collect { Registration.build(attendee: user1).save() }
        (1..6).collect { Registration.build(attendee: user2).save() }

        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> true }

        def mockSpringSecurityService = mockFor(SpringSecurityService)
        mockSpringSecurityService.demand.getCurrentUser(0) {-> user1 }
        controller.springSecurityService = mockSpringSecurityService.createMock()

        def model = controller.list()

        assert model.registrationInstanceList.size() == 10
        assert model.registrationInstanceTotal == 12
        mockSpringSecurityService.verify()
    }
}
