# RestaurantChoice

[TopJava internship ](http://javaops.ru/view/topjava) graduation project.


Project of voting system for deciding where to have lunch.\
The interface is built on the basis of roles (USER, ADMIN).\
The administrator can create/edit/delete users, restaurants and restaurant's dishes.\
The user can vote for his favorite restaurant, as well as change choice.\
Provided REST interface with basic authorization.\
The entire REST interface is covered by JUnit tests using the Spring MVC Test and Spring Security Test.

Used instruments and technologies: Spring (MVC, DataJpa, Security), Hibernate ORM, REST(Jackson),
Logging(SLF4J), Ehcache, JUnit 5, Maven, Apache Tomcat, HSQLDB.

##Please note:
 For the correctly  work of application, you must:
* declare the environment variable 'GRADUATION_ROOT', which will refer to the root of the project.
* configure HSQLDB with options, specified into '/resources/db/hsqldb.properties' file

## List of curl commands
### Users management:</b>
#### get all users:
`curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin`
#### get user 100013 (User):
`curl -s http://localhost:8080/rest/admin/users/100013 --user admin@gmail.com:admin`
#### create new user:
`curl -s -X POST -d '{"name":"newUser","email":"newuser@mail.ru","password":"newpasswd","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin`
#### update user (with id 100013):
`curl -s -X PUT -d '{"name":"updatedUser","email":"updated@mail.ru","password":"updatedpswd","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/100013 --user admin@gmail.com:admin`
#### delete user (with id 100013):
`curl -s -X DELETE http://localhost:8080/rest/admin/users/100013 --user admin@gmail.com:admin`

### Restaurants management
#### get all restaurants:
`curl -s http://localhost:8080/rest/restaurants --user admin@gmail.com:admin`
#### get restaurant(id:100001) with dishes:
`curl -s http://localhost:8080/rest/restaurants/100001 --user admin@gmail.com:admin`
#### create new restaurant:
`curl -s -X POST -d '{"name":"newRestaurant","address":"newAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin`
#### update restaurant with id:100001 :
`curl -s -X PUT -d '{"name":"updatedRestaurant","address":"updatedAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100001 --user admin@gmail.com:admin`
#### delete restaurant with id:100001 :
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100001 --user admin@gmail.com:admin`

### Dishes management
#### get all dishes:
`curl -s http://localhost:8080/rest/dishes --user admin@gmail.com:admin`
#### get dish id:100002 (with restaurant):
`curl -s http://localhost:8080/rest/dishes/100002 --user admin@gmail.com:admin`
#### add new dish to restaurant with id:100001:
`curl -s -X POST -d '{"name":"newDish","price":"2.2","restaurantId":"100001"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/dishes --user admin@gmail.com:admin`
#### delete dish with id:100008:
`curl -s -X DELETE http://localhost:8080/rest/admin/dishes/100008 --user admin@gmail.com:admin`

### Voting management
#### vote for restaurant with id:100000:
`curl -s -X PUT http://localhost:8080/rest/vote/100000?time=10:00 --user admin@gmail.com:admin`
#### cancel vote for restaurant with id:100000:
`curl -s -X PATCH http://localhost:8080/rest/vote/100000?time=10:00 --user admin@gmail.com:admin`
