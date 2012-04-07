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

                def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
                def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

                def adminUser = new User(username: 'admin', enabled: true, password: 'password',
                        firstName: 'Bob', lastName: 'Doe', email: 'bob@doe.com')
                adminUser.save(flush: true)
                def user = new User(username: 'jdoe', enabled: true, password: 'password',
                        firstName: 'Jon', lastName: 'Doe', email: 'jon@doe.com')
                user.save(flush: true)

                UserRole.create adminUser, adminRole, true
                UserRole.create user, userRole, true

                assert User.count() == 2
                assert Role.count() == 2
                assert UserRole.count() == 2

                def lottery = new Lottery(name: '2012 Twins Season', completed: false)
                lottery.save()

                def event1 = new Event(name: 'Game 1', description: 'Twins vs. Angels', startDate: (new Date() + 20),
                        cost: 60.0, maxAttendees: 4, lottery: lottery)
                event1.save()
                def event2 = new Event(name: 'Game 2', description: 'Twins vs. Rangers', startDate: (new Date() + 30),
                        cost: 60.0, maxAttendees: 4, lottery: lottery)
                event2.save()
                def event3 = new Event(name: 'Game 3', description: 'Twins vs. Rangers', startDate: (new Date() + 31),
                        cost: 60.0, maxAttendees: 8, lottery: lottery)
                event3.save()

                def reg1 = new Registration(attendee: user, event: event1)
                reg1.save()

                break

            case "production" : break
        }

        def destroy = {
        }
    }
}