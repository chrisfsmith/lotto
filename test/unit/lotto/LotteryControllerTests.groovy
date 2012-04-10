package lotto

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@TestFor(LotteryController)
@Mock([Lottery, LotteryService, SpringSecurityService, Event])
class LotteryControllerTests {


    def populateValidParams(params) {
        assert params != null
        params.name = 'name'
        params.completed = false
        params.users = [new User()]
    }

    void testIndex() {
        controller.index()
        assert "/lottery/list" == response.redirectedUrl
    }

    void testList() {
        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> false }

        def mockLotteryService = mockFor(LotteryService)
        mockLotteryService.demand.findLotteriesByUser {user, params -> createMockPagedResultList() }
        controller.lotteryService = mockLotteryService.createMock()

        def model = controller.list()

        assert model.lotteryInstanceList.size() == 0
        assert model.lotteryInstanceTotal == 0
        mockLotteryService.verify()
    }

    void testAdminList() {
        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role -> true }

        def mockLotteryService = mockFor(LotteryService)
        mockLotteryService.demand.findLotteriesByUser(0) {lottery, params -> createMockPagedResultList() }
        controller.lotteryService = mockLotteryService.createMock()

        def model = controller.list()

        assert model.lotteryInstanceList.size() == 0
        assert model.lotteryInstanceTotal == 0
        mockLotteryService.verify()
    }

    void testCreate() {
        def model = controller.create()

        assert model.lotteryInstance != null
    }

    void testSave() {
        def mockLotteryService = mockFor(LotteryService)
        mockLotteryService.demand.setupPickOrder(2) {lottery -> }
        controller.lotteryService = mockLotteryService.createMock()

        controller.save()

        assert model.lotteryInstance != null
        assert view == '/lottery/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lottery/show/1'
        assert controller.flash.message != null
        assert Lottery.count() == 1
        mockLotteryService.verify()

    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lottery/list'


        populateValidParams(params)
        def lottery = new Lottery(params)

        assert lottery.save() != null

        params.id = lottery.id

        def model = controller.show()

        assert model.lotteryInstance == lottery
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lottery/list'


        populateValidParams(params)
        def lottery = new Lottery(params)

        assert lottery.save() != null

        params.id = lottery.id

        def model = controller.edit()

        assert model.lotteryInstance == lottery
    }

    void testUpdate() {
        def mockLotteryService = mockFor(LotteryService)
        mockLotteryService.demand.setupPickOrder(2) {lottery -> }
        controller.lotteryService = mockLotteryService.createMock()

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lottery/list'

        response.reset()


        populateValidParams(params)
        def lottery = new Lottery(params)

        assert lottery.save() != null

        // test invalid parameters in update
        params.id = lottery.id
        params.name = ''

        controller.update()

        assert view == "/lottery/edit"
        assert model.lotteryInstance != null

        lottery.clearErrors()

        populateValidParams(params)
        params.users = []
        assert lottery.users != null
        controller.update()

        assert lottery.users == null
        assert response.redirectedUrl == "/lottery/show/$lottery.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lottery.clearErrors()

        populateValidParams(params)
        params.id = lottery.id
        params.version = -1
        controller.update()

        assert view == "/lottery/edit"
        assert model.lotteryInstance != null
        assert model.lotteryInstance.errors.getFieldError('version')
        assert flash.message != null
        mockLotteryService.verify()
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lottery/list'

        response.reset()

        populateValidParams(params)
        def lottery = new Lottery(params)

        assert lottery.save() != null
        assert Lottery.count() == 1

        params.id = lottery.id

        controller.delete()

        assert Lottery.count() == 0
        assert Lottery.get(lottery.id) == null
        assert response.redirectedUrl == '/lottery/list'
    }

    void testPick() {
        def mockLotteryService = mockFor(LotteryService)

        mockLotteryService.demand.pick {lottery, user, event -> false }
        controller.lotteryService = mockLotteryService.createMock()
        params.lottery = '1'
        params.event = '1'
        controller.pick()
        assert flash.message == 'default.not.picked.message'
        assert response.redirectedUrl == '/event/list?lottery=1&event=1'
        mockLotteryService.verify()

        response.reset()

        mockLotteryService.demand.pick {lottery, user, event -> true }
        controller.pick()
        assert flash.message == 'default.picked.message'
        assert response.redirectedUrl == '/event/list?lottery=1&event=1'
        mockLotteryService.verify()
    }

    def createMockPagedResultList = {
        def list = new ArrayList()
        list.metaClass.getProperty = { propertyName ->
            if (propertyName == 'totalCount') { 0 }
            else { delegate }
        }

        list
    }
}
