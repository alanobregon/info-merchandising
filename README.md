# Informatorio Merchandising API
## Pre-requisites
- JDK +11
- Maven +3.6
- MySQL Database

## Setup
### Clone repository
```shell
git clone https://github.com/Alan49/info-merchandising.git
cd info-merchandising
```

### Database connection setup
Create a `application.properties` file in `src/main/resources/` folder with the following instructions
```properties
spring.datasource.url=jdbc:mysql://localhost/db_info_merch?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=db_user
spring.datasource.password=db_password

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Change `spring.datasource.username` and `spring.datasource.password` for your MySQL credentials

### Build and run project
```shell
mvn spring-boot:run
```

## Endpoints
### Cities
| Method | Endpoint | Description |
| ------ | -------- | ------------|
| GET | `/cities`| Find all cities |
| GET | `/cities?name=Resistencia` | Find all cities with name like |
| POST | `/cities` | Add new city |
| GET | `/cities/:id` | Find city by id |
| PUT | `/cities/:id` | Update city by id |
| DELETE | `/cities/:id` | Delete city by id |

### Users
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET | `/users` | Find all users |
| GET | `/users?city=1` | Find all users for city |
| GET | `/users?created_at=2021-09-04` | Find all users that were created after date |
| GET | `/users?city=1&created_at=2021-09-04` | Find all users by city and that were created after date |
| POST | `/users` | Add new user |
| GET | `/users/:id` | Find user by id |
| PUT | `/users/:id` | Update user by id |
| DELETE | `/users/:id` | Delete user by id |

### Products
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET | `/products` | Find all published users |
| GET | `/products?name=Computer` | Find all published products with name like |
| GET | `/products/all` | Find all products |
| GET | `/products/all?name=Computer` | Find all products with name like |
| POST | `/products` | Add new product |
| GET | `/products/:id` | Find product by id |
| PUT | `/products/:id` | Update product by id |
| DELETE | `/products/:id` | Delete product by id |

### Carts
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET | `/users/:id/cart` | Find current cart by user |
| PUT | `/users/:id/cart` | Add new product to current cart by an user |
| DELETE | `/users/:id/cart/:product_id` | Remove product to current cart by an user |

### Orders
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET | `/orders` | Find all orders |
| GET | `/orders?user=1` | Find all orders by an user |
| POST | `/orders` | Add new order |
| GET | `/orders/:id` | Find order by id |
| PUT | `/orders/:id` | Update order by |
| DELETE | `/orders/:id` | Delete order by id |