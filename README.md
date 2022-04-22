# What is GrizzFinancial?

### GrizzFinancial is a personal expense tracker/budget manager built to help college students better their financial habits, where users will be able to:

- Perform CRUD operations on their personal expenses
- See graphical display of their spendings
- Pick a budget and see their remaining balance and total money spent
- update their budget as they see fit
- Automatic updating for their remaining balance when updating the budget or when adding/updating/deleting expenses
- Export their expenses to a pdf file that gets downloaded to their computer

## Technologies used:

```
  Backend : Spring Boot, Spring Security, Spring MVC, Spring Data JPA, Hibernate, MySQL
  Frontend: Thymeleaf, HTML 5, CSS, Bootstrap
```
## Steps to Setup
1. Clone the application
2. Create MySQL database
3. Change MySQL username and password as per your installation
- Open `src/main/resources/application.properties`
- Change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation
4. Build and run the application using Maven

![Expenses page](https://user-images.githubusercontent.com/84100829/164512420-f77ec5b3-2885-4d9c-bc7c-106928955b19.PNG)
