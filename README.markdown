# Grails: Lucky Lotto Application

##This is a sample application.

The application is configured to use MySQL and the Database Migration plugin. All
you need to do is create the lotto_dev (lotto_test, lotto) database and the plugin
will create the tables during application startup ($ grails run-app).

##To create the database:
    
    $ mysql -u root -p
    
    mysql> create database lotto_dev;
    mysql> grant all on lotto_dev.* to grails@localhost identified by 'server';
    mysql> exit

