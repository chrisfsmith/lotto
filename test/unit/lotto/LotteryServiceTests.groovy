package lotto

import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(LotteryService)
@Mock([Lottery, User, Event, Registration])
@Build([Lottery, User, Event, Registration])
class LotteryServiceTests {

    void testGeneratePickOrder() {
        def user1 = User.build()
        def user2 = User.build()
        def user3 = User.build()

        def result = service.generatePickOrder([user1, user2, user3])

        assert result.size() == 3
        assert result.contains(user1)
        assert result.contains(user2)
        assert result.contains(user3)
    }

    void testSetupPickOrder() {
        def user1 = User.build()
        def user2 = User.build()
        def lottery = Lottery.build(users: [user1, user2])

        service.setupPickOrder(lottery)
        assert lottery.getPickIndex() != null
        assert lottery.getPickIndex() == 0

        lottery = Lottery.build()

        service.setupPickOrder(lottery)
        assert lottery.getPickIndex() == null
    }

    void testPickWithNullParameters() {
        def user = User.build()
        def lottery = Lottery.build()
        def event = Event.build()

        assertFalse service.pick(lottery, user, null)
        assertFalse service.pick(lottery, null, event)
        assertFalse service.pick(null, user, event)
    }

    void testPick() {
        def user = User.build()
        def lottery = Lottery.build(users: [user], pickIndex: 0)
        def event = Event.build(lottery: lottery)

        assertTrue service.pick(lottery, user, event)
        assertNotNull Registration.findByAttendeeAndEvent(user, event)
    }

    void testPickWithBadPicker() {
        def user = User.build()
        def lottery = Lottery.build(users: [user])
        def event = Event.build(lottery: lottery)

        assertFalse service.pick(lottery, user, event)
    }

    void testPickWithBadLotteryForEvent() {
        def user = User.build()
        def lottery = Lottery.build(users: [user], pickIndex: 0)
        def event = Event.build()

        assertFalse service.pick(lottery, user, event)
    }

    void testPickWithFullEvent() {
        def user = User.build()
        def lottery = Lottery.build(users: [user], pickIndex: 0)
        def reg = Registration.build()
        def event = Event.build(lottery: lottery, maxAttendees: 1, registrations: [reg])

        assertFalse service.pick(lottery, user, event)
    }
}
