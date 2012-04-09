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
                def user3
                def user4
                def user5
                if (User.count == 0) {
                    adminUser = new User(username: 'admin', enabled: true, password: 'password',
                            firstName: 'Super', lastName: 'Doe', email: 'super@doe.com')
                    adminUser.save(flush: true)
                    user1 = new User(username: 'user1', enabled: true, password: 'password',
                            firstName: 'Anna', lastName: 'Doe', email: 'anna@doe.com')
                    user1.save(flush: true)
                    user2 = new User(username: 'user2', enabled: true, password: 'password',
                            firstName: 'Bob', lastName: 'Doe', email: 'bob@doe.com')
                    user2.save(flush: true)
                    user3 = new User(username: 'user3', enabled: true, password: 'password',
                            firstName: 'Charlie', lastName: 'Doe', email: 'charlie@doe.com')
                    user3.save(flush: true)
                    user4 = new User(username: 'user4', enabled: true, password: 'password',
                            firstName: 'Darren', lastName: 'Doe', email: 'darren@doe.com')
                    user4.save(flush: true)
                    user5 = new User(username: 'user5', enabled: true, password: 'password',
                            firstName: 'Ericia', lastName: 'Doe', email: 'ericia@doe.com')
                    user5.save(flush: true)
                }

                if (UserRole.count == 0) {
                    UserRole.create adminUser, adminRole, true
                    UserRole.create user1, userRole, true
                    UserRole.create user2, userRole, true
                    UserRole.create user3, userRole, true
                    UserRole.create user4, userRole, true
                    UserRole.create user5, userRole, true
                }

                assert User.count() == 6
                assert Role.count() == 2
                assert UserRole.count() == 6

                def lottery1
                def lottery2
                if (Lottery.count == 0) {
                    lottery1 = new Lottery(name: '2011 Wild Season', completed: true)
                    lottery1.save()
                    lottery2 = new Lottery(name: '2012 Twins Season', completed: false, users: [user1, user5, user4, user2], pickIndex: 1)
                    lottery2.save()
                }

                def event1
                def event2
                def event3
                def event4
                if (Event.count == 0) {
                    event1 = new Event(name: 'Game 1', description: 'Twins vs. Angels', startDate: (new Date() + 20),
                            cost: 60.0, maxAttendees: 4, lottery: lottery2)
                    event1.save()
                    event2 = new Event(name: 'Game 2', description: 'Twins vs. Rangers', startDate: (new Date() + 30),
                            cost: 60.0, maxAttendees: 4, lottery: lottery2)
                    event2.save()
                    event3 = new Event(name: 'Game 3', description: 'Twins vs. Rangers', startDate: (new Date() + 31),
                            cost: 60.0, maxAttendees: 8, lottery: lottery2)
                    event3.save()
                    event4 = new Event(name: 'Game 1', description: 'Wild vs. Ducks', startDate: (new Date() + 1),
                            cost: 40.0, maxAttendees: 2, lottery: lottery1)
                    event4.save()
                }

                if (Registration.count == 0) {
                    def reg1 = new Registration(attendee: user1, event: event1)
                    reg1.save()
                    def reg2 = new Registration(attendee: user2, event: event4)
                    reg2.save()
                    def reg3 = new Registration(attendee: user2, event: event4)
                    reg3.save()
                }

                break

            case "production" : break
        }

        def destroy = {
        }
    }
}