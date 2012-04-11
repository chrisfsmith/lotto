package lotto

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class LotteryControllerIntegrationTests extends GroovyTestCase {

    void testIndex() {
        def controller = new LotteryController()
        controller.index()
        assertEquals "/lottery/list", controller.response.redirectedUrl
    }

    void testList() {
        def controller = new LotteryController()
        def user = User.build().save(flush: true)

        (1..5).collect { Lottery.build(users: [user]).save(flush: true) }
        (1..2).collect { Lottery.build(users: [user], completed: true).save(flush: true) }
        (1..2).collect { Lottery.build().save(flush: true) }
        (1..3).collect { Lottery.build(completed: true).save(flush: true) }

        SpringSecurityUtils.doWithAuth(user.username, {
            controller.params.max = 2
            controller.params.offset = 2
            def model = controller.list()

            assert model.lotteryInstanceList.size() == 2
            assert model.lotteryInstanceTotal == 5
        })

        def role = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        UserRole.create user, role, true

        SpringSecurityUtils.doWithAuth(user.username, {
            controller.params.clear()
            def model = controller.list()

            assert model.lotteryInstanceList.size() == 10
            assert model.lotteryInstanceTotal == 12
        })
    }
}
