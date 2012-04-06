import lotto.User
import lotto.UserRole
import lotto.Role

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new User(username: 'me', enabled: true, password: 'password')
        adminUser.save(flush: true)
        def user = new User(username: 'you', enabled: true, password: 'password')
        user.save(flush: true)

        UserRole.create adminUser, adminRole, true
        UserRole.create user, userRole, true

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2
    }

    def destroy = {
    }
}
