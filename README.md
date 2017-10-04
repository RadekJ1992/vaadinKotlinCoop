**Requirements**

JAVA 8

Gradle 3.5 (other versions may work, too)

mySQL database

To view code you'll need IDEA or Eclipse with Kotlin plugin

**first we have to create mySQL database**

create database vaadin_kotlin_coop;

create user 'vaadinKotlin'@'localhost' identified by '1qaz@WSX';

grant all privileges on vaadin_kotlin_coop.* to 'vaadinKotlin'@'localhost'; 

**starting the app:**

gradle bootRun