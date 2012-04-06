package lotto

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
    String firstName
    String lastName
    String email

    static hasMany = [registrations:Registration]

	static constraints = {
		username blank: false, unique: true
		password blank: false
        firstName blank:false
        lastName blank:false
        email email:true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

    String toString(){
        "${lastName}, ${firstName} (${email})"
    }

    protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
