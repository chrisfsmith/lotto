package lotto



import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Lottery)
@Build([Lottery, User])
class LotteryTests {


    void testNullConstraints() {
        mockForConstraintsTests(Lottery)

        def lottery = new Lottery()

        assertFalse lottery.validate()
        assertEquals "nullable", lottery.errors["name"]
        assertEquals "nullable", lottery.errors["completed"]
    }

    void testSimpleConstraints() {
        mockForConstraintsTests(Lottery)

        def lottery = new Lottery(name: '', completed: true, pickIndex: -1)

        assertFalse lottery.validate()
        assertEquals "blank", lottery.errors["name"]
        assertEquals "min", lottery.errors["pickIndex"]
    }

    void testGetPicker() {
        def lottery = Lottery.build()

        // pickIndex null
        assertNull lottery.getPicker()

        def user = User.build()
        lottery.users = [user]
        lottery.pickIndex = 0

        assertNotNull lottery.getPicker()

        // pickIndex too high
        lottery.pickIndex = 1
        assertNull lottery.getPicker()
    }

    void testIncrementPickIndex() {
        def lottery = Lottery.build(users: [User.build(), User.build()])

        lottery.incrementPickIndex()
        assertNull lottery.pickIndex

        // completed lotto with pick
        lottery.completed = true
        lottery.pickIndex = 0
        lottery.incrementPickIndex()
        assert lottery.pickIndex == 0

        // back to in progress
        lottery.completed = false
        lottery.incrementPickIndex()
        assert lottery.pickIndex == 1

        // roll around
        lottery.incrementPickIndex()
        assert lottery.pickIndex == 0
    }
}
