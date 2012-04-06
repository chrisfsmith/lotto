import lotto.User
import lotto.UserRole
import lotto.Role
import grails.util.GrailsUtil
import lotto.Event
import lotto.Registration

class BootStrap {

    def init = { servletContext ->
        switch (GrailsUtil.environment) {
            case "development":

                def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
                def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

                def adminUser = new User(username: 'me', enabled: true, password: 'password',
                        firstName: 'Mini', lastName: 'Me', email: 'mini@me.com')
                adminUser.save(flush: true)
                def user = new User(username: 'you', enabled: true, password: 'password',
                        firstName: 'Jon', lastName: 'Doe', email: 'jon@doe.com')
                user.save(flush: true)

                UserRole.create adminUser, adminRole, true
                UserRole.create user, userRole, true

                assert User.count() == 2
                assert Role.count() == 2
                assert UserRole.count() == 2

                def event1 = new Event(name: 'Game 1', description: 'Twins vs. Angels', startDate: (new Date() + 20),
                        cost: 60.0, maxAttendees: 4)
                event1.save()
                def event2 = new Event(name: 'Game 2', description: 'Twins vs. Rangers', startDate: (new Date() + 30),
                        cost: 60.0, maxAttendees: 4)
                event2.save()
                def event3 = new Event(name: 'Game 3', description: 'Twins vs. Rangers', startDate: (new Date() + 31),
                        cost: 60.0, maxAttendees: 8)
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