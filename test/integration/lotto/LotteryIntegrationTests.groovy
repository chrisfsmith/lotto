package lotto

class LotteryIntegrationTests extends GroovyTestCase {

    void testQuery() {
        def lotteries = [
                new Lottery(name: "Twins vs. Angels", completed: false),
                new Lottery(name: "Water Polo Expo", completed: true)]
        lotteries*.save(flush: true)

        assertEquals 2, Lottery.list().size()
        assertEquals 1, Lottery.findAllByCompleted(true).size()
    }
}
