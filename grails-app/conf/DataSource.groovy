dataSource {
    pooled = true
//    driverClassName = "org.h2.Driver"
//    username = "sa"
//    password = ""
    driverClassName = "com.mysql.jdbc.Driver"
    username = "grails"
    password = "server"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = 'validate' // one of 'create', 'create-drop', 'update', 'validate', ''
//            url = "jdbc:h2:mem:devDb;MVCC=TRUE"
            url = "jdbc:mysql://localhost:3306/lotto_dev?autoreconnect=true"
        }
    }
    test {
        dataSource {
            dbCreate = 'validate'
//            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
            url = "jdbc:mysql://localhost:3306/lotto_test?autoreconnect=true"
        }
    }
    production {
        dataSource {
            dbCreate = 'validate'
//            url = "jdbc:h2:prodDb;MVCC=TRUE"
            url = "jdbc:mysql://localhost:3306/lotto?autoreconnect=true"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
