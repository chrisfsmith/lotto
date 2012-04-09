package lotto

import grails.orm.PagedResultList

class LotteryService {

    List<User> generatePickOrder(users) {
        log.debug "BEFORE ${users}"
        if (users) {
            // This could be much more complicated
            Collections.shuffle(users)
        }
        log.debug "AFTER ${users}"
        users
    }

    PagedResultList getLotteriesForUser(user, params) {
        def criteria = Lottery.createCriteria()
        def results = criteria.list(params) {
            users {eq "username", user.getUsername()}
            eq "completed", false
            order "dateCreated", "asc"
        }
        results
    }

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

    boolean pick(lottery, user, event) {
        log.debug "Picking " + event + " for " + user + " from " + lottery

        if (!lottery || !user || !event) {
            return false
        }

        if (event.lottery != lottery || event.isFull()) {
            return false
        }

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
        return false
    }
}
