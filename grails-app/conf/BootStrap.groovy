import lotto.User
import lotto.UserRole
import lotto.Role
import grails.util.GrailsUtil
import lotto.Event
import lotto.Registration
import lotto.Lottery

class BootStrap {

    def init = { servletContext ->
        switch (GrailsUtil.environment) {
            case "development":

                def adminRole
                def userRole
                if (Role.count == 0) {
                    adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
                    userRole = new Role(authority: 'ROLE_USER').save(flush: true)
                }

                def adminUser
                def user1
                def user2
                if (User.count == 0) {
                    adminUser = new User(username: 'admin', enabled: true, password: 'password',
                            firstName: 'Bob', lastName: 'Doe', email: 'bob@doe.com')
                    adminUser.save(flush: true)
                    user1 = new User(username: 'jdoe', enabled: true, password: 'password',
                            firstName: 'Jon', lastName: 'Doe', email: 'jon@doe.com')
                    user1.save(flush: true)
                    user2 = new User(username: 'adoe', enabled: true, password: 'password',
                            firstName: 'Andy', lastName: 'Doe', email: 'andy@doe.com')
                    user2.save(flush: true)
                }

                if (UserRole.count == 0) {
                    UserRole.create adminUser, adminRole, true
                    UserRole.create user1, userRole, true
                    UserRole.create user2, userRole, true
                }

                assert User.count() == 3
                assert Role.count() == 2
                assert UserRole.count() == 3

                def lottery
                if (Lottery.count == 0) {
                    lottery = new Lottery(name: '2012 Twins Season', completed: false)
                    lottery.save()
                }

                def event1
                def event2
                def event3
                if (Event.count == 0) {
                    event1 = new Event(name: 'Game 1', description: 'Twins vs. Angels', startDate: (new Date() + 20),
                            cost: 60.0, maxAttendees: 4, lottery: lottery)
                    event1.save()
                    event2 = new Event(name: 'Game 2', description: 'Twins vs. Rangers', startDate: (new Date() + 30),
                            cost: 60.0, maxAttendees: 4, lottery: lottery)
                    event2.save()
                    event3 = new Event(name: 'Game 3', description: 'Twins vs. Rangers', startDate: (new Date() + 31),
                            cost: 60.0, maxAttendees: 8, lottery: lottery)
                    event3.save()
                }

                if (Registration.count == 0) {
                    def reg1 = new Registration(attendee: user1, event: event1)
                    reg1.save()
                }

                break

            case "production" : break
        }

        def destroy = {
        }
    }
}