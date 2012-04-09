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

}
