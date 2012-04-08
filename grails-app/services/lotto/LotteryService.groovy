package lotto

class LotteryService {

    List<User> generatePickOrder(users) {
        log.debug "BEFORE ${users}"
        if (users) {
            Collections.shuffle(users)
        }
        log.debug "AFTER ${users}"
        users
    }
}
