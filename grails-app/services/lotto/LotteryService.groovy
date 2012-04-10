package lotto

class LotteryService {

    /**
     * Given a list of Users return a randomized list.
     *
     * @param users the Users to randomize
     * @return a list contains users but in random order
     */
    List<User> generatePickOrder(users) {
        log.debug "BEFORE ${users}"
        if (users) {
            // This could be much more complicated
            Collections.shuffle(users)
        }
        log.debug "AFTER ${users}"
        users
    }

    /**
     * Get the active Lotteries associated with the provided User.
     * Return type is grails.orm.PagedResultList so that the
     * pagination count is returned too, but using def to
     * support unit testing.
     *
     * @param user
     * @param params Pagination parameters such as offset, max etc.
     * @return PagedResultList
     */
    def findLotteriesByUser(user, params) {
        def criteria = Lottery.createCriteria()
        def results = criteria.list(params) {
            users {eq "id", user.id}
            eq "completed", false
            order "dateCreated", "asc"
        }
        results
    }

    /**
     * Generates a pick order for the provided lottery and sets the
     * lottery's pickIndex.
     *
     * @param lottery
     */
    void setupPickOrder(lottery) {
        lottery.users = generatePickOrder(lottery.users)
        if (lottery.users) {
            log.debug "list is not empty"
            lottery.pickIndex = 0
        } else {
            log.debug "list is empty"
            lottery.pickIndex = null
        }
    }

    /**
     * Smarts for a User to pick an Event from a Lottery
     *
     * @param lottery
     * @param user
     * @param event
     * @return true if the pick was successful; false otherwise
     */
    boolean pick(lottery, user, event) {
        log.debug "Picking " + event + " for " + user + " from " + lottery

        if (!lottery || !user || !event) {
            return false
        }

        if (event.lottery == lottery && !event.isFull()) {
            def nextPicker = lottery.getPicker()
            if (user == nextPicker) {
                log.debug "attempt to register"
                def reg = new Registration(attendee: user, event: event)
                reg.save()
                if (reg.hasErrors()) {
                    return false
                } else {
                    lottery.incrementPickIndex()
                }
                return true
            }
        }
        return false
    }
}
