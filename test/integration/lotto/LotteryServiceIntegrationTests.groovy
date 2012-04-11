package lotto

class LotteryServiceIntegrationTests extends GroovyTestCase {

    def lotteryService

    void testFindLotteriesWithAllHavingTheSameUser() {
        def user = User.build()
        (1..5).collect { Lottery.build(users: [user]) }
        user.save(flush: true)

        def results = lotteryService.findLotteriesByUser(user, [max: 2, offset: 0])

        assert 5 == results.totalCount
        assert 2 == results.size()
    }
}
