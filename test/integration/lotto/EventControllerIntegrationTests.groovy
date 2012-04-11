package lotto

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class EventControllerIntegrationTests extends GroovyTestCase {

    void testIndex() {
        def controller = new EventController()
        controller.index()
        assertEquals "/event/list", controller.response.redirectedUrl
    }

    void testList() {
        def controller = new EventController()
        def user = User.build().save(flush: true)

        def lottery1 = Lottery.build().save(flush: true)
        (1..5).collect { Event.build(lottery: lottery1).save(flush: true) }
        def lottery2 = Lottery.build().save(flush: true)
        (1..5).collect { Event.build(lottery: lottery2).save(flush: true) }

        SpringSecurityUtils.doWithAuth(user.username, {
            controller.params.lottery = lottery1.id
            controller.params.max = 2
            controller.params.offset = 2
            def model = controller.list()

            assert model.eventInstanceList.size() == 2
            assert model.eventInstanceTotal == 5
        })

        def role = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        UserRole.create user, role, true

        SpringSecurityUtils.doWithAuth(user.username, {
            controller.params.clear()
            def model = controller.list()

            assert model.eventInstanceList.size() == 10
            assert model.eventInstanceTotal == 10
        })
    }
}
