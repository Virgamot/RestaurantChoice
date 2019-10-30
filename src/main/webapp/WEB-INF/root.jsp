<%--
  Created by IntelliJ IDEA.
  User: virgamot
  Date: 29.10.2019
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant Choice</title>
</head>
<style>
    mark{
        background: gainsboro;
    }
</style>
<body>
<div>
        &nbsp;&nbsp;&nbsp;<a href="https://github.com/Virgamot/RestaurantChoice">Java Enterprise проект</a> для
        голосования за за понравившийся ресторан. Интерфейс построен на основе ролей (USER, ADMIN).
        Администратор может создавать/редактировать/удалять пользователей и рестораны.
        Пользователь может голосовать за понравившийся ресторан, а так же менять свой выбор.
        Предоставлен REST интерфейс с базовой авторизацией.
        Весь REST интерфейс покрывается  JUnit тестами, используя Spring MVC Test и Spring Security Test.
</div>
<div>
    <br><b>Список curl команд для управления пользователями:</b>
    <br> get all users:
    <br><mark> curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin</mark>
    <br> get user 100013 (User):
    <br><mark> curl -s http://localhost:8080/rest/admin/users/100013 --user admin@gmail.com:admin</mark>
    <br> create new user:
    <br><mark> curl -s -X POST -d '{"name":"newUser","email":"newuser@mail.ru","password":"newpasswd","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin</mark>
    <br> update user (with id 100013):
    <br><mark> curl -s -X PUT -d '{"name":"updatedUser","email":"updated@mail.ru","password":"updatedpswd","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/100013 --user admin@gmail.com:admin</mark>
    <br> delete user (with id 100013):
    <br><mark> curl -s -X DELETE http://localhost:8080/rest/admin/users/100013 --user admin@gmail.com:admin</mark>
    <br>
    <br><b>Список curl команд для управления ресторанами:</b>
    <br> get all restaurants:
    <br><mark> curl -s http://localhost:8080/rest/restaurants --user admin@gmail.com:admin</mark>
    <br> get restaurant(id:100001) with dishes:
    <br><mark> curl -s http://localhost:8080/rest/restaurants/100001 --user admin@gmail.com:admin</mark>
    <br> create new restaurant:
    <br><mark> curl -s -X POST -d '{"name":"newRestaurant","address":"newAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin</mark>
    <br> update restaurant with id:100001 :
    <br><mark> curl -s -X PUT -d '{"name":"updatedRestaurant","address":"updatedAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100001 --user admin@gmail.com:admin</mark>
    <br> delete restaurant with id:100001 :
    <br><mark> curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100001 --user admin@gmail.com:admin</mark>
    <br>
    <br><b>Список curl команд для управлением блюдами:</b>
    <br> get all dishes:
    <br><mark> curl -s http://localhost:8080/rest/dishes --user admin@gmail.com:admin</mark>
    <br> get dish id:100002 (with restaurant):
    <br><mark> curl -s http://localhost:8080/rest/dishes/100002 --user admin@gmail.com:admin</mark>
    <br> add new dish to restaurant with id:100001:
    <br><mark> curl -s -X POST -d '{"name":"newDish","price":"2.2","restaurantId":"100001"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/dishes --user admin@gmail.com:admin</mark>
    <br> delete dish with id:100008:
    <br><mark> curl -s -X DELETE http://localhost:8080/rest/admin/dishes/100008 --user admin@gmail.com:admin</mark>
    <br>
    <br><b>Список curl команд для голосования:</b>
    <br> vote for restaurant with id:100000:
    <br><mark> curl -s -X PUT http://localhost:8080/rest/vote/100000?time=10:00 --user admin@gmail.com:admin</mark>
    <br> cancel vote for restaurant with id:100000:
    <br><mark> curl -s -X PATCH http://localhost:8080/rest/vote/100000?time=10:00 --user admin@gmail.com:admin</mark>

</div>
<div>
    <p>Стек технологий: <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
        <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
        <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
        <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
            Test</a>,
        <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
        <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
        <a href="http://www.slf4j.org/">SLF4J</a>,
        <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
        <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
        <a href="http://ehcache.org">Ehcache</a>,
        <a href="http://junit.org/">JUnit</a>,
        <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>
    </p>
</div>
</body>
</html>
