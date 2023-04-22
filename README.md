# Email Marketing and Analysis System

This application enables business owners to schedule and send marketing emails in bulk to their subscribers. Additionally, it provides analytics such as Conversion Rate and Click-Through Rate (CTR) by tracking email openings and link clicks.

## How to Use

-   Install Java JDK. If you are unsure, check by running below command:

```
java -version
```

-   Install MySQL and start the server. You can run below command to check if MySQL is installed in your machine:

```
mysql --version
```

-   Install dependenies

```
./gradlew build --refresh-dependencies
```

-   Add configuration.json file inside `src/main/resources/configuration`. Use below as the reference:

```
{
  "env": "dev",
  "dev": {
    "mysqlUrl": <MYSQL_URL>,
    "userName": <USERNAME>,
    "dbPassword": <PASSWORD>,
    "smtpEmail": <RECEEIVER_EMAIL>
    "smtpPassword": <RECEIVER_SECRET_PASSWORD>
  },
  "test": {
    "mysqlUrl": <MYSQL_URL>,
    "userName": <USERNAME>,
    "dbPassword": <PASSWORD>,
    "smtpEmail": <RECEEIVER_EMAIL>
    "smtpPassword": <RECEIVER_SECRET_PASSWORD>
  },
  "prod": {
    "mysqlUrl": <MYSQL_URL>,
    "userName": <USERNAME>,
    "dbPassword": <PASSWORD>,
    "smtpEmail": <RECEEIVER_EMAIL>
    "smtpPassword": <RECEIVER_SECRET_PASSWORD>
  }
}
```

`NOTE:` As you can see above, we have three objects for development, test and production environment. `env` is used to set the active environment.

-   Run the application with below command:

```
./gradlew bootRun
```
