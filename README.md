# Pizza Franchise Management System

## Table of Contents
* [Description](#Description)
* [User Stories](#User-Stories)
* [Tools/Technologies](#Tools/Technologies)
* [Installation Instruction](#Installation-Instructions)
* [Usage Information](#Usage-Information)
* [Contributions](#Contributions)
* [Testing](#Testing)
* [Obstacles](#Obstacles)
* [Questions?](#Questions?)

## Description
This application is designed for employers of a pizza franchise to keep updated on employee, store, vehicle, and equipment information.

## User Stories
- As an employer for a pizza franchise, I want a management system so that I can manage store, employee, vehicle, and equipment information
-	As an employer for a pizza franchise, I want to ensure that only general managers have access to this application, so that not anyone can access or edit the information in the system
-	As an employer, I want to be able to add, remove and update employees, stores, vehicles, and equipment in the system so that the information I have on my business can easily stay up to date
-	As an employer, I want to be able to view information for all employees, stores, vehicles, and equipment so that I can ensure that it is accurate
-	As an employer, I want to be able to view employees and equipment based on what store they are associated with, so that I can better assess the needs of each store

## Tools/Technologies
This is a dynamic Spring MVC application that runs on Apache Tomcat port 8080. The app uses JPA to communicate with a MYSQL database, and has DAO classes that allow you to perform CRUD operations, tested by JUnit. All of the front-end is produced with JSP files.
## Installation Instructions
Make sure you have an IDE that can run Spring MVC applications and Apache Tomcat v9

## Usage Information
The app opens on a Welcome page. Hit the "Get Started" button to log in. You must log in as a general manager to interact with the application. Once you're logged in, use the navbar to navigate between the Employee, Store, Vehicle, and Equipment pages. Each of those pages has full CRUD functionality with its respective table in the database (i.e. adding, viewing,  updating, and deleting employees, stores, vehicles or equipment). On the Stores page, you can view all employees and all equipment associated with the same store. Once you are done, you can navigate to the Logout page on the far right of the navbar to end your session.

## Contributions
Charlie De La Rosa, Young Wook Baek, and Lawrence Palacios, my instructors at the Per Scholas java bootcamp all helped me complete this project.

## Testing
All dao CRUD methods are tested with Junit, and they all pass. You can run them by running the AllTests Test Suite in the Test package.

## Obstacles
This was my first Spring MVC project, so there was definitely a learning curve for working with the controllers, JSP, and getting data to load onto the page at times. It was also my first time working with session management, but once I figured out how to log in as a user, I was able to figure out how to log out with no errors on my first attempt. Finally, setting up relationships in the database broke my app in ways I wasn't anticipating, so I had to add several new methods to my DAO that add and remove employees and machinery from their respective store lists, and call those methods in the controller in the right spots.
## Questions?
Contact the author for further inquiries!<br>
Github link: (https://github.com/elijahflanders96)<br>
Email: elijahflanders86@gmail.com
