package lotto

import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EventService)
@Mock([Event, Lottery])
@Build([Event, Lottery])
class EventServiceTests {

    void testFindEventsWithAllHavingTheSameLottery() {
        def lottery = Lottery.build()
        def event1 = Event.build(lottery: lottery)
        println event1
        event1.save()
        def event2 = Event.build(lottery: lottery)
        println event2
        event2.save()
        def event3 = Event.build(lottery: lottery)
        println event3
        event3.save()

        def results = service.findEventsByLottery(lottery, [max: 2, offset: 0])

        assert 3 == results.totalCount
        assert 2 == results.size()
        assert event1 == results[0]
        assert event2 == results[1]
    }

    void testFindEventsWithMixedLotteries() {
        def event1 = Event.build()
        event1.save()
        def event2 = Event.build()
        event2.save()

        def results = service.findEventsByLottery(event1.lottery, [max: 10, offset: 0])

        assert 1 == results.totalCount
        assert 1 == results.size()
        assert event1 == results[0]
    }
}
